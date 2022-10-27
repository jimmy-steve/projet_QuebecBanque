package com.example.projet_quebecbanque.repository;

import com.example.projet_quebecbanque.entities.Carte;
import com.example.projet_quebecbanque.entities.Client;
import com.example.projet_quebecbanque.entities.Compte;
import com.example.projet_quebecbanque.utils.Utilitaire;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CarteRepositoryTest {

    @Autowired
    private CarteRepository carteRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CompteRepository compteRepository;

    @Test
    public void testAddNewCarte() {
//
//        Client client = new Client("Test3", "test3");
//        Client clientSaved = clientRepository.save(client);
//        Assertions.assertThat(clientSaved).isNotNull();
//        Assertions.assertThat(clientSaved.getIdClient()).isGreaterThan(0);
//
//        Compte compte = new Compte("epargneTest3", clientSaved);
//        Compte savedCompte = compteRepository.save(compte);
//        Assertions.assertThat(savedCompte).isNotNull();
//        Assertions.assertThat(savedCompte.getId()).isGreaterThan(0);

//
//        long nouveauId = Utilitaire.creationsID();
//
//        String NumerocCompte = String.valueOf(nouveauId);
//        String dateExp = Utilitaire.setDateExpiration();
//        Carte carte = new Carte(nouveauId, dateExp, clientSaved, savedCompte, 2000.00);
//
//
//        // -------------------------------------peut etre utiliser pour affichage
//        String nombre1 = NumerocCompte.substring(0, 4);
//        String nombre2 = NumerocCompte.substring(5, 9);
//        String nombre3 = NumerocCompte.substring(10, 14);
//        String nombre4 = NumerocCompte.substring(15);
//        long compteSubString = Long.parseLong(nombre1 + nombre2 + nombre3 + nombre4);
//        System.out.println(nombre1 + nombre2 + nombre3 + nombre4);
//        //-------------------------------------------------------------------
//
//        Carte savedCarte = carteRepository.save(carte);
//        Assertions.assertThat(savedCarte).isNotNull();
//        Assertions.assertThat(savedCarte.getClient().getIdClient()).isGreaterThan(0);
    }

    @Test
    public void testListAllCarte() {
        Iterable<Carte> listeCartes = carteRepository.findAll();
        Assertions.assertThat(listeCartes).hasSizeGreaterThan(0);

        for (Carte tmp : listeCartes
        ) {
            System.out.println(tmp);
        }
    }

    @Test
    public void testUpdateCarte() {
        Integer id = 2;
        Optional<Carte> optionalCarte = carteRepository.findById(id);

        Carte carte = optionalCarte.get();
        carte.setTotal(20);
        carteRepository.save(carte);

        Carte updatedCarte = carteRepository.findById(id).get();
        Assertions.assertThat(updatedCarte.getTotal()).isEqualTo(20);
    }

    @Test
    public void testGetCarte() {
        Integer id = 2;
        Optional<Carte> optionalCarte = carteRepository.findById(id);
        Assertions.assertThat(optionalCarte).isPresent();
        System.out.println(optionalCarte.get());
    }

    @Test
    public void testDelete() {
        Integer id = 3;
        carteRepository.deleteById(id);
        Optional<Carte> optionalCarte = carteRepository.findById(id);
        Assertions.assertThat(optionalCarte).isNotPresent();

    }
}
