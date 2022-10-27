package com.example.projet_quebecbanque.service;

import com.example.projet_quebecbanque.controller.exception.CarteNotFoundException;
import com.example.projet_quebecbanque.entities.Carte;
import com.example.projet_quebecbanque.repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Name : CarteService
 * agit comme une couche intermédiaire entre la couche
 * de persistance (repository) et la couche de contrôleur
 *
 * @author Francis Lafontaine
 * @version V1
 * @since 6 octobre 2002
 */
@Service
@Transactional
public class CarteService {
    @Autowired
    private CarteRepository carteRepository;

    /**
     * Trouve la liste des cartes dans la bd.
     *
     * @return la liste des cartes.
     */
    public List<Carte> listAllCarte() {
        return (List<Carte>) carteRepository.findAll();
    }

    /**
     * Permet de d'enregistrer une carte dans la bd
     *
     * @param carte qui sera insérer
     */
    public void save(Carte carte) {
        carteRepository.save(carte);
    }

    /**
     * Name : getCarte
     * Permet d'aller chercher toute les informations d'ne carte client
     *
     * @param id de la carte client
     * @return un objet de type carte
     * @throws CarteNotFoundException si on ne trouve pas la carte
     */
    public Object getCarte(Integer id) throws CarteNotFoundException {
        Optional<Carte> carteOptional = carteRepository.findById(id);

        if (carteOptional.isPresent()) {
            return carteOptional.get();
        }
        return new CarteNotFoundException("Aucune carte n'a été trouvé");
    }

    /**
     * Name : delete
     * Permet de deleter une carte dans la base de donnée
     *
     * @param id de la carte a supprimer
     * @throws CarteNotFoundException si on ne trouve pas la carte
     */
    public void delete(Integer id) throws CarteNotFoundException {
        Optional<Carte> carteOptional = carteRepository.findById(id);

        if (carteOptional.isPresent()) {
            carteRepository.deleteById(id);
        } else {
            throw new CarteNotFoundException("Aucune carte n'a été trouvé");
        }
    }

    /**
     * Name : updateCarte
     * Permet de mettre a jour la carte d'un client surtout le total
     *
     * @param carte qui sera mise a jour
     */
    @Transactional
    public void updateCarte(Carte carte) {
        carteRepository
                .findById(carte.getIdCarte()) // returns Optional<User>
                .ifPresent(carte1 -> {
                    carte1.setTotal(carte.getTotal());
                    carteRepository.save(carte1);
                });
    }
}
