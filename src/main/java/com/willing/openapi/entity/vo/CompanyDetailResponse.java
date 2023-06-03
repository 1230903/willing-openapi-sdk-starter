package com.willing.openapi.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
@Data
public class CompanyDetailResponse extends BaseResponse implements Serializable {

    private CompanyDetailDTO data;

    @Data
    public static class CompanyDetailDTO implements Serializable {
        private String companyId;

        private String abbreviation;
        private String companyName;
        private String creditCode;
        private String status;
        private String corpFoundedTime;
        private String registerAddress;
        private CompanyUserDTO userList;
        private CompanyStaffDTO staffList;
        private CompanyCreatorDTO creator;
        private Long createTime;
        private CompanyBusinessDTO businessList;
        private String industry;
        private Integer populationSize;
        private String websiteList;
        private Map<String, Object> companyCustom;
    }

    @Data
    public static class CompanyUserDTO implements Serializable {
        private String id;
        private String nickName;
        private String type;
        private String opUserId;
    }

    @Data
    public static class CompanyStaffDTO implements Serializable {
        private String userId;
        private String nickName;
        private String avatar;
        private String position;
    }

    @Data
    public static class CompanyCreatorDTO implements Serializable {
        private String id;
        private String nickName;
    }

    @Data
    public static class CompanyBusinessDTO implements Serializable {
        private String businessId;
        private String businessName;
    }
}
