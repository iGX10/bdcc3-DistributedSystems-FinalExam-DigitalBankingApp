package org.sid.compteoperationservice.repositories;

import org.sid.compteoperationservice.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompteRepository extends JpaRepository<Compte, Long> {
}
