package org.sid.kafkaproducerservice.models;

import lombok.Data;

import java.util.Date;

@Data
public class Operation {
    private Long id;
    private Date date;
    private double montant;
    private String type;
    private Long compte_id;
}
