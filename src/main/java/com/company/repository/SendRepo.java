package com.company.repository;


import com.company.domian.Send;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SendRepo extends JpaRepository<Send, Long> {



}
