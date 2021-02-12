package org.sid.compteoperationservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.sid.compteoperationservice.models.Client;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Compte {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double solde;
    private Date date_creation;
    private String type;
    private String etat;
    @Transient
    private Client client;
    private Long client_id;
    @OneToMany(mappedBy = "compte")
    private Collection<Operation> operations;
}
