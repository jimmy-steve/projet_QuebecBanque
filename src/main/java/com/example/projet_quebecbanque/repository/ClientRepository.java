package com.example.projet_quebecbanque.repository;

import com.example.projet_quebecbanque.entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
}
