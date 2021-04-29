package com.company.service;



import com.company.domian.Status;
import com.company.repository.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    @Autowired
    StatusRepo statusRepo;

    public Status save(Status status){
        return statusRepo.save(status);
    }
}
