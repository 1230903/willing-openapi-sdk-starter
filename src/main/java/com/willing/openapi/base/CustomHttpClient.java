package com.willing.openapi.base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author xiaozhou
 * @date 2023/6/12
 * <p>
 * </p>
 */
@Slf4j
public class CustomHttpClient {

    private final HttpRequestRetryHandler defaultHttpRequestRetryHandler = (exception, executionCount, context) -> false;

    private final SSLConnectionSocketFactory sslConnectionSocketFactory = SSLConnectionSocketFactory.getSocketFactory();

    private final PlainConnectionSocketFactory plainConnectionSocketFactory = PlainConnectionSocketFactory.getSocketFactory();

    /**
     * 自定义重试策略
     */
    private HttpRequestRetryHandler httpRequestRetryHandler;

    private final WillingOpenapiAccessTokenProperties properties;

    public CustomHttpClient(WillingOpenapiAccessTokenProperties properties) {
        this.properties = properties;
    }


    /**
     * 创建
     *
     * @return {@link CloseableHttpClient}
     */
    public CloseableHttpClient create() {

        HttpClientBuilder httpClientBuilder = HttpClients.custom()
                .setConnectionManager(this.connectionManager())
                .setConnectionManagerShared(true)
                .setSSLSocketFactory(this.buildSSLConnectionSocketFactory())
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setSocketTimeout(5000)
                        .setConnectTimeout(5000)
                        .setConnectionRequestTimeout(-1)//获取链接的超时时间设置,设置为零时不超时,一直等待.
                        .build());

        return httpClientBuilder
                .setRetryHandler(httpRequestRetryHandler == null ? defaultHttpRequestRetryHandler : httpRequestRetryHandler)
                .build();
    }


    /**
     * 连接管理器
     *
     * @return {@link PoolingHttpClientConnectionManager}
     */
    private PoolingHttpClientConnectionManager connectionManager() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", this.plainConnectionSocketFactory)
                .register("https", this.sslConnectionSocketFactory)
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(null == properties.getMaxTotal() ? 800 : properties.getMaxTotal());
        connectionManager.setDefaultMaxPerRoute(400);
        connectionManager.setDefaultSocketConfig(
                SocketConfig.copy(SocketConfig.DEFAULT)
                        .setSoTimeout(5000)
                        .build());
        return connectionManager;
    }

    /**
     * 构建 sslconnection 套接字工厂
     *
     * @return {@link SSLConnectionSocketFactory}
     */
    private SSLConnectionSocketFactory buildSSLConnectionSocketFactory() {
        try {
            SSLContext sslcontext = SSLContexts.custom()
                    //忽略掉对服务器端证书的校验
                    .loadTrustMaterial(new TrustStrategy() {
                        @Override
                        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            return true;
                        }
                    }).build();

            return new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            log.error("构建SSL连接工厂时发生异常！", e);
        }
        return null;
    }
}
