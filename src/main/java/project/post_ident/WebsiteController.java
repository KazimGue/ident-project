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

    @RequestMapping(value="vergleich")
    public String vergleicheWerte(Model model){

        List <Personendaten> personenDatenListe = personenDatenRepository.findAll();
        model.addAttribute("personenDatenListe", personenDatenListe);

        return "vergleich";
    }

    //Bringt zu der HTML-Seite, auf der ein neues Objekt hinzugefügt wird
    @GetMapping(value = "/datenAendern")
    public String personenDatenAendern(
            Model model) {

        model.addAttribute("neuePersonenDaten", new Personendaten());

        return "datenAendern";
    }
    //Speicherung der eingegebenen Daten
    @PostMapping(value = "/datenSpeichern")
    public String personenDatenSpeichern(Model model, @ModelAttribute("neuePersonenDaten") Personendaten personendaten){

        personenDatenRepository.save(personendaten);

        return "checkout";
    }

    @GetMapping(value = "/zeigeCheckout")
    public String zeigeCheckout() {

        return "checkout";
    }




}