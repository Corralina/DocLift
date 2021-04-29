package com.company.domian;

import javax.persistence.*;

@Entity
@Table(
        name = "charter"
)
public class Status {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    @Column(columnDefinition = "TINYINT")
    private Boolean finish;
    @Column(columnDefinition = "TINYINT")
    private Boolean revers;
    @Column(columnDefinition = "TINYINT")
    private Boolean telegram;
    @Column(columnDefinition = "TINYINT")
    private Boolean done;




    public Status(){}
    public Status(Long id, Boolean finish, Boolean revers, Boolean telegram, Boolean done){
        this.id = id;
        this.finish = finish;
        this.revers = revers;
        this.telegram = telegram;
        this.done = done;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public Boolean getRevers() {
        return revers;
    }

    public void setRevers(Boolean revers) {
        this.revers = revers;
    }

    public Boolean getTelegram() {
        return telegram;
    }

    public void setTelegram(Boolean telegram) {
        this.telegram = telegram;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
