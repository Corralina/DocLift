package com.company.domian;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "visa"
)
public class Visa {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private String position;
    private String agrees;
    private LocalDate data;

    public Visa(){}
    public Visa(Long id, String position, String agrees, LocalDate data){
        this.id = id;
        this.position = position;
        this.agrees = agrees;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAgrees() {
        return agrees;
    }

    public void setAgrees(String agrees) {
        this.agrees = agrees;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

}
