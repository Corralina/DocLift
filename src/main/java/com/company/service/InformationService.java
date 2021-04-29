package com.company.service;


import com.company.domian.Contact;
import com.company.domian.Information;
import com.company.repository.InformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class InformationService {

    @Autowired
    private InformationRepo informationRepo;

    public void save(Information information){
        informationRepo.save(information);
    }

    public void delete(Information information){informationRepo.delete(information);}
}
