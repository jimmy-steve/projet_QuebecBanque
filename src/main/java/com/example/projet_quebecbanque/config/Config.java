package com.example.projet_quebecbanque.config;

import com.example.projet_quebecbanque.entities.*;
import com.example.projet_quebecbanque.repository.*;
import com.example.projet_quebecbanque.utils.PasswordUtils;
import com.example.projet_quebecbanque.utils.Utilitaire;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Name : Config
 * Permet de préconfigurer l'application lors de la lancer du programme
 *
 * @author Francis Lafontaine
 * @version V1
 * @since 6 octobre/2022
 */
@Configuration
public class Config {

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository,
                                        CompteRepository compteRepository,
                                        CarteRepository carteRepository,
                                        OperationRepository operationRepository,
                                        UserRepository userRepository) {
        return args -> {

//            Client client1 = new Client("Loiselle", "Melissa", 1, 1, 1988,
//                    "981 rue notre-dame", "canada", "Repentigny", "(514)582-2459",
//                    "1234", "email@gmail.com");
//            Client client2 = new Client("Lefebre", "Manon", 12, 12, 1968,
//                    "95 de l'amitié", "canada", "Chicoutimi", "(438)654-5435",
//                    "0000", "lefebre@gmail.com");
//
//            Client client3 = new Client("Barrest", "Gislain", 30, 1, 1995,
//                    "76 rue de la montagne", "canada", "Montréal", "(514)654-5005",
//                    "69", "barrest@gmail.com");
//
//            Client client4 = new Client("Roy", "Patrick", 25, 12, 2001,
//                    "15 rue de l'église", "canada", "Québec", "(438)555-5556",
//                    "1234", "patroy@gmail.com");
//
//
//            clientRepository.saveAll(List.of(client1, client2, client3, client4));
//
//
//            PasswordUtils passwordUtils = new PasswordUtils();
//            passwordUtils.hash(client1.getMotDePasse());
//            User userHash1 = new User(client1.getEmail(), passwordUtils.getHashpassword(), passwordUtils.getSalt(),
//                    client1.getIdClient());
//
//            passwordUtils.hash(client2.getMotDePasse());
//            User userHash2 = new User(client2.getEmail(), passwordUtils.getHashpassword(), passwordUtils.getSalt(),
//                    client2.getIdClient());
//
//            passwordUtils.hash(client3.getMotDePasse());
//            User userHash3 = new User(client3.getEmail(), passwordUtils.getHashpassword(), passwordUtils.getSalt(),
//                    client3.getIdClient());
//
//            passwordUtils.hash(client4.getMotDePasse());
//            User userHash4 = new User(client4.getEmail(), passwordUtils.getHashpassword(), passwordUtils.getSalt(),
//                    client4.getIdClient());
//
//
//            userRepository.saveAll(List.of(userHash1, userHash2, userHash3, userHash4));
//
//
//            Compte compte1 = new Compte("epargne", new Date(), 0, client2.getIdClient());
//            Compte compte2 = new Compte("cheque", new Date(), 0, client2.getIdClient());
//            Compte compte3 = new Compte("epargne", new Date(), 0, client3.getIdClient());
//            Compte compte4 = new Compte("cheque", new Date(), 0, client4.getIdClient());
//
//
//            compteRepository.saveAll(List.of(compte1, compte2, compte3, compte4));
//
//
//            Carte carte1 = new Carte(Utilitaire.creationsID(), Utilitaire.setDateExpiration(),
//                    client2.getIdClient(), compte2.getId(), 2000.00);
//            Carte carte2 = new Carte(Utilitaire.creationsID(), Utilitaire.setDateExpiration(),
//                    client3.getIdClient(), compte3.getId(), 75.00);
//            Carte carte3 = new Carte(Utilitaire.creationsID(), Utilitaire.setDateExpiration(),
//                    client4.getIdClient(), compte4.getId(), 200.00);
//
//            carteRepository.saveAll(List.of(carte1, carte2, carte3));
//
//
//            double montant = 155;
//            Optional<Compte> compte = compteRepository.findById(compte1.getId());
//            Operation versement = null;
//            if (compte.isPresent()) {
//                versement = new Versement(new Date(), compte.get().getSolde() + montant, compte.get());
//                operationRepository.save(versement);
//
//                compte.get().setSolde(compte.get().getSolde() + montant);
//                compteRepository.save(compte.get());
//            }
//
//
//            double montant2 = 20;
//            Optional<Compte> compteTrouver = compteRepository.findById(1);
//            Operation retrait = null;
//            if (compteTrouver.isPresent()) {
//                retrait = new Retrait(new Date(), compteTrouver.get().getSolde() - montant2,
//                        compteTrouver.get());
//                operationRepository.save(retrait);
//
//                compteTrouver.get().setSolde(compteTrouver.get().getSolde() - montant2);
//                compteRepository.save(compteTrouver.get());
//            }
//
//
        };


    }
}
