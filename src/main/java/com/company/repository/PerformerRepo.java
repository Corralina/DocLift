package com.company.repository;


import com.company.domian.Performer;
import com.company.domian.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface PerformerRepo extends JpaRepository<Performer, Long> {


    Iterable<Performer> findAllByResolution(Resolution resolution);

}
