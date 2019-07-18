package project.post_ident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.post_ident.entities.Bild;
import project.post_ident.repository.BildRepository;

import java.util.*;

/**
 * 
 */
@Controller
 public class WebsiteController {

/*
    // Verbindung zur Bild- Datenbank
    @Autowired
    private BildRepository bildRepository;

 */


    // Startseite öffnen
    @GetMapping(value = "/")
    public String startSeiteOeffnen() {
        return "startseite";
    }


    // Bild hochladen Methode
    @RequestMapping (value = "bildhochladen")
    public String bildHochladen() {

        // In die Methodenparameter einfügen?
       // bildRepository.save(bild);
        return "vergleich";
    }



}
