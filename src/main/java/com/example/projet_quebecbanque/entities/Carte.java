package com.example.projet_quebecbanque.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carte")
    private Integer idCarte;
    @Column(name = "no_carte")
    private Long noCarte;
    @Column(length = 10, name = "date_expiration")
    private String dateExpiration;

    @JoinColumn(name = "id_client")
    private Integer idClient;
    @JoinColumn(name = "id_compte")
    private Integer idCompte;
//    @ManyToOne
//    @JoinColumn(name = "id_client")
//    private Client client;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_compte")
//    @MapsId
//    private Compte idCompte;

    private double total;

    public Carte(Long noCarte, String dateExpiration, int idClient, int idCompte, double total) {
        this.noCarte = noCarte;
        this.dateExpiration = dateExpiration;
        this.idClient = idClient;
        this.idCompte = idCompte;
        this.total = total;
    }
//    public Carte(Long noCarte, String dateExpiration, Client client, Compte idCompte, double total) {
//        this.noCarte = noCarte;
//        this.dateExpiration = dateExpiration;
//        this.client = client;
//        this.idCompte = idCompte;
//        this.total = total;
//    }


}
