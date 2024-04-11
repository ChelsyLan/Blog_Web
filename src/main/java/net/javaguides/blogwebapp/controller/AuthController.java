package net.javaguides.blogwebapp.controller;

import net.javaguides.blogwebapp.dto.RegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AuthController {
    @GetMapping("/register")
    public String showRegisterForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user",user);

        return "register";
    }
}
