package com.gigbiz.models;

public class PaymentModel {
    String name;
    String process;
    String approved;
    String rejected;
    String total;
    String paid;
    String balance;

    public PaymentModel(String name, String process, String approved, String rejected, String total, String paid, String balance) {
        this.name = name;
        this.process = process;
        this.approved = approved;
        this.rejected = rejected;
        this.total = total;
        this.paid = paid;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getRejected() {
        return rejected;
    }

    public void setRejected(String rejected) {
        this.rejected = rejected;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}