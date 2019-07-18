package project.post_ident.entities;


import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.criteria.CriteriaBuilder;


public class Bewertung {



    private Long bewertungID;

    private Integer anzSterne;
    private String bewertungText;

    /*Default Constructr*/
    public Bewertung() {
    }

    public String getBewertungText() {
        return bewertungText;
    }

    public void setBewertungText(String bewertungText) {
        this.bewertungText = bewertungText;
    }

    public Long getBewertungID() {
        return bewertungID;
    }

    public void setBewertungID(Long bewertungID) {
        this.bewertungID = bewertungID;
    }

    public Integer getAnzSterne() {
        return anzSterne;
    }

    public void setAnzSterne(Integer anzSterne) {
        this.anzSterne = anzSterne;
    }

}