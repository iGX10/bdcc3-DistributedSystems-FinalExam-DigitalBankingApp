package org.sid.kafkaproducerservice.feign;

import org.sid.kafkaproducerservice.models.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "COMPTE-OPERATION-SERVICE")
public interface OperationRestClient {
    @GetMapping(path = "/operations")
    public PagedModel<Operation> getOperations();
}
