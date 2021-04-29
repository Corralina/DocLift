package com.company.domian;


import javax.persistence.*;

@Entity
@Table(
        name = "info"
)
public class Information {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "person"
    )
    private Person person;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "contact"
    )
    private Contact contact;


    public Information(){}

    public Information(Long id, Person person, Contact contact){
        this.id = id;
        this.person = person;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}
