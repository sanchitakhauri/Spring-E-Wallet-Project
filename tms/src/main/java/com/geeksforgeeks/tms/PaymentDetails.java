package com.geeksforgeeks.tms;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PaymentDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String source;
    private String bankId;
    private String backtransactionId;
    public PaymentDetails(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getTransactionId() {
        return backtransactionId;
    }

    public void setTransactionId(String transactionId) {
        this.backtransactionId = transactionId;
    }

}
