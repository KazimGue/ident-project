package project.post_ident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.post_ident.entities.Personendaten;
import project.post_ident.entities.TempPersonendaten;
import project.post_ident.repository.PersonenDatenRepository;
import project.post_ident.repository.TempPersonenDatenRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class WebsiteController {

    @Autowired
    private PersonenDatenRepository personenDatenRepository;

    @Autowired
    private TempPersonenDatenRepository tempPersonenDatenRepository;

    // Startseite öffnen
    @GetMapping(value = "/")
    public String startSeiteOeffnen() {

        return "startseite";
    }

    // Bild hochladen Methode
    @RequestMapping(value = "bildhochladen")
    public String bildHochladen(Model model) {
        // In die Methodenparameter einfügen?
        // bildRepository.save(bild);

        List<TempPersonendaten> tempPersonenDatenListe = tempPersonenDatenRepository.findAll();
        model.addAttribute("tempPersonenDatenListe", tempPersonenDatenListe);
        return "vergleich";
    }

    @GetMapping(value="vergleich")
    public String vergleicheWerte(Model model){

        return "vergleich";
    }

    //Bringt zu der HTML-Seite, auf der ein neues Objekt hinzugefügt wird
    @GetMapping(value = "/datenAendern")
    public String personenDatenAendern(Model model) {

        List<TempPersonendaten> tempPersonenDatenListe = tempPersonenDatenRepository.findAll();
        model.addAttribute("tempPersonenDatenListe", tempPersonenDatenListe);

        Personendaten neuePerson = new Personendaten();
        model.addAttribute("neuePersonenDaten", neuePerson);


        return "datenAendern";
    }

    //Speicherung der eingegebenen Daten
    @PostMapping(value = "/datenSpeichern")
    public String personenDatenSpeichern(Model model,
            @ModelAttribute("neuePersonenDaten") Personendaten neuePerson){

        List<TempPersonendaten> tempPersonenDatenListe = tempPersonenDatenRepository.findAll();
        model.addAttribute("tempPersonenDatenListe", tempPersonenDatenListe);

        neuePerson.setVorname(tempPersonenDatenListe.get(0).getVorname());
        neuePerson.setNachname(tempPersonenDatenListe.get(0).getNachname());
        neuePerson.setPersoNr(tempPersonenDatenListe.get(0).getPersoNr());
        neuePerson.setStrasse(tempPersonenDatenListe.get(0).getStrasse());
        neuePerson.setHausnummer(tempPersonenDatenListe.get(0).getHausnummer());
        neuePerson.setPlz(tempPersonenDatenListe.get(0).getPlz());
        neuePerson.setStadt(tempPersonenDatenListe.get(0).getStadt());

       personenDatenRepository.save(neuePerson);

        return "checkout";
    }

    @GetMapping(value = "/zeigeCheckout")
    public String zeigeCheckout() {


        return "checkout";
    }




}