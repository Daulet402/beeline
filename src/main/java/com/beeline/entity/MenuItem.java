package com.beeline.entity;

import java.util.List;

public class MenuItem {
    private SpecificMenuType type;
    private String code;
    private String name;
    private String message;
    private List<MenuItem> submenu;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(List<MenuItem> submenu) {
        this.submenu = submenu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SpecificMenuType getType() {
        return type;
    }

    public void setType(SpecificMenuType type) {
        this.type = type;
    }

    /**
     * defines if menu item should be processed specifically
     */
    public enum SpecificMenuType {
        BALANCE,
        INTERNET_TRAFFIC,
        EXIT,
    }
}
