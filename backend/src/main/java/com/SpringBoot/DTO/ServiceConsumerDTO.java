package com.SpringBoot.DTO;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ServiceConsumerDTO {
    private String name;
    private String email;
    private String password;
}
