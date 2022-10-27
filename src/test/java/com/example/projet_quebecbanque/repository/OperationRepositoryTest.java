package com.example.projet_quebecbanque.repository;

import com.example.projet_quebecbanque.entities.Compte;
import com.example.projet_quebecbanque.entities.Operation;
import com.example.projet_quebecbanque.entities.Retrait;
import com.example.projet_quebecbanque.entities.Versement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class OperationRepositoryTest {

    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private CompteRepository compteRepository;

    @Test
    public void testAddNewOperationVersement() {
        double montant = 155;
        Optional<Compte> compte = compteRepository.findById(1);
        Operation versement = null;
        if (compte.isPresent()) {
            versement = new Versement(new Date(), compte.get().getSolde()+ montant, compte.get());
            operationRepository.save(versement);

            compte.get().setSolde(compte.get().getSolde()+montant);
            compteRepository.save(compte.get());
        }
        Assertions.assertThat(versement).isNotNull();
        Assertions.assertThat(versement.getNumero()).isGreaterThan(0);

    }

    @Test
    public void testAddNewOperationRetrait() {
        double montant = 20;
        Optional<Compte> compte = compteRepository.findById(1);
        Operation retrait = null;
        if (compte.isPresent()) {
            retrait = new Retrait(new Date(),compte.get().getSolde()- montant, compte.get());
            operationRepository.save(retrait);

            compte.get().setSolde(compte.get().getSolde()-montant);
            compteRepository.save(compte.get());
        }
        Assertions.assertThat(retrait).isNotNull();
        Assertions.assertThat(retrait.getNumero()).isGreaterThan(0);
    }
}
