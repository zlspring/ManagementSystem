package com.aohui.btcorg.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 后台操作记录
 */
@Entity
@Table(name = "background_operation_record")
public class BackgroundOperationRecordEntity {
    @Id
    private Long id;
    private Long uid;
    private String operation_account;
    private String operation_time;
    private String operation_type;
    private String operator_mailbox;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperation_account() {
        return operation_account;
    }

    public void setOperation_account(String operation_account) {
        this.operation_account = operation_account;
    }

    public String getOperation_time() {
        return operation_time;
    }

    public void setOperation_time(String operation_time) {
        this.operation_time = operation_time;
    }

    public String getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }

    public String getOperator_mailbox() {
        return operator_mailbox;
    }

    public void setOperator_mailbox(String operator_mailbox) {
        this.operator_mailbox = operator_mailbox;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "BackgroundOperationRecordEntity{" +
                "id=" + id +
                ", uid=" + uid +
                ", operation_account='" + operation_account + '\'' +
                ", operation_time='" + operation_time + '\'' +
                ", operation_type='" + operation_type + '\'' +
                ", operator_mailbox='" + operator_mailbox + '\'' +
                '}';
    }
}
