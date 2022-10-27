package com.example.projet_quebecbanque.controller;

import com.example.projet_quebecbanque.entities.Carte;
import com.example.projet_quebecbanque.entities.Client;
import com.example.projet_quebecbanque.entities.Compte;
import com.example.projet_quebecbanque.entities.User;
import com.example.projet_quebecbanque.repository.IBanqueRepository;
import com.example.projet_quebecbanque.service.CarteService;
import com.example.projet_quebecbanque.service.ClientService;
import com.example.projet_quebecbanque.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BanqueController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CarteService carteService;

    @Autowired
    private CompteService compteService;

    @Autowired
    private IBanqueRepository iBanqueRepository;

    @GetMapping("/gestionAdmin")
    public String showAllList(Model model) {
        List<Client> listClients = clientService.listAll();
        List<Compte> listComptes = compteService.listAllComptes();
        List<Carte> listCartes  = carteService.listAllCarte();

        model.addAttribute("listClients", listClients);
        model.addAttribute("listComptes", listComptes);
        model.addAttribute("listCartes", listCartes);
        return "gestionAdmin";
    }

    @GetMapping("/newClient")
    public String addNewClient(Model model , RedirectAttributes ra){
        Client newClient = new Client();
        model.addAttribute("client", newClient);
        ra.addFlashAttribute("message", "newClient fonctionne");
        return "gestionAdmin";
    }

    @RequestMapping("/consulterCompte")
    public String consulter(Model model, Integer id,
                            @RequestParam(name="page", defaultValue="0") int page,
                            @RequestParam(name="size", defaultValue="5") int size){
        model.addAttribute("codeCompte", id);
        try{
            Compte cp = iBanqueRepository.consulterCompte(id);
            model.addAttribute("compte", cp);
//            Page<Operation> pageOperations= banqueMetier.listOperation(codeCompte, page, size);
//            model.addAttribute("listOperations", pageOperations.getContent());
//            int[] pages = new int [pageOperations.getTotalPages()];
//            model.addAttribute("pages",pages);
        } catch (Exception e) {
            model.addAttribute("exception",e);
        }
        return "comptes";
    }



}
