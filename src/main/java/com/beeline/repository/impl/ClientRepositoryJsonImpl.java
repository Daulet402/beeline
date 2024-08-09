package com.beeline.repository.impl;

import com.beeline.entity.Client;
import com.beeline.repository.ClientRepository;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientRepositoryJsonImpl implements ClientRepository {
    private static final Gson gson = new Gson();
    private static List<Client> clients = new ArrayList<>();

    public ClientRepositoryJsonImpl() {
        loadClients();
    }

    private List<Client> loadClients() {
        if (clients.isEmpty()) {
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/clients.json");
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                clients = Arrays.asList(gson.fromJson(reader, Client[].class));
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Clients are not loaded");
            }
        }
        return clients;
    }

    @Override
    public Client findClientByName(String name) {
        return clients.stream()
                .filter(c -> StringUtils.equalsIgnoreCase(name, c.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }
}

