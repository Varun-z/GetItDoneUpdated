package com.SpringBoot.service;

import com.SpringBoot.DAO.ServiceProviderRepository;
import com.SpringBoot.entities.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class ServiceProviderServiceIMPL implements ServiceProviderService{

    @Autowired
    ServiceProviderRepository serviceProviderRepository;
    @Override
    public ServiceProvider findRecruiter(UUID id) {
        return serviceProviderRepository.findById(id).orElse(null);
    }

    @Override
    public ServiceProvider register(ServiceProvider sp) throws Exception{
        ServiceProvider obj = serviceProviderRepository.findByEmail(sp.getEmail());
        if(obj!=null) {
            throw new Exception("This user already exists ");
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

