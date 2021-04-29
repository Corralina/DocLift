package com.company.repository;


import com.company.domian.Resolution;
import com.company.domian.Revers;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReversRepo extends JpaRepository<Revers, Long> {

    Revers findFirstByResolutionAndFinishFalse(Resolution resolution);

    Iterable<Revers> findAllByResolution(Resolution resolution, Sort sort);
}
