package com.company.domian;



import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "comeback"
)
public class Revers {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "resolution"
    )
    private Resolution resolution;
    private String coment;
    private LocalDate date;
    @Column(columnDefinition = "TINYINT")
    private Boolean finish;
    @Column(columnDefinition = "TINYINT")
    private Boolean telegram;

    public Revers(){}
    public Revers(Long id, Resolution resolution, String coment, LocalDate date, Boolean finish, Boolean telegram){
        this.id = id;
        this.resolution = resolution;
        this.coment = coment;
        this.date = date;
        this.finish = finish;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getRev() {
        return finish;
    }

    public void setRev(Boolean rev) {
        this.finish = rev;
    }


    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public Boolean getTelegram() {
        return telegram;
    }

    public void setTelegram(Boolean telegram) {
        this.telegram = telegram;
    }
}
