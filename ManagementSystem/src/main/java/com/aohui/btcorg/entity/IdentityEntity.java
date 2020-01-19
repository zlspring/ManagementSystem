package com.aohui.btcorg.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 身份认证
 */
@Entity
@Table(name = "identity")
public class IdentityEntity {
    @Id
    private Long id;
    private String account;
    private String auditStatus;
    private String auditTime;
    private String passTime;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getPassTime() {
        return passTime;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    @Override
    public String toString() {
        return "IdentityEntity{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", auditStatus='" + auditStatus + '\'' +
                ", auditTime='" + auditTime + '\'' +
                ", passTime='" + passTime + '\'' +
                '}';
    }
}
