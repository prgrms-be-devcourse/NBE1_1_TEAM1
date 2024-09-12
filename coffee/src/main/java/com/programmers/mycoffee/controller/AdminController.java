package com.programmers.mycoffee.controller;

import com.programmers.mycoffee.model.admin.JoinDto;
import com.programmers.mycoffee.model.admin.LoginDto;
import com.programmers.mycoffee.model.admin.Role;
import com.programmers.mycoffee.service.admin.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final JoinService joinService;

    @Autowired
    public AdminController(JoinService joinService) {
        this.joinService = joinService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginDto", new LoginDto("", ""));
        return "loginPage";
    }
    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("joinDto", new JoinDto("", "", Role.ADMIN));
        return "signUp";
    }

    @PostMapping("/signUp")
    public String registerUser(JoinDto joinDto) {
        joinService.joinProcess(joinDto);
        return "redirect:/login";
    }
}
