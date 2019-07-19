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
        model.addAttribute("originalPerson", new Personendaten());

        return "vergleich";
    }

    @GetMapping(value="vergleich")
    public String vergleicheWerte(Model model){

        return "vergleich";
    }

    //Bringt zu der HTML-Seite, auf der ein neues Objekt hinzugefügt wird
    @GetMapping(value = "/datenAendern")
    public String personenDatenAendern(Model model) {

        Long tempID= new Long(1);
        Optional<TempPersonendaten> tempPersonendaten1 = tempPersonenDatenRepository.findById(tempID);
        TempPersonendaten tempPersonendaten=tempPersonendaten1.get();
        model.addAttribute("tempPersonenDaten", tempPersonendaten);


        return "datenAendern";
    }

    //Speicherung der eingegebenen Daten
    @PostMapping(value = "/datenSpeichern")
    public String personenDatenSpeichern(Model model,
            @ModelAttribute("tempPersonenDaten") TempPersonendaten tempPersonendaten){


        Personendaten neuePerson = new Personendaten();

        neuePerson.setVorname(tempPersonendaten.getVorname());
        neuePerson.setNachname(tempPersonendaten.getNachname());
        neuePerson.setPersoNr(tempPersonendaten.getPersoNr());
        neuePerson.setStrasse(tempPersonendaten.getStrasse());
        neuePerson.setHausnummer(tempPersonendaten.getHausnummer());
        neuePerson.setPlz(tempPersonendaten.getPlz());
        neuePerson.setStadt(tempPersonendaten.getStadt());

       personenDatenRepository.save(neuePerson);

        return "checkout";
    }

    @PostMapping(value ="originalSpeichern")
    public String originalDatenSpeichern(Model model,
         @ModelAttribute("originalPerson") Personendaten originalPerson){

        List<TempPersonendaten> tempPersonenDatenListe = tempPersonenDatenRepository.findAll();
        model.addAttribute("tempPersonenDatenListe", tempPersonenDatenListe);

        originalPerson.setVorname(tempPersonenDatenListe.get(0).getVorname());
        originalPerson.setNachname(tempPersonenDatenListe.get(0).getNachname());
        originalPerson.setPersoNr(tempPersonenDatenListe.get(0).getPersoNr());
        originalPerson.setStrasse(tempPersonenDatenListe.get(0).getStrasse());
        originalPerson.setHausnummer(tempPersonenDatenListe.get(0).getHausnummer());
        originalPerson.setPlz(tempPersonenDatenListe.get(0).getPlz());
        originalPerson.setStadt(tempPersonenDatenListe.get(0).getStadt());

        personenDatenRepository.save(originalPerson);


        return "checkout";
    }

    @GetMapping(value = "/zeigeCheckout")
    public String zeigeCheckout() {


        return "checkout";
    }




}