package com.bestapps.moneymaker.model;

public class LabelEarnings implements Comparable{
    private String type;
    private Double totalEarning;
    private int iconId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getTotalEarning() {
        return totalEarning;
    }

    public void setTotalEarning(Double totalEarning) {
        this.totalEarning = totalEarning;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    @Override
    public int compareTo(Object o) {
        return this.getTotalEarning().compareTo(((LabelEarnings) o).getTotalEarning());
    }
}
