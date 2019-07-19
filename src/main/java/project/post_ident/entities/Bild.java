package project.post_ident.entities;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.hibernate.annotations.Type;
import org.hibernate.type.BlobType;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.File;
import java.sql.Blob;
import java.util.*;

/**
 * 
 */
@Entity
public class Bild {

 /*   private Bitmap bitmap;*/

    // Attribute und Spalten in der Datenbank
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // byte array erstellen zur Speicherung von Bildern
   @Lob
    private byte[] bild;


    // GEETTER UND SETTER
    public void setBild(byte[] bild) {
        this.bild = bild;
    }

    public byte[] getBild() {
        return bild;
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
}
