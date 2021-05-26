package com.company.controllers.user;


import com.company.configuration.LDAPConfig;
import com.company.domian.*;
import com.company.formatService.PhoneFormat;
import com.company.service.ContactService;
import com.company.service.InformationService;
import com.company.service.PersonService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("userLDAP")
public class UpdateUserData {

    @Autowired
    UserService userService;

    @Autowired
    LDAPConfig LDAPConfig;

    @Autowired
    PersonService personService;

    @Autowired
    ContactService contactService;

    @Autowired
    InformationService informationService;

    @GetMapping
    public String start(){
        return "user/Update";
    }


    @PostMapping
    public String go(
            @RequestParam Map<String,String> form,
            Model model
    ) throws NamingException {
        String returnedAtts[]={"samAccountName", "mail", "mobile", "title", "sn", "givenname"};
        NamingEnumeration<SearchResult> answer = LDAPConfig.LDAPConnect(returnedAtts);
        int totalResults = 0;
        HashSet<User> userLocal = new HashSet((Collection) userService.allUser());
        PhoneFormat phoneFormat = new PhoneFormat();
        while (answer.hasMoreElements()) {
            SearchResult sr = answer.next();
            totalResults++;
            Attributes attrs = sr.getAttributes();
            String givenname = String.valueOf(attrs.get("givenname"))
                    .substring(String.valueOf(attrs.get("givenname")).indexOf(": ") + 2);

            //Для перевірки чи це людина, може бути наприклад зал
            if(!givenname.equals("ull")){

                StringBuilder sam = new StringBuilder(String.valueOf(attrs.get("mail")));
                sam.delete(0,sam.indexOf(": ") + 2);

                StringBuilder mail = new StringBuilder(String.valueOf(attrs.get("mail")));
                mail.delete(0,mail.indexOf(": ") + 2);

                String mobile = String.valueOf(attrs.get("mobile")).substring(String.valueOf(attrs.get("mobile")).indexOf(": ") + 2);
//                mobile = mobile.replaceAll("-","");

                StringBuilder surname = new StringBuilder(String.valueOf(attrs.get("sn")));
                surname.delete(0,surname.indexOf(": ") + 2);

                String[] names = givenname.split(" ");

                StringBuilder title = new StringBuilder(String.valueOf(attrs.get("title")));
                title.delete(0,title.indexOf(": ") + 2);

                User user;
                Information information;
                Contact contact;
                Person person;

                boolean userStatus = userService.loadUserByUsername(String.valueOf(sam)) != null;

                if (!userStatus){
                    user = new User();
                    information = new Information();
                    contact = new Contact();
                    person = new Person();
                }else{
                    user = userService.findByUsername(String.valueOf(sam));
                    information = user.getInformation();
                    contact = user.getInformation().getContact();
                    person = user.getInformation().getPerson();
                }

                userLocal.remove(user);

                if (form.get("mail") != null || !userStatus){
                    contact.setMail(String.valueOf(mail));
                }
                if (form.get("phone") != null || !userStatus){
                    contact.setPhone(phoneFormat.convert(mobile));
                }
                if (form.get("name") != null || !userStatus){
                    person.setSurname(String.valueOf(surname));
                    person.setName(names[0]);
                    try {
                        person.setMiddlename(names[1]);
                    }catch (Exception e){
                        person.setMiddlename(" ");
                    }
                    person.setInitials(person.getSurname() + " " + person.getName().substring(0,1) + ". " + person.getMiddlename().substring(0,1) + ".");
                }
                if (form.get("title") != null || !userStatus){
                    person.setPost(String.valueOf(title));
                }

                personService.save(person);
                contactService.save(contact);

                if (userService.loadUserByUsername(String.valueOf(sam)) == null) {
                    information.setContact(contact);
                    information.setPerson(person);
                    informationService.save(information);
                    user.setUsername(String.valueOf(sam));
                    user.setPassword("password");
                    user.setInformation(information);
                    userService.userSave(user);
                }
            }
        }
        Iterator<User> i = userLocal.iterator();
        while (i.hasNext()){
            User u = i.next();
            if (!u.isLoc()){
                if ( !u.isDrop()){
                    u.getRoles().clear();
                    u.getRoles().add(Role.valueOf("DROP"));
                    userService.userSave(u);
                }
            }

        }
        return "redirect:/user";
    }


}
