package com.SpringBoot.DTO;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ServiceConsumerDTO {

    private String name;
    private String email;
    private String serviceTitle;
    private String serviceDescription;
    public ServiceConsumerDTO() {
    }

    public ServiceConsumerDTO(String name, String email, String serviceTitle, String serviceDescription) {
        this.name = name;
        this.email = email;
        this.serviceTitle = serviceTitle;
        this.serviceDescription = serviceDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}
