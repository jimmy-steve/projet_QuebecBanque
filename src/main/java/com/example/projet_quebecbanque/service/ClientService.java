package com.example.projet_quebecbanque.service;

import com.example.projet_quebecbanque.controller.exception.ClientNotFoundException;
import com.example.projet_quebecbanque.entities.Client;
import com.example.projet_quebecbanque.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Name : ClientService
 * agit comme une couche intermédiaire entre la couche
 * de persistance (repository) et la couche de contrôleur
 *
 * @author Francis Lafontaine
 * @version V1
 * @since 6/octobre/2022
 */
@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Trouve la liste des clients dans la bd.
     *
     * @return la liste des clients.
     */
    public List<Client> listAll() {
        return (List<Client>) clientRepository.findAll();
    }

    /**
     * Permet de d'enregistrer un client dans la bd
     *
     * @param client qui sera insérer
     */
    public void save(Client client) {
        clientRepository.save(client);
    }

    /**
     * Name : get
     * Permet d'aller chercher les information d'un client
     *
     * @param id du client a recherché
     * @return un objet de type client
     * @throws ClientNotFoundException si on ne trouve pas le client
     */
    public Client get(Integer id) throws ClientNotFoundException {
        Optional<Client> clientOption = clientRepository.findById(id);

        if (clientOption.isPresent()) {
            return clientOption.get();
        }
        throw new ClientNotFoundException("Aucun Client d'a été trouvé");
    }

    /**
     * Name : delete
     * Permet de deleter un client de la base de donnée
     *
     * @param id du client a suppriemr
     * @throws ClientNotFoundException si on ne trouve pas le client
     */
    public void delete(Integer id) throws ClientNotFoundException {
        Optional<Client> clientOption = clientRepository.findById(id);
        if (clientOption.isPresent()) {
            clientRepository.deleteById(id);
        } else {
            throw new ClientNotFoundException("Aucun Client d'a été trouvé");
        }
    }

    /**
     * Name : updateClient
     * Permet de mettre a jour lke sinformation d'un client
     *
     * @param client qui sera mise a jour
     */
    @Transactional
    public void updateClient(Client client) {
        clientRepository
                .findById(client.getIdClient()) // returns Optional<User>
                .ifPresent(client1 -> {
                    client1.setNom(client.getNom());
                    client1.setPrenom(client.getPrenom());
                    client1.setAdresse(client.getAdresse());
                    client1.setJourNaissance(client.getJourNaissance());
                    client1.setMoisNaissance(client.getMoisNaissance());
                    client1.setAnneeNaissance(client.getAnneeNaissance());
                    client1.setPays(client.getPays());
                    client1.setVille(client.getVille());
                    client1.setTelephone(client.getTelephone());

                    clientRepository.save(client1);
                });
    }
}
