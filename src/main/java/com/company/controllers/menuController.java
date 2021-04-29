package com.company.controllers;

import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.NamingException;

@Controller
@RequestMapping({"/"})
public class menuController {

    @Autowired
    UserService userService;

    @GetMapping
    public String menu() throws NamingException {
        return "menu";
    }


}
