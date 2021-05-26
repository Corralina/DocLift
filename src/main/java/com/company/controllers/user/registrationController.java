package com.company.controllers.user;



import com.company.domian.*;
import com.company.service.ContactService;
import com.company.service.InformationService;
import com.company.service.PersonService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class registrationController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;





    @GetMapping
    public String trageFirst(
            Model model
    ){
        return "user/registrationIndividual";
    }

    @PostMapping("individual")
    public String saveIndividual(
            @RequestParam Map<String,String> form,
            Person person,
            Model model
    ){


        personService.save(person);

        model.addAttribute("individual", person);

        return "user/registrationContact";
    }

    @PostMapping("contact")
    public String saveContact(
            @RequestParam Map<String,String> form,
            @RequestParam("individual") Person person,
            Contact contact,
            Model model
    ){



        contactService.save(contact);

        Information information = new Information();
        information.setPerson(person);
        information.setContact(contact);
        informationService.save(information);

        model.addAttribute("information", information);

        return "user/registration";
    }

    @PostMapping("registration")
    public String saveUser(
            @RequestParam Map<String,String> form,
            User user,
            Model model
    ){

        if(userService.loadUserByUsername(user.getUsername()) != null){
            model.addAttribute("error", "Користувач із логіном " + user.getUsername() + " уже існеє");
            model.addAttribute("user",user);
            return "user/registration";
        }


        if(user.getUsername() == ""){
            model.addAttribute("error", "Поле логін не може бути пустим");
            return "user/registrationContact";
        }


        if(user.getPassword() == ""){
            model.addAttribute("error", "Поле пароль не може бути пустим");
            return "user/registrationContact";
        }

        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.userSave(user);


        return "menu";
    }


}
