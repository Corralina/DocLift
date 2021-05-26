package com.company.service;


import com.company.domian.Performer;
import com.company.domian.Resolution;
import com.company.domian.User;
import com.company.repository.PerformerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public Iterable<Performer> allStatusFinish(User user, Boolean status){
        return performerRepo.findTop50ByUserAndResolutionStatusFinish(user, status);
    }

    public Iterable<Performer> filterNoDate(User user, String number, String autor, String agrees, Boolean status){
        return performerRepo.findAllByUserAndResolutionDocumentNumberLikeAndResolutionFillingInformationPersonInitialsLikeAndResolutionAgreesInformationPersonInitialsLikeAndResolutionStatusFinish(user,"%" + number + "%", "%" + autor + "%", "%" + agrees +"%", status);
    }

    public Iterable<Performer> filterOneDate(User user, String number, String autor, String agrees, Boolean status, LocalDate date){
        return performerRepo.findAllByUserAndResolutionDocumentNumberLikeAndResolutionFillingInformationPersonInitialsLikeAndResolutionAgreesInformationPersonInitialsLikeAndResolutionStatusFinishAndResolutionDate(user, "%" + number + "%", "%" + autor + "%", "%" + agrees +"%", status, date);
    }

    public Iterable<Performer> filterAll(User user, String number, String autor, String agrees, Boolean status, LocalDate start, LocalDate finish){
        return performerRepo.findAllByUserAndResolutionDocumentNumberLikeAndResolutionFillingInformationPersonInitialsLikeAndResolutionAgreesInformationPersonInitialsLikeAndResolutionStatusFinishAndResolutionDateAfterAndResolutionDateBefore(user, "%" + number + "%", "%" + autor + "%", "%" + agrees +"%", status, start, finish);
    }

}
