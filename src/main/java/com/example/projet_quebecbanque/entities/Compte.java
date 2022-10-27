package com.example.projet_quebecbanque.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compte")
    private Integer id;
    @Column(length = 45)
    private String type;
    private Date dateCreation;
    private double solde;

//    @ManyToOne
    @JoinColumn(name = "id_client")
    private int idClient;
//    private Client client;

//    public Compte(String type, Client client) {
//        this.type = type;
//        this.client = client;
//    }

    @OneToMany(mappedBy="compte")
    private Collection<Operation> operations;

    public Compte(String type, int idClient) {
        this.type = type;
        this.idClient = idClient;
    }

    public Compte(String type, Date dateCreation, double solde, int idClient) {
        this.type = type;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.idClient = idClient;
    }

    public Compte(int id) {
        this.id = id;
    }
}
