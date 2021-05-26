package com.company.repository;


import com.company.domian.Performer;
import com.company.domian.Resolution;
import com.company.domian.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

public interface PerformerRepo extends JpaRepository<Performer, Long> {


    Iterable<Performer> findAllByResolution(Resolution resolution);

    Iterable<Performer> findTop50ByUserAndResolutionStatusFinish(User user, Boolean status);

    Iterable<Performer> findAllByUserAndResolutionDocumentNumberLikeAndResolutionFillingInformationPersonInitialsLikeAndResolutionAgreesInformationPersonInitialsLikeAndResolutionStatusFinish(User user,String number, String autor, String agrees, Boolean status);

    Iterable<Performer> findAllByUserAndResolutionDocumentNumberLikeAndResolutionFillingInformationPersonInitialsLikeAndResolutionAgreesInformationPersonInitialsLikeAndResolutionStatusFinishAndResolutionDate(User user,String number, String autor, String agrees, Boolean status, LocalDate date);

    Iterable<Performer> findAllByUserAndResolutionDocumentNumberLikeAndResolutionFillingInformationPersonInitialsLikeAndResolutionAgreesInformationPersonInitialsLikeAndResolutionStatusFinishAndResolutionDateAfterAndResolutionDateBefore(User user,String number, String autor, String agrees, Boolean status, LocalDate start, LocalDate finish);

}
