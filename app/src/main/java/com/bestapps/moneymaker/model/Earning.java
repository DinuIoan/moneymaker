package com.bestapps.moneymaker.model;

public class Earning {
    private Long id;
    private String label;
    private Double amount;
    private String date;

    public Earning(Long id, String label, Double amount, String date) {
        this.id = id;
        this.label = label;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return label;
    }

    public void setDescription(String description) {
        this.label = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
