package com.example.projet_quebecbanque.repository;

import com.example.projet_quebecbanque.entities.Compte;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompteRepository extends CrudRepository<Compte, Integer> {

    @Query(
            value = "select * from compte where id_client = 2",
            nativeQuery = true)
    List<Compte> listAllCompteByIdClient(Integer id);
}
