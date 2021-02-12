package org.sid.compteoperationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CompteOperationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompteOperationServiceApplication.class, args);
    }

}
