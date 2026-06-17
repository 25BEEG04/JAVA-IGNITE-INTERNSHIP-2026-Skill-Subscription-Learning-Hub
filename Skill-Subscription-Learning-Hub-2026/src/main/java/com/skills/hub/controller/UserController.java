package com.skills.hub.controller;
import com.skills.hub.model.User;
import com.skills.hub.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
/*
=========================================================
WHAT IS THIS FILE?
Handles user actions like register, login and logout
=========================================================
*/
@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/register")
    public String showRegisterPage() {
        // STEP 1: Return register page
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        // STEP 1: call service.registerUser(user)
        User saved = userService.registerUser(user);
        // STEP 2: if success → redirect to login
        if (saved != null) {
            return "redirect:/login";
        }
        // STEP 3: else → stay on register page
        else {
            return "register";
        }
    }
    @GetMapping("/login")
    public String showLoginPage() {
        // STEP 1: return login page
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {
        // STEP 1: call userService.login(email, password)
        User user = userService.login(email, password);
        // STEP 2: if user != null → save user in session → redirect /packs
        if (user != null) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/packs";
        }
        // STEP 3: else → return login page again
        else {
            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // STEP 1: destroy the session (removes logged in user)
        session.invalidate();
        // STEP 2: redirect back to login page
        return "redirect:/login";
    }
    public UserService getUserService() {
        return userService;
    }
}
