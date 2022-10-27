package com.example.projet_quebecbanque.repository;

import com.example.projet_quebecbanque.entities.Client;
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
public class ClientRepositoryTest {
    @Autowired
    ClientRepository clientRepository;

    @Test
    public void testAddNewClient(){
        Client client = new Client("Salut", "Bonjour");
        Client savedClient = clientRepository.save(client);
        Assertions.assertThat(savedClient).isNotNull();
        Assertions.assertThat(savedClient.getIdClient()).isGreaterThan(0);

    }

    @Test
    public void testAddNewClientComplet(){
        Client client = new Client("Loiselle", "Melissa", 1,1,1988,
                "981 rue notre-dame", "canada", "Repentigny", "(514)582-2459",
                "1234", "email@gmail.com");
        Client savedClient = clientRepository.save(client);
        Assertions.assertThat(savedClient).isNotNull();
        Assertions.assertThat(savedClient.getIdClient()).isGreaterThan(0);
    }

    @Test
    public void testListAllClient(){
        Iterable<Client> listeClients = clientRepository.findAll();
        Assertions.assertThat(listeClients).hasSizeGreaterThan(0);

        for (Client tmp : listeClients
        ) {
            System.out.println(tmp);
        }
    }

    @Test
    public  void testUpdateClient(){
        Integer id =1;
        Optional<Client> optionalClient = clientRepository.findById(id);

        Client client = optionalClient.get();
        client.setPays("Cuba");
        clientRepository.save(client);

        Client updatedClient = clientRepository.findById(id).get();
        Assertions.assertThat(updatedClient.getPays()).isEqualTo("Cuba");
    }

    @Test
    public  void testGetClient(){
        Integer id =1;
        Optional<Client> optionalClient= clientRepository.findById(id);
        Assertions.assertThat(optionalClient).isPresent();
        System.out.println(optionalClient.get());
    }

    @Test
    public void testDelete(){
        Integer id =1;
        clientRepository.deleteById(id);
        Optional<Client> optionalClient = clientRepository.findById(id);
        Assertions.assertThat(optionalClient).isNotPresent();

    }
}
