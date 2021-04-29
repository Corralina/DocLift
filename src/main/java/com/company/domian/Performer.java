package com.company.domian;


import javax.persistence.*;

@Entity
@Table(
        name = "executant"
)
public class Performer {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "doer"
    )
    private User user;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "resolution"
    )
    private Resolution resolution;
    private String coment;



    public Performer(){}
    public Performer(Long id, User user, Resolution resolution, String coment){
        this.id = id;
        this.user = user;
        this.resolution = resolution;
        this.coment = coment;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
