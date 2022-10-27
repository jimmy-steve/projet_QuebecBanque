package com.example.projet_quebecbanque.controller;

import com.example.projet_quebecbanque.controller.exception.CarteNotFoundException;
import com.example.projet_quebecbanque.entities.Carte;
import com.example.projet_quebecbanque.entities.Compte;
import com.example.projet_quebecbanque.service.CarteService;
import com.example.projet_quebecbanque.utils.Utilitaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CarteController {
    @Autowired
    private CarteService carteService;

    @GetMapping("/addCarte")
    public String sendForm(Carte carte, Model model) {
//        long noCarte = Utilitaire.creationsID();
        String noCarte = String.valueOf(Utilitaire.creationsID());
        noCarte = noCarte.substring(0, 4) + " " + noCarte.substring(4, 8) + " " + noCarte.substring(8, 12)
                + " " + noCarte.substring(12);

        String dateExpiration = Utilitaire.setDateExpiration();

        model.addAttribute("dateExpiration", dateExpiration);
        model.addAttribute("noCarte", noCarte);
        return "addCarte";
    }


    @GetMapping("/addCartePortail")
    public String sendFormPortail(Carte carte, Model model) {
//        long noCarte = Utilitaire.creationsID();
        String noCarte = String.valueOf(Utilitaire.creationsID());
        noCarte = noCarte.substring(0, 4) + " " + noCarte.substring(4, 8) + " " + noCarte.substring(8, 12)
                + " " + noCarte.substring(12);

        String dateExpiration = Utilitaire.setDateExpiration();

        model.addAttribute("dateExpiration", dateExpiration);
        model.addAttribute("noCarte", noCarte);
        return "portailClient";
    }

    @GetMapping("/addTransaction")
    public String sendFormTransactionPortail(Carte carte, Model model) {

        Carte transaction = new Carte();
        model.addAttribute("transaction", transaction);
        return "portailClient";
    }

    @GetMapping("/addCarte2")
    public String sendForm2(Carte carte, Model model) {
//        long noCarte = Utilitaire.creationsID();
        String noCarte = String.valueOf(Utilitaire.creationsID());
        noCarte = noCarte.substring(0, 4) + " " + noCarte.substring(4, 8) + " " + noCarte.substring(8, 12)
                + " " + noCarte.substring(12);

        String dateExpiration = Utilitaire.setDateExpiration();

        model.addAttribute("dateExpiration", dateExpiration);
        model.addAttribute("noCarte", noCarte);
        return "addCarte2";
    }

    @GetMapping("/GenererNoCarte")
    public String genererNoCarte(Model model) {
        String noCarte = String.valueOf(Utilitaire.creationsID());
        noCarte = noCarte.substring(0, 4);
        String noCarteStr = String.valueOf(noCarte);

        noCarteStr = noCarteStr.substring(1, 4);
        System.out.println(noCarteStr);
        model.addAttribute("noCarte", noCarteStr);

        return "redirect:/portailClient";
    }

    /**
     * méyhode post qui focntionne trèes bien reste a modifier la porter
     *
     * @param carte la carte a sauvegarder
     * @param ra    le message de confirmation
     * @return vers la page désiré
     */
    @PostMapping("/carte/save")
    public String saveCarte(Model model, Carte carte, RedirectAttributes ra) {
        carteService.save(carte);
        ra.addFlashAttribute("message", "La carte a bien été activé");
        model.addAttribute("carte", carte);
        return "showMessageCarte";
    }


    @GetMapping("/cartes")
    public String showlistCartes(Model model) {
        List<Carte> listCartesClients = carteService.listAllCarte();
        model.addAttribute("listCartesClients", listCartesClients);
        return "gestionAdmin";
    }



    @GetMapping("/cartes/edit/{id}")
    public String editCarte(@PathVariable Integer id , Model model, RedirectAttributes ra) {
        try {
            Carte carteCourant = (Carte) carteService.getCarte(id);
            model.addAttribute("carteCourant", carteCourant);
            model.addAttribute("pageTitle", "Edit Carte (ID: " + id + ")");
            return  "gestionAdmin";
        } catch (CarteNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/gestionAdmin";
        }
    }


    @GetMapping("/cartes/delete/{id}")
    public String  deleteCarte(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            carteService.delete(id);
            ra.addFlashAttribute("message", "La carte a bien été supprimer ");
            return "redirect:/gestionAdmin";
        } catch (CarteNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/gestionAdmin";
        }

    }


    @GetMapping("/carte/update/{id}")
    public String updateCarte(@PathVariable Integer id , Carte carte, Model model, RedirectAttributes ra) {
        System.out.println("id de la carte : "+ id);
        Carte carteTrouver = carte;
        carteTrouver.setIdCarte(id);
        System.out.println(carteTrouver);
        carteService.updateCarte(carteTrouver);

        ra.addFlashAttribute("message",
                "La carte avec le id : " + carteTrouver.getIdCarte() + " a été modifié avec succès");
        List<Carte> listCartesClients = carteService.listAllCarte();
        model.addAttribute("listCartesClients", listCartesClients);
        return "redirect:/gestionAdmin";
    }


}
