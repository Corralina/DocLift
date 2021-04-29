package com.company.service;


import com.company.domian.Performer;
import com.company.domian.Resolution;
import com.company.domian.User;
import com.company.repository.PerformerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PerformerService {

    @Autowired
    PerformerRepo performerRepo;

    public void save(Performer performer){
        performerRepo.save(performer);
    }

    public Iterable<Performer> byResolution(Resolution resolution){
        return performerRepo.findAllByResolution(resolution);
    }

    public void drop(Performer performer){performerRepo.delete(performer);}

}
