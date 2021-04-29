package com.company.service;



import com.company.domian.Send;
import com.company.repository.SendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendService {
    @Autowired
    SendRepo sendRepo;


    public Iterable<Send> who(){
        return  sendRepo.findAll();
    }

    public void save(Send send){
        sendRepo.save(send);
    }
}
