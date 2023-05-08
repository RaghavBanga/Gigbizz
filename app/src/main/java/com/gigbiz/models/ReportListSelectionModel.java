package com.gigbiz.models;

public class ReportListSelectionModel {

    public String name;
    public int color;
    public int icon;

    public ReportListSelectionModel(String name, int color, int icon) {
        this.name = name;
        this.color = color;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
