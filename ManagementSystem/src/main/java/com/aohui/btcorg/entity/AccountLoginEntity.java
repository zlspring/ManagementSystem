package com.aohui.btcorg.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 账户登录记录表
 */
@Entity
@Table(name = "accountlogin")
public class AccountLoginEntity {
    @Id
    private Long id;
    private Long accountLoginId;
    private String loginTime;
    private String loginaddresses;
    private String loginIP;
    private String facilityInformation;

    public String getLoginaddresses() {
        return loginaddresses;
    }

    public void setLoginaddresses(String loginaddresses) {
        this.loginaddresses = loginaddresses;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public Long getAccountLoginId() {
        return accountLoginId;
    }

    public void setAccountLoginId(Long accountLoginId) {
        this.accountLoginId = accountLoginId;
    }

    public String getFacilityInformation() {
        return facilityInformation;
    }

    public void setFacilityInformation(String facilityInformation) {
        this.facilityInformation = facilityInformation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AccountLoginEntity{" +
                "id=" + id +
                ", accountLoginId=" + accountLoginId +
                ", loginTime='" + loginTime + '\'' +
                ", loginaddresses='" + loginaddresses + '\'' +
                ", loginIP='" + loginIP + '\'' +
                ", facilityInformation='" + facilityInformation + '\'' +
                '}';
    }
}
