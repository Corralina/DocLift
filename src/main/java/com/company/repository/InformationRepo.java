package com.company.repository;


import com.company.domian.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepo extends JpaRepository<Information, Long> {
}
