package com.example.projet_quebecbanque.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long idUser;
    private String username;
    @Column(name = "hash_password")
    private String hashPassword;
    private String salt;
    @Column(name = "is_admin")
    private  boolean isAdmin;
    private Integer idClient;



    public User(long idUser, String username) {
        this.idUser = idUser;
        this.username = username;
    }

    public User(String username, String hashPassword) {
        this.username = username;
        this.hashPassword = hashPassword;
    }

    public User(long idUser, String username, String hashPassword) {
        this.idUser = idUser;
        this.username = username;
        this.hashPassword = hashPassword;
    }

    public User(String username, String hashPassword, String salt, boolean isAdmin) {
        this.username = username;
        this.hashPassword = hashPassword;
        this.salt = salt;
        this.isAdmin = isAdmin;
    }

    public User(String username, String hashPassword, boolean isAdmin) {
        this.username = username;
        this.hashPassword = hashPassword;
        this.isAdmin = isAdmin;
    }

    public User(String username, String hashPassword, String salt) {
        this.username = username;
        this.hashPassword = hashPassword;
        this.salt = salt;
    }

    public User(String username, String hashPassword, String salt, Integer idClient) {
        this.username = username;
        this.hashPassword = hashPassword;
        this.salt = salt;
        this.idClient = idClient;
    }
}
