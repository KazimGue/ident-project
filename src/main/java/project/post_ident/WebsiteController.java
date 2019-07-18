package project.post_ident;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.post_ident.entities.Bewertung;

import java.util.ArrayList;


@Controller
public class WebsiteController {

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
    public String personenDatenAendern(Model model) {
        return "datenAendern";
    }

    //BEWERTUNG ABGEBEN
    @GetMapping(value = "/zeigeCheckout")
    public String zeigeCheckout(Model model){

        model.addAttribute("mykey","myvalue");
        ArrayList myList= new ArrayList();

        Bewertung bewertung1=new Bewertung();
        bewertung1.setBewertungText("super Seite");
        bewertung1.setAnzSterne(5);
        myList.add(bewertung1);

        Bewertung bewertung2=new Bewertung();
        bewertung2.setBewertungText("echt miese Seite");
        bewertung2.setAnzSterne(2);
        myList.add(bewertung2);
        return "checkout";
    }







}