package com.company.service;



import com.company.domian.Document;
import com.company.repository.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class DocumentService {
    @Autowired
    private DocumentRepo documentRepo;

    public void save(Document document){documentRepo.save(document);}
    public Iterable<Document> all(){
        return documentRepo.findTop50ByNumberNotNull(Sort.by(Sort.Direction.DESC, "date"));
    }

    public Document findByNumber(String number){
        return documentRepo.findFirstByNumber(number);
    }

    public Iterable<Document> filterAll(String number, String autor, LocalDate dateStart, LocalDate dateFinish){
        return documentRepo.findAllByNumberLikeAndAuthorInformationPersonInitialsLikeAndDateAfterAndDateBefore("%" + number + "%", "%" + autor + "%",dateStart, dateFinish, Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Document> filterOneDate(String number, String autor, LocalDate date){
        return documentRepo.findAllByNumberLikeAndAuthorInformationPersonInitialsLikeAndDate("%" + number + "%", "%" + autor + "%", date, Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Document> filterNoDate(String number, String autor){
        return documentRepo.findAllByNumberLikeAndAuthorInformationPersonInitialsLike("%" + number + "%", "%" + autor + "%", Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Document> allStatus(Boolean status){
        return documentRepo.findTop50ByResolution(status, Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Document> filterAllStatus(String number, String autor, LocalDate dateStart, LocalDate dateFinish, Boolean status){
        return documentRepo.findAllByNumberLikeAndAuthorInformationPersonInitialsLikeAndDateAfterAndDateBeforeAndResolution("%" + number + "%", "%" + autor + "%",dateStart, dateFinish, status, Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Document> filterOneDateStatus(String number, String autor, LocalDate date, Boolean status){
        return documentRepo.findAllByNumberLikeAndAuthorInformationPersonInitialsLikeAndDateAndResolution("%" + number + "%", "%" + autor + "%", date, status, Sort.by(Sort.Direction.DESC, "date"));
    }

    public Iterable<Document> filterNoDateStatus(String number, String autor, Boolean status){
        return documentRepo.findAllByNumberLikeAndAuthorInformationPersonInitialsLikeAndResolution("%" + number + "%", "%" + autor + "%", status, Sort.by(Sort.Direction.DESC, "date"));
    }

}
