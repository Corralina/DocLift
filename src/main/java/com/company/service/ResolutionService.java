package com.company.service;



import com.company.domian.Document;
import com.company.domian.Resolution;
import com.company.domian.User;
import com.company.repository.ResolutionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ResolutionService {

    @Autowired
    ResolutionRepo resolutionRepo;

    public Resolution save(Resolution resolution){
        return resolutionRepo.save(resolution);
    }

    public Iterable<Resolution> all(){return resolutionRepo.findTop50ByDocumentNotNull(Sort.by(Sort.Direction.DESC, "date"));}

    public Iterable<Resolution> allStatusFinish(Boolean status){
        return resolutionRepo.findTop50ByStatusFinish(status,Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Resolution> filterNoDate(String number, String autor, String agrees, Boolean status){
        return resolutionRepo.findAllByDocumentNumberLikeAndFillingInformationPersonInitialsLikeAndAgreesInformationPersonInitialsLikeAndStatusFinish("%" + number + "%", "%" + autor + "%", "%" + agrees +"%", status, Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Resolution> filterOneDate(String number, String autor, String agrees, Boolean status, LocalDate date){
        return resolutionRepo.findAllByDocumentNumberLikeAndFillingInformationPersonInitialsLikeAndAgreesInformationPersonInitialsLikeAndStatusFinishAndDate("%" + number + "%", "%" + autor + "%", "%" + agrees +"%", status, date, Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Resolution> filterAll(String number, String autor, String agrees, Boolean status, LocalDate start, LocalDate finish){
        return resolutionRepo.findAllByDocumentNumberLikeAndFillingInformationPersonInitialsLikeAndAgreesInformationPersonInitialsLikeAndStatusFinishAndDateAfterAndDateBefore("%" + number + "%", "%" + autor + "%", "%" + agrees +"%", status, start, finish, Sort.by(Sort.Direction.DESC, "date"));
    }



}
