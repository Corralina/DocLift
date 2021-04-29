package com.company.repository;

import com.company.domian.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DocumentRepo extends JpaRepository<Document, Long> {

    Iterable<Document> findTop50ByNumberNotNull(Sort sort);

    Iterable<Document> findTop50ByResolution(Boolean status, Sort sort);

    Document findFirstByNumber(String number);

    Iterable<Document> findAllByNumberLikeAndAuthorInformationPersonInitialsLike(String number, String autor, Sort sort);
    Iterable<Document> findAllByNumberLikeAndAuthorInformationPersonInitialsLikeAndResolution(String number, String autor, Boolean res, Sort sort);

    Iterable<Document> findAllByNumberLikeAndAuthorInformationPersonInitialsLikeAndDate(String number, String autor, LocalDate date, Sort sort);
    Iterable<Document> findAllByNumberLikeAndAuthorInformationPersonInitialsLikeAndDateAndResolution(String number, String autor, LocalDate date, Boolean satus, Sort sort);


    Iterable<Document> findAllByNumberLikeAndAuthorInformationPersonInitialsLikeAndDateAfterAndDateBefore(String number, String autor, LocalDate dateStart, LocalDate dateFinish, Sort sort);
    Iterable<Document> findAllByNumberLikeAndAuthorInformationPersonInitialsLikeAndDateAfterAndDateBeforeAndResolution(String number, String autor, LocalDate dateStart, LocalDate dateFinish, Boolean status, Sort sort);



}
