package com.SpringBoot.service;

import com.SpringBoot.entities.ServiceConsumer;

public interface ServiceConsumerService {
    ServiceConsumer register(ServiceConsumer serviceConsumer) throws Exception;
    ServiceConsumer login(ServiceConsumer serviceConsumer) throws Exception;
}
