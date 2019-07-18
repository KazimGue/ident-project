package project.post_ident.entities;


import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


public class Bewertung {



    private Long bewertungID;

    public Long getBewertungID() {
        return bewertungID;
    }

    public void setBewertungID(Long bewertungID) {
        this.bewertungID = bewertungID;
    }

    public int getAnzSterne() {
        return anzSterne;
    }

    public void setAnzSterne(int anzSterne) {
        this.anzSterne = anzSterne;
    }

    private int anzSterne;

    /*Default Constructr*/
    public Bewertung() {
    }

}