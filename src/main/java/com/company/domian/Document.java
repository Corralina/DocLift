package com.company.domian;


import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(
        name = "file"
)
public class Document {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private String name;
    @NotEmpty(message = "Номер документу обовязково повинен бути заповненим")
    @Size(min = 2, max = 30, message = "Довжина поля 'номер документу' повина складати вiд 2 до 30 символів!!!")
    private String number;
    private LocalDate date;
    private String coment;
    private boolean telegram;
    @Column(columnDefinition = "TINYINT")
    private Boolean resolution;
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "author"
    )
    private User author;

    public Document(){}
    public Document(Long id, String name, String number, LocalDate date, String coment, boolean telegram, User author, Boolean resolution){
        this.id = id;
        this.name = name;
        this.number = number;
        this.date = date;
        this.coment = coment;
        this.telegram= telegram;
        this.author = author;
        this.resolution = resolution;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public boolean isTelegram() {
        return telegram;
    }

    public void setTelegram(boolean telegram) {
        this.telegram = telegram;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Boolean getResolution() {
        return resolution;
    }

    public void setResolution(Boolean resolution) {
        this.resolution = resolution;
    }
}
