package com.example.projet_quebecbanque.repository;

import com.example.projet_quebecbanque.entities.Compte;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IBanqueRepository {
    Compte consulterCompte(Integer id);
    void verser(Integer id, double montant);

//    public void retirer(String codeCpte, double montant);
//    public void virement(String codeCpte1, String codeCpte2, double montant);
//    public Page<Operation> listOperation(String codeCpte, int page, int size);
}
