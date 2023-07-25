package com.SpringBoot.controller;

import com.SpringBoot.DTO.ServiceConsumerDTO;
import com.SpringBoot.entities.ServiceConsumer;
import com.SpringBoot.entities.ServiceProvider;
import com.SpringBoot.entities.Services;
import com.SpringBoot.service.ServiceConsumerService;
import com.SpringBoot.service.ServiceProviderService;
import com.SpringBoot.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
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
    public Services addService(@PathVariable("SPid") int id, @RequestBody Services services) {
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
    @GetMapping("/{SCid}/selectService/{serviceId}")
    public void selectService(@PathVariable("serviceId") int id,@PathVariable("SCid") int SCId) {
        serviceConsumerService.selectPrefferedService(SCId,id);
    }
    @GetMapping("fetchServiceConsumer/{SCid}")
    public ServiceConsumer getConsumer(@PathVariable("SCid") int id) {
        return serviceConsumerService.getServiceConsumer(id);
    }
    @GetMapping("/fetchServiceByZipcode/{zipCode}")
    public List<Services> findServiceByZipcode(@PathVariable("zipCode") Integer zipCode) {
        return servicesService.findServicesByZipCode(zipCode);
    }
    @GetMapping("/{serviceId}")
    public ResponseEntity<Services> getServiceById(@PathVariable("serviceId") int serviceId) {
        Services service = servicesService.getServiceById(serviceId);
        if (service != null) {
            return ResponseEntity.ok(service);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/serviceProvider/{providerId}/serviceConsumers")
    public List<ServiceConsumerDTO> getServiceConsumersByProviderId(@PathVariable int providerId) {
        ServiceProvider serviceProvider = serviceProviderService.findRecruiter(providerId);
        if (serviceProvider == null) {
            // Handle the case where the service provider is not found
            return new ArrayList<>();
        }

        List<ServiceConsumerDTO> serviceConsumerDTOs = new ArrayList<>();
        for (Services service : serviceProvider.getServicesList()) {
            for (ServiceConsumer serviceConsumer : service.getServiceConsumerList()) {
                ServiceConsumerDTO dto = new ServiceConsumerDTO();
                dto.setName(serviceConsumer.getName());
                dto.setEmail(serviceConsumer.getEmail());
                dto.setServiceTitle(service.getTitle());
                dto.setServiceDescription(service.getDescription());

                serviceConsumerDTOs.add(dto);
            }
        }

        return serviceConsumerDTOs;
    }
}
