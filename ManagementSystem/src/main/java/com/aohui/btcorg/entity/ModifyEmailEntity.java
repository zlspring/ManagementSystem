package com.aohui.btcorg.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 修改邮箱
 */
@Entity
@Table(name = "modifyemail")
public class ModifyEmailEntity {
    @Id
    private Long id;
    private String account;
    private String attestation;
    private String applyForTime;
    private String systemAuditStatus;
    private String answerTheQuestionsOnRecord;
    private String imageContrast;
    private String transitTime;

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

    public String getAttestation() {
        return attestation;
    }

    public void setAttestation(String attestation) {
        this.attestation = attestation;
    }

    public String getApplyForTime() {
        return applyForTime;
    }

    public void setApplyForTime(String applyForTime) {
        this.applyForTime = applyForTime;
    }

    public String getSystemAuditStatus() {
        return systemAuditStatus;
    }

    public void setSystemAuditStatus(String systemAuditStatus) {
        this.systemAuditStatus = systemAuditStatus;
    }

    public String getAnswerTheQuestionsOnRecord() {
        return answerTheQuestionsOnRecord;
    }

    public void setAnswerTheQuestionsOnRecord(String answerTheQuestionsOnRecord) {
        this.answerTheQuestionsOnRecord = answerTheQuestionsOnRecord;
    }

    public String getImageContrast() {
        return imageContrast;
    }

    public void setImageContrast(String imageContrast) {
        this.imageContrast = imageContrast;
    }

    public String getTransitTime() {
        return transitTime;
    }

    public void setTransitTime(String transitTime) {
        this.transitTime = transitTime;
    }

    @Override
    public String toString() {
        return "ModifyEmailEntity{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", attestation='" + attestation + '\'' +
                ", applyForTime='" + applyForTime + '\'' +
                ", systemAuditStatus='" + systemAuditStatus + '\'' +
                ", answerTheQuestionsOnRecord='" + answerTheQuestionsOnRecord + '\'' +
                ", imageContrast='" + imageContrast + '\'' +
                ", transitTime='" + transitTime + '\'' +
                '}';
    }
}
