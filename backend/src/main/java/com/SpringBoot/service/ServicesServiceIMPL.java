package com.SpringBoot.service;

import com.SpringBoot.DAO.ServiceProviderRepository;
import com.SpringBoot.DAO.ServicesRepository;
import com.SpringBoot.entities.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServicesServiceIMPL implements ServicesService{
    @Autowired
    ServicesRepository servicesRepository;

    @Autowired
    ServiceProviderService serviceProviderService;
    @Override
    public List<Services> getServices() {
        return servicesRepository.findAll();
    }

    @Override
    public Services saveService(UUID id, Services services) {
        services.setServiceProvider(serviceProviderService.findRecruiter(id));
        Services obj = servicesRepository.save(services);
        List<Services> tempJobList = serviceProviderService.findRecruiter(id).getServicesList();
        tempJobList.add(obj);
        serviceProviderService.findRecruiter(id).setServicesList(tempJobList);
        return obj;
    }
}
