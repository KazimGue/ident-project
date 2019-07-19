package project.post_ident.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

@Entity
public class Bild {

    @Id
    @GeneratedValue
    private Long id;

    public Bild() {
    }


    /**
     *
     */
 /*   private Bitmap bitmap;*/
    /**
     * 
     */
    private Long bildID;


}