package com.hcmute.ltdd.model;

public class Feature {
    private String name;
    private boolean selected;
    private int iconResId;

    public Feature(String name, boolean selected, int iconResId) {
        this.name = name;
        this.selected = selected;
        this.iconResId = iconResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }
}