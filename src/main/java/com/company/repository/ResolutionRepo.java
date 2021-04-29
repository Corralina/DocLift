package com.company.repository;


import com.company.domian.Resolution;
import com.company.domian.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ResolutionRepo extends JpaRepository<Resolution, Long> {

    Iterable<Resolution> findTop50ByDocumentNotNull(Sort sort);

    Iterable<Resolution> findTop50ByStatusFinish(Boolean status, Sort sort);

    Iterable<Resolution> findAllByDocumentNumberLikeAndFillingInformationPersonInitialsLikeAndAgreesInformationPersonInitialsLikeAndStatusFinish(String number, String autor, String agrees, Boolean status, Sort sort);

    Iterable<Resolution> findAllByDocumentNumberLikeAndFillingInformationPersonInitialsLikeAndAgreesInformationPersonInitialsLikeAndStatusFinishAndDate(String number, String autor, String agrees, Boolean status, LocalDate date, Sort sort);

    Iterable<Resolution> findAllByDocumentNumberLikeAndFillingInformationPersonInitialsLikeAndAgreesInformationPersonInitialsLikeAndStatusFinishAndDateAfterAndDateBefore(String number, String autor, String agrees, Boolean status, LocalDate start, LocalDate finish, Sort sort);

}
