package com.davideleonino.palestra.controller;

import com.davideleonino.palestra.model.User;
import com.davideleonino.palestra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Visualizza il form di registrazione
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());  // Crea un nuovo oggetto User per il form
        return "register";  // Ritorna il nome del template per il form di registrazione
    }

    // Gestisce la registrazione
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        try {
            userService.registerUser(user);  // Cifra la password e salva l'utente
            return "redirect:/login";  // Redirige alla pagina di login dopo la registrazione
        } catch (Exception e) {
            // Gestisce eventuali errori (come email già in uso)
            return "redirect:/register?error";  // Ritorna alla pagina di registrazione con errore
        }
    }
}