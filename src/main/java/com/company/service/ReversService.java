package com.company.service;



import com.company.domian.Resolution;
import com.company.domian.Revers;
import com.company.repository.ReversRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReversService {

    @Autowired
    ReversRepo reversRepo;

    public void save(Revers revers){
        reversRepo.save(revers);
    }

    public Revers byResolutionActiv(Resolution resolution){
        return reversRepo.findFirstByResolutionAndFinishFalse(resolution);
    }

    public Iterable<Revers> byResolution(Resolution resolution){
        return reversRepo.findAllByResolution(resolution, Sort.by(Sort.Direction.DESC, "date"));
    }
}
