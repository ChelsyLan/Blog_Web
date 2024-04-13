package net.javaguides.blogwebapp.controller;

import jakarta.validation.Valid;
import net.javaguides.blogwebapp.dto.RegistrationDto;
import net.javaguides.blogwebapp.entity.User;
import net.javaguides.blogwebapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";

    }

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result,Model model){
        User existingUser = userService.findByEmail(user.getEmail());
        if(existingUser!=null){
            result.rejectValue("email",null,"The email already existed");
        }
        if(result.hasErrors()){
            model.addAttribute("user",user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";

    }
}
