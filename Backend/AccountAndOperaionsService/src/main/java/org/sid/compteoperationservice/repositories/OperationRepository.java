package org.sid.compteoperationservice.repositories;

import org.sid.compteoperationservice.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;


public interface OperationRepository extends JpaRepository<Operation, Long> {
    public Collection<Operation> findOperationsByCompteId(Long id);
}
