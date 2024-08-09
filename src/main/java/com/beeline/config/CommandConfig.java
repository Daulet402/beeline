package com.beeline.config;

public class CommandConfig {
    private CommandType type;
    private String code;
    private String description;

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public enum CommandType {
        GO_TO_MAIN_MENU,
        GO_BACK,
        PRINT_CURRENT_MENU,
        EXIT,
    }
}


