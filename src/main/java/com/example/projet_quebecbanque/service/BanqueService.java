package com.example.projet_quebecbanque.service;

import com.example.projet_quebecbanque.entities.Compte;
import com.example.projet_quebecbanque.entities.Versement;
import com.example.projet_quebecbanque.repository.CompteRepository;
import com.example.projet_quebecbanque.repository.IBanqueRepository;
import com.example.projet_quebecbanque.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
@Service
@Transactional
public class BanqueService implements IBanqueRepository {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private OperationRepository operationRepository;

    public Compte consulterCompte(Integer id) {
        Optional<Compte> optionalCompte = compteRepository.findById(id);
        Compte compte1;
        if (optionalCompte.isPresent()) {
            compte1 = optionalCompte.get();
        } else {
            throw new RuntimeException("Compte introuvable");
        }
        return compte1;
    }

    @Override
    public void verser(Integer id, double montant) {
        Compte cp = consulterCompte(id);
        Versement v = new Versement(new Date(), montant, cp);
        operationRepository.save(v);
        cp.setSolde(cp.getSolde() + montant);
        compteRepository.save(cp);

    }
}
