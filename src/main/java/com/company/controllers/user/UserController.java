package com.company.controllers.user;


import com.company.domian.Contact;
import com.company.domian.Person;
import com.company.domian.Role;
import com.company.domian.User;
import com.company.fileService.SaveCaption;
import com.company.service.ContactService;
import com.company.service.PersonService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    SaveCaption saveCaption;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ContactService contactService;

    @Autowired
    PersonService personService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String list(
            Model model
    ){
        model.addAttribute("users", userService.allUser());

        return "user/list";
    }

    @GetMapping("{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String edit(
            @PathVariable User user,
            Model model
    ){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());

        return "user/edit";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String edit(@RequestParam Map<String,String> form,
                       @RequestParam("userId") User user,
                       @RequestParam("caption") MultipartFile caption
    ) throws IOException {

            Person person = user.getInformation().getPerson();
            Contact contact = user.getInformation().getContact();

            person.setSurname(form.get("surname"));
            person.setName(form.get("name"));
            person.setMiddlename(form.get("middlename"));
            person.setInitials(form.get("initials"));
            person.setPost(form.get("post"));
            if(!caption.getOriginalFilename().equals("")){
                person.setCaption(saveCaption.uploadImg(caption));
            }

            contact.setPhone(form.get("phone"));
            contact.setMail(form.get("mail"));


                user.setUsername(form.get("username"));
                if (!form.get("password").equals("")) {
                    user.setPassword(passwordEncoder.encode(form.get("password")));
                }


            Set<String> roles = Arrays.stream(Role.values())
                    .map(Role::name)
                    .collect(Collectors.toSet());
            user.getRoles().clear();
            for (String key : form.keySet()){
                if (roles.contains((key))){
                    user.getRoles().add(Role.valueOf(key));
                }
            }

            personService.save(person);
            contactService.save(contact);
            userService.userSave(user);

            return "redirect:/user";

    }
}
