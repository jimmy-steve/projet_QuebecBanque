package com.example.projet_quebecbanque;


import com.example.projet_quebecbanque.controller.exception.UserNotFoundException;
import com.example.projet_quebecbanque.entities.Compte;
import com.example.projet_quebecbanque.entities.Operation;
import com.example.projet_quebecbanque.entities.User;
import com.example.projet_quebecbanque.entities.Versement;
import com.example.projet_quebecbanque.service.UserService;
import com.example.projet_quebecbanque.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Name : mainController
 * Permet de gèrer la page web
 *
 * @author Francis Lafontaine
 * @version V1
 * @since 6/octobre/2022
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @PostMapping("/operation")
    public String operation(Versement versement, Double montant) {
        System.out.println(versement);
        Compte compte1 = new Compte();
        Operation versement1 = new Versement(new Date(), montant, compte1);
        System.out.println(versement1.toString());

        System.out.println("page operation");
        return "portailClient";
    }

    @GetMapping("/index")
    public String showHomePage() {
        System.out.println("main controller");
        return "index";
    }

    @GetMapping("/contact")
    public String showContact() {
        System.out.println("page contact ");
        return "contact";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        System.out.println("page loginForm ");
        return "loginForm";
    }

    @PostMapping("/login")
    public String logIn(User user, RedirectAttributes ra, Model model) {
        if (user == null) {
            return "loginForm";
        }
        PasswordUtils passwordUtils = new PasswordUtils();
        try {
            /*
            je saisi le mot de passe
             */
            String passwordAVerifier = user.getHashPassword();

            /*
            j'essai de trouver le user avec son username
             */
            user = userService.finByUsername(user.getUsername());

            /*
            si je le trouve j'y attribut c'est élément
             */
            String passwordHash = user.getHashPassword();
            String salt = user.getSalt();

            /*
            si tout est valide
             */
            boolean isValid = passwordUtils.verify(passwordAVerifier, passwordHash, salt);
            System.out.println(isValid);

            /*
            Si tout est valide on sera connecter
             */
            if (isValid) {

                /*
                La connection pour l'admin
                 */
                if (user.isAdmin()) {
                    String messageConfirmation = "Vous êtes maintenant connecter comme admin... ";
                    System.out.println(messageConfirmation);
                    ra.addFlashAttribute("message", messageConfirmation);
                    return "gestionAdmin";
                }


//                String noCarte = String.valueOf(Utilitaire.creationsID());
//                noCarte = noCarte.substring(0, 4) + " " + noCarte.substring(4, 8) + " " + noCarte.substring(8, 12)
//                        + " " + noCarte.substring(12);
//
//                String dateExpiration = Utilitaire.setDateExpiration();
//
//                model.addAttribute("dateExpiration",dateExpiration);
//                model.addAttribute("noCarte", noCarte);
//                Carte carte = new Carte();
//                model.addAttribute("carte", carte);


                /*
                Sinon la connexion pour un client régulier
                 */
//                Compte compte = new Compte();

                System.out.println(user);

                System.out.println("Vous êtes maintenant connecter ... ");
                ra.addFlashAttribute("message", "est connecter :)");
                model.addAttribute("user", user);
                model.addAttribute("idUser", user.getIdUser());
//                model.addAttribute("compte", compte);
                return "portailClient";
            }
            /*
            Si aucun user est trouver
             */
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return "erreur";
        }

        /*
        Si aucune connexion et aucune erreur on redirige
         */
        System.out.println("pas de connexion");
        return "index";
    }

    @GetMapping("/logout")
    public String logOut(HttpServletRequest request) {

        request.changeSessionId();
        System.out.println("the user is log out");
        return "index";
    }

}
