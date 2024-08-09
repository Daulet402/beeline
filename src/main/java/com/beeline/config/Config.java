package com.beeline.config;

import com.beeline.entity.MenuItem;

import java.util.List;

public class Config {
    private List<MenuItem> menu;
    private List<CommandConfig> commandConfig;
    private BalanceConfig balanceConfig;

    public List<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }

    public List<CommandConfig> getCommandConfig() {
        return commandConfig;
    }

    public void setCommandConfig(List<CommandConfig> commandConfig) {
        this.commandConfig = commandConfig;
    }

    public BalanceConfig getBalanceConfig() {
        return balanceConfig;
    }

    public void setBalanceConfig(BalanceConfig balanceConfig) {
        this.balanceConfig = balanceConfig;
    }
}
