package com.company.repository;



import com.company.domian.Static;
import com.company.domian.Visa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface StaticRepo extends JpaRepository<Static, Long> {

    Iterable<Static> findAllByVisa(Visa visa);

}
