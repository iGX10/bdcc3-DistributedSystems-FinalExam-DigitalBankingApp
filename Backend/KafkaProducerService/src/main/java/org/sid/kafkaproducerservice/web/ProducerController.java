package org.sid.kafkaproducerservice.web;

import org.sid.kafkaproducerservice.feign.OperationRestClient;
import org.sid.kafkaproducerservice.models.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ProducerController {
    @Autowired
    KafkaTemplate<String, Operation> kafkaTemplate;
    @Autowired
    OperationRestClient operationRestClient;

    int counter = 0;

    @GetMapping(path = "/publish/operations")
    public String sendOperations(@RequestParam String topic) {
        PagedModel<Operation> operations = operationRestClient.getOperations();
        ++counter;
        operations.getContent().forEach(operation -> {
            kafkaTemplate.send(topic, String.valueOf(counter), operation);
        });
        return "Operations sent ...";
    }
}
