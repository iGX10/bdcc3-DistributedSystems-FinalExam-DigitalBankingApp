package org.sid.kafkaproducerservice.feign;

import org.sid.kafkaproducerservice.models.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@FeignClient(name = "COMPTE-OPERATION-SERICE")
public interface OperationRestClient {
    @GetMapping(path = "/operations")
    public Collection<Operation> getOperations();
}
