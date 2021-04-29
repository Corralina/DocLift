package com.company.service;



import com.company.domian.Static;
import com.company.domian.Visa;
import com.company.repository.StaticRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaticService {

    @Autowired
    StaticRepo staticRepo;

    public void save(Static stat){
        staticRepo.save(stat);
    }

    public Iterable<Static> byVisa(Visa visa){
        return staticRepo.findAllByVisa(visa);
    }
}
