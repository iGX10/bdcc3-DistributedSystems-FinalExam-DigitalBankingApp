package org.sid.compteoperationservice;

import org.sid.compteoperationservice.entities.Compte;
import org.sid.compteoperationservice.entities.Operation;
import org.sid.compteoperationservice.feign.ClientRestClient;
import org.sid.compteoperationservice.repositories.CompteRepository;
import org.sid.compteoperationservice.services.CompteOperationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableFeignClients
@Transactional
public class CompteOperationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompteOperationServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(RepositoryRestConfiguration repositoryRestConfiguration,
                            CompteOperationService compteOperationService,
                            CompteRepository compteRepository) {
        repositoryRestConfiguration.exposeIdsFor(Compte.class, Operation.class);
        return args -> {
            Compte compte1 = compteOperationService.ajouterCompte(1111, "COURANT", 1L);
            Compte compte2 = compteOperationService.ajouterCompte(2222, "COURANT", 2L);
            compteOperationService.versement(111, compte1.getId());
            compteOperationService.versement(222, compte2.getId());
            compteOperationService.virement(100, compte1.getId(), compte2.getId());

            compteRepository.findAll().forEach(compte -> {
                System.out.println("Compte : "+compte.getId()+", solde : "+compte.getSolde());
            });
        };
    }

}
