package com.SpringBoot.service;

import com.SpringBoot.DAO.ServiceProviderRepository;
import com.SpringBoot.entities.ServiceConsumer;
import com.SpringBoot.entities.ServiceProvider;
import com.SpringBoot.entities.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service
public class ServiceProviderServiceIMPL implements ServiceProviderService{

    @Autowired
    ServiceProviderRepository serviceProviderRepository;
    @Override
    public ServiceProvider findRecruiter(int id) {
        return serviceProviderRepository.findById(id).orElse(null);
    }

    @Override
    public ServiceProvider register(ServiceProvider sp) throws Exception{
        ServiceProvider obj = serviceProviderRepository.findByEmail(sp.getEmail());
        if (obj != null) {
            throw new Exception("service provider already exist");
        }
        return serviceProviderRepository.save(sp);
    }

    @Override
    public ServiceProvider login(ServiceProvider sp) throws Exception{
        ServiceProvider obj = serviceProviderRepository.findByEmail(sp.getEmail());
        if(obj==null) {
            throw new Exception("service provider doesn't exist");
        }
        else if(!sp.getPassword().equals(obj.getPassword())) {
            throw new Exception("incorrect password");
        }

        return obj;
    }
    }

