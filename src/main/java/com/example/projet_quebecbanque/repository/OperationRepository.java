package com.example.projet_quebecbanque.repository;

import com.example.projet_quebecbanque.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
