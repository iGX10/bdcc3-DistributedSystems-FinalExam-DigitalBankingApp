package org.sid.compteoperationservice.services;

import org.sid.compteoperationservice.entities.Compte;
import org.sid.compteoperationservice.entities.Operation;
import org.sid.compteoperationservice.feign.ClientRestClient;
import org.sid.compteoperationservice.models.Client;
import org.sid.compteoperationservice.repositories.CompteRepository;
import org.sid.compteoperationservice.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class CompteOperationServiceImpl implements CompteOperationService{

    private CompteRepository compteRepository;
    private OperationRepository operationRepository;
    private ClientRestClient clientRestClient;

    public CompteOperationServiceImpl(CompteRepository compteRepository, OperationRepository operationRepository, ClientRestClient clientRestClient) {
        this.compteRepository = compteRepository;
        this.operationRepository = operationRepository;
        this.clientRestClient = clientRestClient;
    }

    @Override
    public Compte ajouterCompte(double solde, String type, Long client_id) {
        Client client = clientRestClient.getClientById(client_id);
        Compte compte = new Compte(null, solde, new Date(), type, "ACTIVE", client, client.getId(), null);
        Compte newCompte = compteRepository.save(compte);
        return newCompte;
    }

    @Override
    public void versement(double montant, Long compte_id) {
        Compte compte = compteRepository.findById(compte_id).get();
        Operation operation = operationRepository.save(new Operation(null, new Date(), montant, "DEBIT", compte));
        compte.setSolde(compte.getSolde()+montant);
        compte.getOperations().add(operation);
        compteRepository.save(compte);
    }

    @Override
    public void retrait(double montant, Long compte_id) {
        Compte compte = compteRepository.findById(compte_id).get();
        Operation operation = operationRepository.save(new Operation(null, new Date(), montant, "CREDIT", compte));
        compte.setSolde(compte.getSolde()-montant);
        compte.getOperations().add(operation);
        compteRepository.save(compte);
    }

    @Override
    public void virement(double montant, Long compte_src_id, Long compte_dest_id) {
        Compte compte_src = compteRepository.findById(compte_src_id).get();
        Operation operation_credit = operationRepository.save(new Operation(null, new Date(), montant, "CREDIT", compte_src));
        compte_src.setSolde(compte_src.getSolde()-montant);
        compte_src.getOperations().add(operation_credit);
        compteRepository.save(compte_src);

        Compte compte_dest = compteRepository.findById(compte_dest_id).get();
        Operation operation_debit = operationRepository.save(new Operation(null, new Date(), montant, "DEBIT", compte_dest));
        compte_dest.setSolde(compte_dest.getSolde()+montant);
        compte_dest.getOperations().add(operation_debit);
        compteRepository.save(compte_dest);
    }

    @Override
    public Page<Operation> getOperationsByCompte(Long compte_id, Pageable pageable) {
        return operationRepository.findOperationsByCompteId(compte_id, pageable);
    }

    @Override
    public Compte getCompteWithClient(Long compte_id) {
        Compte compte = compteRepository.findById(compte_id).get();
        Client client = clientRestClient.getClientById(compte.getClient_id());
        compte.setClient(client);
        return compte;
    }

    @Override
    public Compte activerCompte(Long compte_id) {
        Compte compte = compteRepository.findById(compte_id).get();
        compte.setEtat("ACTIVE");
        return compteRepository.save(compte);
    }

    @Override
    public Compte suspenderCompte(Long compte_id) {
        Compte compte = compteRepository.findById(compte_id).get();
        compte.setEtat("SUSPENDED");
        return compteRepository.save(compte);
    }
}
