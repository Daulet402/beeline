package com.beeline.repository;

import com.beeline.entity.Client;


public interface ClientRepository {

    Client findClientByName(String name);
}
