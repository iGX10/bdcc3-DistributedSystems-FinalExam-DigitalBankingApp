package org.sid.kafkaproducerservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Operation {
    private Long id;
    private Date date;
    private double montant;
    private String type;
}
