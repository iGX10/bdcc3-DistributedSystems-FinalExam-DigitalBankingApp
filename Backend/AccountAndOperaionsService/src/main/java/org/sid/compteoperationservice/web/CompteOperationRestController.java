package org.sid.compteoperationservice.web;

import org.sid.compteoperationservice.entities.Compte;
import org.sid.compteoperationservice.entities.Operation;
import org.sid.compteoperationservice.services.CompteOperationService;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CompteOperationRestController {

    private CompteOperationService compteOperationService;

    public CompteOperationRestController(CompteOperationService compteOperationService) {
        this.compteOperationService = compteOperationService;
    }


    @GetMapping(path = "/comptes/add")
    public Compte addCompte(@RequestParam double solde, @RequestParam String type, @RequestParam Long client_id) {
        return compteOperationService.ajouterCompte(solde, type, client_id);
    }

    @GetMapping(path = "/operations/virsement")
    public void virsement(@RequestParam double montant, @RequestParam Long compte_id) {
        compteOperationService.versement(montant, compte_id);
    }

    @GetMapping(path = "/operations/retrait")
    public void retrait(@RequestParam double montant, @RequestParam Long compte_id) {
        compteOperationService.retrait(montant, compte_id);
    }

    @GetMapping(path = "/operations/virement")
    public void virement(@RequestParam double montant, @RequestParam Long compte_src_id, @RequestParam Long compte_dest_id) {
        compteOperationService.virement(montant, compte_src_id, compte_dest_id);
    }

    @GetMapping(path = "/operations/compte/{compte_id}")
    public Collection<Operation> getOperationsByCompte(@PathVariable Long compte_id) {
        return compteOperationService.getOperationsByCompte(compte_id);
    }

    @GetMapping(path = "/comptes/client-detail/{compte_id}")
    public Compte getCompteWithClient(@PathVariable Long compte_id) {
        return compteOperationService.getCompteWithClient(compte_id);
    }

    @GetMapping(path = "/comptes/activate/{compte_id}")
    public Compte activerCompte(@PathVariable Long compte_id) {
        return compteOperationService.activerCompte(compte_id);
    }

    @GetMapping(path = "/comptes/suspend/{compte_id}")
    public Compte suspenderCompte(Long compte_id) {
        return compteOperationService.suspenderCompte(compte_id);
    }
}
