package com.SpringBoot.service;

import com.SpringBoot.DAO.ServiceConsumerRepository;
import com.SpringBoot.entities.ServiceConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;
import com.SpringBoot.entities.Services;
import java.util.ArrayList;

@Service
public class ServiceConsumerServiceIMPL implements ServiceConsumerService{
    @Autowired
    ServiceConsumerRepository serviceConsumerRepository;
    @Override
    public ServiceConsumer register(ServiceConsumer serviceConsumer) throws Exception {
        ServiceConsumer obj = serviceConsumerRepository.findByEmail(serviceConsumer.getEmail());
        if (obj != null) {
            throw new Exception("service consumer already exist");
        }
        if (serviceConsumer.getServicesList() == null) {
            serviceConsumer.setServicesList(new ArrayList<Services>());
        }
        return serviceConsumerRepository.save(serviceConsumer);
    }
        public ServiceConsumer login(ServiceConsumer serviceConsumer) throws Exception {
            ServiceConsumer obj = serviceConsumerRepository.findByEmail(serviceConsumer.getEmail());
            if(obj==null) {
                throw new Exception("User didn't exists");
            }
            else if(!serviceConsumer.getPassword().equals(obj.getPassword())) {
                throw new Exception("Bad credentials");
            }

            return obj;
        }
    }

