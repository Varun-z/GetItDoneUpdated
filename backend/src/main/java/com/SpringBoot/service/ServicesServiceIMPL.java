package com.SpringBoot.service;

import com.SpringBoot.DAO.ServicesRepository;
import com.SpringBoot.entities.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Services saveService(int id, Services services) {
        services.setServiceProvider(serviceProviderService.findProvider(id));
        Services obj = servicesRepository.save(services);
        List<Services> tempServiceList = serviceProviderService.findProvider(id).getServicesList();
        tempServiceList.add(obj);
        serviceProviderService.findProvider(id).setServicesList(tempServiceList);
        return obj;
    }

    @Override
    public Services getServiceById(int id) {
        return servicesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Services> findServicesByZipCode(Integer zipCode) {
        return servicesRepository.findByZipCode(zipCode);
    }
}
