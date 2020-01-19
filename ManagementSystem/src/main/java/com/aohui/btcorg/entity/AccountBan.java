package com.aohui.btcorg.entity;

/**
 * 账户解禁
 */
public class AccountBan {
    private Long id;
    private String account;
    private String resetType;
    private String timeOfApplication;
    private String whetherComplete;
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

    public String getResetType() {
        return resetType;
    }

    public void setResetType(String resetType) {
        this.resetType = resetType;
    }

    public String getTimeOfApplication() {
        return timeOfApplication;
    }

    public void setTimeOfApplication(String timeOfApplication) {
        this.timeOfApplication = timeOfApplication;
    }

    public String getWhetherComplete() {
        return whetherComplete;
    }

    public void setWhetherComplete(String whetherComplete) {
        this.whetherComplete = whetherComplete;
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
}
