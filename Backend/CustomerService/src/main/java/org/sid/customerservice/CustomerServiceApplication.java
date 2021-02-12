package org.sid.customerservice;

import org.sid.customerservice.entities.Client;
import org.sid.customerservice.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ClientRepository clientRepository,
                            RepositoryRestConfiguration repositoryRestConfiguration) {
        repositoryRestConfiguration.exposeIdsFor(Client.class);
        return args -> {
            clientRepository.save(new Client(null, "Client1", "client1@mail.com"));
            clientRepository.save(new Client(null, "Client2", "client2@mail.com"));
            clientRepository.save(new Client(null, "Client3", "client3@mail.com"));

            clientRepository.findAll().forEach(client -> {
                System.out.println(client.toString());
            });
        };
    }

}
