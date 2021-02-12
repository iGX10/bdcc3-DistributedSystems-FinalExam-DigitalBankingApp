package org.sid.customerservice.repositories;

import org.sid.customerservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
    public Client findClientByEmail(@Param("email") String email);
}
