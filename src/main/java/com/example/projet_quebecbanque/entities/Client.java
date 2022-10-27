package com.example.projet_quebecbanque.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 45, name = "id_client")
    private Integer idClient;
    @Column(nullable = false, length = 45)
    private String nom;
    @Column(nullable = false, length = 45)
    private String prenom;
    @Column(length = 2, name = "jour_naissance")
    private int jourNaissance;
    @Column(length = 2, name = "mois_naissance")
    private int moisNaissance;
    @Column(length = 4, name = "annee_naissance")
    private int anneeNaissance;
    @Column(length = 60)
    private String adresse;
    @Column(length = 45)
    private String pays;
    @Column(length = 45)
    private String ville;
    @Column(length = 45)
    private String telephone;


    @Transient
    private String motDePasse;
    @Transient
    private String email;

//    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    @ToString.Exclude
//    @JsonIgnore
//    private List<Compte> comptes;

//    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    @ToString.Exclude
//    @JsonIgnore
//    private List<Carte> cartes;


    public Client(String nom, String prenom, int jourNaissance, int moisNaissance, int anneeNaissance,
                  String adresse, String pays, String ville, String telephone, String motDePasse, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.jourNaissance = jourNaissance;
        this.moisNaissance = moisNaissance;
        this.anneeNaissance = anneeNaissance;
        this.adresse = adresse;
        this.pays = pays;
        this.ville = ville;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
        this.email = email;
    }

    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

}
