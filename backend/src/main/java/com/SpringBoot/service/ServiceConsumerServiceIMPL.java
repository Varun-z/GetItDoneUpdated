package com.SpringBoot.service;

import com.SpringBoot.DAO.ServiceConsumerRepository;
import com.SpringBoot.entities.ServiceConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;
import com.SpringBoot.entities.Services;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceConsumerServiceIMPL implements ServiceConsumerService{
    @Autowired
    ServiceConsumerRepository serviceConsumerRepository;
    @Autowired
    ServicesService servicesService;
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

    @Override
    public void selectPrefferedService(UUID scId, UUID id) {
        Services tempService=servicesService.getServiceById(id);
        System.out.println("This is here");
        if(getServiceConsumer(scId).getServicesList()==null) {
            System.out.println(getServiceConsumer(scId).getServicesList());
            getServiceConsumer(scId).setServicesList(new ArrayList<Services>());
            System.out.println("is it working");
        }
        getServiceConsumer(scId).getServicesList().add(tempService);
        ServiceConsumer tempServiceConsumer = getServiceConsumer(scId);
        if(servicesService.getServiceById(id).getServiceConsumerList() == null) {
            servicesService.getServiceById(id).setServiceConsumerList(new ArrayList<ServiceConsumer>());
            System.out.println("working");
        }
        List<ServiceConsumer> tempServiceConsumerList = servicesService.getServiceById(id).getServiceConsumerList();
        tempServiceConsumerList.add(tempServiceConsumer);
        servicesService.getServiceById(id).setServiceConsumerList(tempServiceConsumerList);
        tempServiceConsumer.setServicesList(getServiceConsumer(scId).getServicesList());
        serviceConsumerRepository.saveAndFlush(tempServiceConsumer);
    }

    @Override
    public ServiceConsumer getServiceConsumer(UUID id) {
        return serviceConsumerRepository.findById(id).orElse(null);
    }
}

