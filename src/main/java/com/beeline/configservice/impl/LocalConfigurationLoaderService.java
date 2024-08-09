package com.beeline.configservice.impl;

import com.beeline.configservice.ConfigurationLoader;
import com.beeline.config.Config;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LocalConfigurationLoaderService implements ConfigurationLoader {
    private static final Gson gson = new Gson();

    @Override
    public Config loadConfig() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("appconfig.json");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return gson.fromJson(reader, Config.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Config is not loaded");
        }
    }
}
