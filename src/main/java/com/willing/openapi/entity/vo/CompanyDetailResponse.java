package com.willing.openapi.entity.vo;

import cn.hutool.core.annotation.Alias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CompanyDetailResponse extends BaseResponse implements Serializable {

    private CompanyDetailDTO data;

    @Data
    public static class CompanyDetailDTO implements Serializable {

        @JsonProperty(value = "company_id")
        private String companyId;

        private String abbreviation;

        @JsonProperty(value = "company_name")
        private String companyName;

        @JsonProperty(value = "credit_code")
        private String creditCode;

        private String status;

        @JsonProperty(value = "corp_founded_time")
        private String corpFoundedTime;

        @JsonProperty(value = "register_address")
        private String registerAddress;

        @JsonProperty(value = "user_list")
        private List<CompanyUserDTO> userList;

        @JsonProperty(value = "staff_list")
        private List<CompanyStaffDTO> staffList;

        @JsonProperty(value = "business_list")
        private List<CompanyBusinessDTO> businessList;

        private CompanyCreatorDTO creator;

        @JsonProperty(value = "create_time")
        private Long createTime;

        private String industry;

        @JsonProperty(value = "population_size")
        private Integer populationSize;

        @JsonProperty(value = "website_list")
        private String websiteList;

        @JsonProperty(value = "company_custom")
        private Map<String, Object> companyCustom;
    }

    @Data
    public static class CompanyUserDTO implements Serializable {

        private String id;

        @JsonProperty(value = "nick_name")
        private String nickName;

        private String type;

        @JsonProperty(value = "op_user_id")
        private String opUserId;
    }

    @Data
    public static class CompanyStaffDTO implements Serializable {
        @JsonProperty(value = "user_id")
        private String userId;

        @JsonProperty(value = "nick_name")
        private String nickName;

        private String avatar;

        private String position;
    }

    @Data
    public static class CompanyCreatorDTO implements Serializable {

        private String id;

        @JsonProperty(value = "nick_name")
        private String nickName;
    }

    @Data
    public static class CompanyBusinessDTO implements Serializable {

        @JsonProperty(value = "business_id")
        private String businessId;

        @Alias("businessName")
        private String businessName;
    }
}
