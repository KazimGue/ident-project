package project.post_ident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.post_ident.entities.Personendaten;
import project.post_ident.repository.PersonenDatenRepository;

import java.util.List;

@Controller
public class WebsiteController {

    @Autowired
    private PersonenDatenRepository personenDatenRepository;

    // Startseite öffnen
    @GetMapping(value = "/")
    public String startSeiteOeffnen() {
        return "startseite";
    }

    // Bild hochladen Methode
    @RequestMapping(value = "bildhochladen")
    public String bildHochladen() {
        // In die Methodenparameter einfügen?
        // bildRepository.save(bild);
        return "vergleich";
    }

    @GetMapping(value = "/vergleich")
    public String personenDatenAbgleichen(Model model) {

        return "vergleich";
    }



    @GetMapping(value = "/datenAendern")
    public String personenDatenAendern(
            Model model) {

        return "datenAendern";
    }


    @GetMapping(value = "/zeigeCheckout")
    public String zeigeCheckout() {

        return "checkout";
    }




}