package com.example.projet_quebecbanque.repository;

import com.example.projet_quebecbanque.entities.Client;
import com.example.projet_quebecbanque.entities.Compte;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CompteRepositoryTest {

    @Autowired
    CompteRepository compteRepository;

    @Autowired
    ClientRepository clientRepository;

    @Test
    public void testAddNewCompte(){
//        Client client = new Client("Test", "test");
//        Client clientSaved = clientRepository.save(client);
//        Assertions.assertThat(clientSaved).isNotNull();
//        Assertions.assertThat(clientSaved.getIdClient()).isGreaterThan(0);
//
//
//        Compte compte = new Compte("epargneTest", client);
//        Compte savedCompte = compteRepository.save(compte);
//        Assertions.assertThat(savedCompte).isNotNull();
//        Assertions.assertThat(savedCompte.getId()).isGreaterThan(0);
    }

    @Test
    public  void testUpdateCompte(){
        Integer id =1;
        Optional<Compte> optionalCompte = compteRepository.findById(id);

        Compte compte = optionalCompte.get();
        compte.setType("cheque");
        compteRepository.save(compte);

        Compte updatedCompte = compteRepository.findById(id).get();
        Assertions.assertThat(updatedCompte.getType()).isEqualTo("cheque");
    }

    @Test
    public  void testGetCompte(){
        Integer id =1;
        Optional<Compte> optionalCompte= compteRepository.findById(id);
        Assertions.assertThat(optionalCompte).isPresent();
        System.out.println(optionalCompte.get());
    }

    @Test
    public void testDelete(){
        Integer id =1;
        compteRepository.deleteById(id);
        Optional<Compte> optionalCompte = compteRepository.findById(id);
        Assertions.assertThat(optionalCompte).isNotPresent();

    }

    @Test
    public void testListAllCompte(){
        Iterable<Compte> listeComptes = compteRepository.findAll();
        Assertions.assertThat(listeComptes).hasSizeGreaterThan(0);

        for (Compte tmp : listeComptes
        ) {
            System.out.println(tmp);
        }
    }
}
