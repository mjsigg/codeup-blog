package com.codeup.springblog.controllers;

import com.codeup.springblog.model.User;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserRepository usersDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSIgnup(Model model) {
        model.addAttribute("newUser", new User());
        return "signup";
    }


    @PostMapping("sign-up")
    public String saveUser(@ModelAttribute User user) {
        usersDao.save(user);
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        // Expression to access the current user using Spring Security
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("currentUser", currentUser);
        return "profile";
    }

}
