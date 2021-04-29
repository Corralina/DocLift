package com.company.domian;



import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "resolve")
public class Resolution {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "document"
    )
    private Document document;
    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "filling"
    )
    private User filling;
    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "agrees"
    )
    private User agrees;
    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "status"
    )
    private Status status;
    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "visa"
    )
    private Visa visa;
    private LocalDate date;
    private String coment;


    public Resolution(){}
    public Resolution(Long id, Document document, User filling, User agrees, Status status, Visa visa, LocalDate date, String coment){
        this.id = id;
        this.document = document;
        this.filling = filling;
        this.agrees = agrees;
        this.status = status;
        this.visa = visa;
        this.date = date;
        this.coment = coment;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public User getFilling() {
        return filling;
    }

    public void setFilling(User filling) {
        this.filling = filling;
    }

    public User getAgrees() {
        return agrees;
    }

    public void setAgrees(User agrees) {
        this.agrees = agrees;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Visa getVisa() {
        return visa;
    }

    public void setVisa(Visa visa) {
        this.visa = visa;
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


}
