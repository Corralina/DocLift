package com.company.service;



import com.company.domian.Contact;
import com.company.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepo contactRepo;

    public void save(Contact contact){
        contactRepo.save(contact);
    }

    public void delete(Contact contact){contactRepo.delete(contact);}
}
