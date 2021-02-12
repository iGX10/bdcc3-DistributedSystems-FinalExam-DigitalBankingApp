package org.sid.compteoperationservice.services;

import org.sid.compteoperationservice.entities.Compte;
import org.sid.compteoperationservice.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface CompteOperationService {
    public Compte ajouterCompte(double solde, String type, Long client_id);
    public void versement(double montant, Long compte_id);
    public void retrait(double montant, Long compte_id);
    public void virement(double montant, Long compte_src_id, Long compte_dest_id);
    public Page<Operation> getOperationsByCompte(Long compte_id, Pageable pageable);
    public Compte getCompteWithClient(Long compte_id);
    public Compte activerCompte(Long compte_id);
    public Compte suspenderCompte(Long compte_id);
}
