package com.SpringBoot.controller;

import com.SpringBoot.entities.ServiceConsumer;
import com.SpringBoot.entities.ServiceProvider;
import com.SpringBoot.entities.Services;
import com.SpringBoot.service.ServiceConsumerService;
import com.SpringBoot.service.ServiceProviderService;
import com.SpringBoot.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MyController {
    @Autowired
    ServiceConsumerService serviceConsumerService;

    @Autowired
    ServicesService servicesService;

    @Autowired
    ServiceProviderService serviceProviderService;

    @GetMapping("/")
    public List<Services> getAllServices(){
        return servicesService.getServices();
    }
    @PostMapping("/addService/{SPid}")
    public Services addService(@PathVariable("SPid") UUID id, @RequestBody Services services) {
        return servicesService.saveService(id,services);
    }
    @PostMapping("/registerServiceProvider")
    public ServiceProvider registerSP(@RequestBody ServiceProvider SP) throws Exception {
        return serviceProviderService.register(SP);
    }
    @PostMapping("/loginServiceProvider")
    public ServiceProvider loginSP(@RequestBody ServiceProvider SP) throws Exception {
        return serviceProviderService.login(SP);
    }
    @PostMapping("/registerServiceConsumer")
    public ServiceConsumer register(@RequestBody ServiceConsumer serviceConsumer) throws Exception {
        return serviceConsumerService.register(serviceConsumer);
    }

    @PostMapping("/loginServiceConsumer")
    public ServiceConsumer login(@RequestBody ServiceConsumer serviceConsumer) throws Exception {
        return serviceConsumerService.login(serviceConsumer);
    }
}
