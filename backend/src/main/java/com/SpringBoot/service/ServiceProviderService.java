package com.SpringBoot.service;

import com.SpringBoot.entities.ServiceProvider;

import java.util.UUID;

public interface ServiceProviderService {
    ServiceProvider findRecruiter(int id);

    ServiceProvider register(ServiceProvider sp) throws Exception;

    ServiceProvider login(ServiceProvider sp) throws Exception;
}
