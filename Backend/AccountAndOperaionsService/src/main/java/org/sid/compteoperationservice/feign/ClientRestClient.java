package org.sid.compteoperationservice.feign;

import org.sid.compteoperationservice.models.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CLIENT-SERVICE")
public interface ClientRestClient {
    @GetMapping(path = "/clients/{id}")
    Client getClientById(@PathVariable Long id);

    @GetMapping(path = "/clients/search/findClientByEmail")
    Client getClientByEmail(@RequestParam String email);
}
