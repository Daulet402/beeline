package com.beeline.entity;

public class Client {
    private String name;
    private double balance;
    private long trafficAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getTrafficAmount() {
        return trafficAmount;
    }

    public void setTrafficAmount(long trafficAmount) {
        this.trafficAmount = trafficAmount;
    }
}

