package com.example.projet_quebecbanque.service;

import com.example.projet_quebecbanque.controller.exception.CompteNotFoundException;
import com.example.projet_quebecbanque.entities.Compte;
import com.example.projet_quebecbanque.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Name : CompteService
 * Permet d'utilisé les opération du crud pour modifié aouté ou supprimer les comtpe
 * agit comme une couche intermédiaire entre la couche
 * de persistance (repository) et la couche de contrôleur
 *
 * @author Francis Lafontaine
 * @version V1
 * @since 6/octobre/2022
 */
@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;

    /**
     * Trouve la liste des comptes dans la bd.
     *
     * @return la liste des comptes.
     */
    public List<Compte> listAllComptes() {
        return (List<Compte>) compteRepository.findAll();
    }

    /**
     * Permet de d'enregistrer un compte dans la bd
     *
     * @param compte qui sera insérer
     */
    public void save(Compte compte) {
        compteRepository.save(compte);
    }

    /**
     * Name : getCompte
     * Permet de trouver un compte avec so id
     *
     * @param id du compte a rechercher
     * @return un objet de type comtpe
     * @throws CompteNotFoundException si le compte n'est pas trouvé
     */
    public Compte getCompte(Integer id) throws CompteNotFoundException {
        Optional<Compte> compteOptional = compteRepository.findById(id);
        if (compteOptional.isPresent()) {
            return compteOptional.get();
        } else {
            throw new CompteNotFoundException("Aucun compte n'a été trouvé");
        }
    }

    /**
     * Name : deleteCompte
     * Permet de supprimer un compte avec son id
     *
     * @param id du compte a supprimer
     * @throws CompteNotFoundException si on ne trouve pas le compte
     */
    public void deleteCompte(Integer id) throws CompteNotFoundException {
        Optional<Compte> compteOptional = compteRepository.findById(id);
        if (compteOptional.isPresent()) {
            compteRepository.deleteById(id);
        } else {
            throw new CompteNotFoundException("Aucun compte n'a été trouvé");
        }
    }

    /**
     * Name : updateCompte
     * Permet de mettgre a jour le type de compte
     *
     * @param compte qui sera mise a jour
     */
    @Transactional
    public void updateCompte(Compte compte) {
        compteRepository
                .findById(compte.getId()) // returns Optional<User>
                .ifPresent(compte1 -> {
                    compte1.setType(compte.getType());
                    compteRepository.save(compte1);
                });
    }


    public List<Compte> listAllComptetByIdClient(Integer id) {
        List<Compte> listeComptes;
        listeComptes = compteRepository.listAllCompteByIdClient(id);
        return listeComptes;
    }


    @Transactional
    public void transact(Compte compte) {
        compteRepository
                .findById(compte.getId()) // returns Optional<User>
                .ifPresent(compte1 -> {
                    compte1.setSolde(compte.getSolde());
                    compteRepository.save(compte1);
                });
    }

}
