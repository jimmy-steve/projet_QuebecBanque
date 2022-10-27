package com.example.projet_quebecbanque.controller;

import com.example.projet_quebecbanque.controller.exception.CompteNotFoundException;
import com.example.projet_quebecbanque.controller.exception.UserNotFoundException;
import com.example.projet_quebecbanque.entities.*;
import com.example.projet_quebecbanque.repository.CompteRepository;
import com.example.projet_quebecbanque.repository.OperationRepository;
import com.example.projet_quebecbanque.service.ClientService;
import com.example.projet_quebecbanque.service.CompteService;
import com.example.projet_quebecbanque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class CompteController {
    @Autowired
    private CompteService compteService;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @GetMapping("/addCompte")
    public String sendForm(Model model) {
        Compte compte = new Compte();
        model.addAttribute("idClient", 2);
        model.addAttribute("compte", compte);
        return "addCompte";
    }

    @GetMapping("/addComptePortail/{id}")
    public String sendFormPortailClient(@PathVariable Integer id, Model model) {
        try {
            User user1 = userService.get(id);
            Compte compte = new Compte();
            model.addAttribute("idClient", user1.getIdClient());
//                        model.addAttribute("idClient", 2);
            model.addAttribute("compte", compte);
            return "portailClient";
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return "redirect:/portailClient";
        }
    }

    @PostMapping("/addCompte")
    public String processForm(Compte compte, RedirectAttributes ra) {
        compte.setDateCreation(new Date());
        compteService.save(compte);
        System.out.println(compte);
        ra.addFlashAttribute("message", "The user has been save successfully");
        return "redirect:/portailClient";
    }

    @GetMapping("/portaillClient")
    public String showlistComptesPortailClient(Model model) {
        List<Compte> listComptesParClient = compteService.listAllComptetByIdClient(2);
        model.addAttribute("listComptesParClient", listComptesParClient);
        return "portailClient";
    }

    @GetMapping("/comptes")
    public String showlistComptes(Model model) {
        List<Compte> listComptesClients = compteService.listAllComptes();
        model.addAttribute("listComptesClients", listComptesClients);
        return "gestionAdmin";
    }


    @GetMapping("/comptes/{id}")
    public ResponseEntity<Compte> get(@PathVariable Integer id) {
        try {
            Compte compte = compteService.getCompte(id);
            return new ResponseEntity<>(compte, HttpStatus.OK);
        } catch (NoSuchElementException | CompteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/comptes/edit/{id}")
    public String editCompte(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        try {
            Compte compteCourant = compteService.getCompte(id);
            model.addAttribute("compteCourant", compteCourant);
            model.addAttribute("pageTitle", "Edit Compte (ID: " + id + ")");
            return "gestionAdmin";
        } catch (CompteNotFoundException ex) {
            ra.addFlashAttribute("message", ex.getMessage());
            return "redirect:/gestionAdmin";
        }
    }


    @GetMapping("/comptes/delete/{id}")
    public String deleteCompte(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            compteService.deleteCompte(id);
            ra.addFlashAttribute("message", "Le Compte a bien été supprimer ");
            return "redirect:/gestionAdmin";
        } catch (CompteNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/gestionAdmin";
        }

    }

    @GetMapping("/compte/update/{id}")
    public String updateCompte(@PathVariable Integer id, Compte compte, Model model, RedirectAttributes ra) {
        System.out.println("id du compte : " + id);
        Compte compteTrouver = compte;
        compteTrouver.setId(id);
        System.out.println(compteTrouver);
        compteService.updateCompte(compteTrouver);

        ra.addFlashAttribute("message",
                "Le compte avec le id : " + compteTrouver.getId() + " a été modifié avec succès");
        List<Compte> listComptesClients = compteService.listAllComptes();
        model.addAttribute("listComptesClients", listComptesClients);
        return "gestionAdmin";
    }

    @GetMapping("/comptes/transact/{id}")
    public String transactCompte(@PathVariable Integer id, Compte compte, Model model, RedirectAttributes ra) {
        System.out.println("id du compte : " + id);

        double montant = 155;
        Optional<Compte> compteTrouver = compteRepository.findById(1);
        Operation versement = null;
        if (compteTrouver.isPresent()) {
            versement = new Versement(new Date(), compteTrouver.get().getSolde() + montant, compteTrouver.get());
            operationRepository.save(versement);

            compteTrouver.get().setSolde(compteTrouver.get().getSolde() + montant);
            compteRepository.save(compteTrouver.get());


            compteService.transact(compteTrouver.get());

            ra.addFlashAttribute("message",
                    "Le compte avec le id : " + compteTrouver.get().getId() + " a fait une transaction avec succès");
            return "portailClient";
        }

        return null;
    }
}
