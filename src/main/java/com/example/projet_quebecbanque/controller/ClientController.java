package com.example.projet_quebecbanque.controller;

import com.example.projet_quebecbanque.controller.exception.ClientNotFoundException;
import com.example.projet_quebecbanque.entities.Client;
import com.example.projet_quebecbanque.entities.Compte;
import com.example.projet_quebecbanque.entities.User;
import com.example.projet_quebecbanque.service.ClientService;
import com.example.projet_quebecbanque.service.CompteService;
import com.example.projet_quebecbanque.service.UserService;
import com.example.projet_quebecbanque.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;
    @Autowired
    private CompteService compteService;


    //    @GetMapping("/clients")
//    public List<Client> list() {
//        return clientService.listAll();
//    }


    @GetMapping("/addClient")
    public String sendForm(Client client, Model model) {
        model.addAttribute("pageTitle", "Ajouter un nouveau client");
        return "addClient";
    }

    @PostMapping("/clients/save")
    public String processForm(Client client, RedirectAttributes ra) {
        clientService.save(client);
        System.out.println(client);

        PasswordUtils passwordUtils = new PasswordUtils();
        passwordUtils.hash(client.getMotDePasse());
        User userHash = new User(client.getEmail(), passwordUtils.getHashpassword(), passwordUtils.getSalt());
        System.out.println(userHash);
        userService.save(userHash);


        ra.addFlashAttribute("message", "Le Client a bien été sauvegardé ");
        System.out.println(client);
        return "showMessageClient";
    }

    @GetMapping("/clients")
    public String showClientlist(Model model) {
        List<Client> listDeClients = clientService.listAll();
        model.addAttribute("listDeClients", listDeClients);
        return "gestionAdmin";
    }

    @GetMapping("/clients/delete/{id}")
    public String  get(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            clientService.delete(id);
            ra.addFlashAttribute("message", "Le Client a bien été supprimer ");
            return "redirect:/clients";
        } catch (ClientNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/clients";
        }

    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> get(@PathVariable Integer id) {
        try {
            Client client = clientService.get(id);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (NoSuchElementException | ClientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clients/edit/{id}")
    public String editClient(@PathVariable Integer id , Model model, RedirectAttributes ra) {
        try {
            Client clientCourant = clientService.get(id);
            model.addAttribute("clientCourant", clientCourant);
            model.addAttribute("pageTitle", "Edit Client (ID: " + id + ")");
            return  "gestionAdmin";
        } catch (ClientNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/gestionAdmin";
        }
    }

    @GetMapping("/portailClient")
    public String showPortailClient(Model model) {
        List<Compte> listComptesParClient = compteService.listAllComptetByIdClient(2);
        model.addAttribute("listComptesParClient", listComptesParClient);
        User user = new User();
        model.addAttribute("user", user);
        return "portailClient";
    }

    @GetMapping("/client/update/{id}")
    public String updateClient(@PathVariable Integer id ,Client client, Model model, RedirectAttributes ra) {
        System.out.println("id du client : "+ id);
        Client clientTrouver = client;
        clientTrouver.setIdClient(id);
        System.out.println(client);
        clientService.updateClient(client);

        ra.addFlashAttribute("message",
                "Le client avec le id : " + client.getIdClient() + " a été modifié avec succès");
        List<Client> listDeClients = clientService.listAll();
        model.addAttribute("listDeClients", listDeClients);
        return "redirect:/gestionAdmin";
    }




}
