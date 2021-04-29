package com.company.service;



import com.company.domian.Person;
import com.company.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepo personRepo;

    public void save(Person person){
        personRepo.save(person);
    }

    public void delete(Person person){personRepo.delete(person);}
}
