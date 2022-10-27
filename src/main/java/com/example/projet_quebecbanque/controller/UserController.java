package com.example.projet_quebecbanque.controller;

import com.example.projet_quebecbanque.controller.exception.UserNotFoundException;
import com.example.projet_quebecbanque.entities.User;
import com.example.projet_quebecbanque.service.UserService;
import com.example.projet_quebecbanque.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/addUser")
    public String sendForm(User user, Model model) {
//        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String processForm(User user , RedirectAttributes ra) {
        PasswordUtils passwordUtils = new PasswordUtils();
        passwordUtils.hash(user.getHashPassword());
        User userHash = new User(user.getUsername(), passwordUtils.getHashpassword(), passwordUtils.getSalt());
        System.out.println(userHash);
        userService.save(userHash);
        ra.addFlashAttribute("message", "The user has been save successfully");
        return "showMessage";
    }

    @GetMapping("/users")
    public String showlistUsers(Model model) {
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);
        return "gestionAdmin";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable Integer id , Model model, RedirectAttributes ra) {
        try {
            User userCourant = userService.get(id);
            model.addAttribute("userCourant", userCourant);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return  "gestionAdmin";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/gestionAdmin";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String  deleteUser(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            userService.delete(id);
            ra.addFlashAttribute("message", "Le user a bien été supprimer ");
            return "redirect:/gestionAdmin";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/gestionAdmin";
        }

    }



    @GetMapping("/user/update/{id}")
    public String updateUser(@PathVariable Integer id , User user, Model model, RedirectAttributes ra) {
        System.out.println("id du user : "+ id);
        User userTrouver = user;
        userTrouver.setIdUser(id);
        System.out.println(userTrouver);
        userService.updateUser(userTrouver);

        ra.addFlashAttribute("message",
                "Le user avec le id : " + userTrouver.getIdUser() + " a été modifié avec succès");

        return "redirect:/gestionAdmin";
    }



}

