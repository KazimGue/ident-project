package project.post_ident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import project.post_ident.entities.Bild;
import project.post_ident.repository.BildRepository;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

@Controller
public class WebsiteController {

    @Autowired
    private BildRepository bildRepository;

    // Startseite öffnen
    @GetMapping(value = "/")
    public String startSeiteOeffnen(Model model) {
        Bild bild = new Bild();
        model.addAttribute("neuesBild", bild);

        return "startseite";
    }

    // Bild hochladen Methode
    @RequestMapping(value = "bildhochladen")
    public String bildHochladen(
            Model model,
            @ModelAttribute ("neuesBild") Bild bild) {

            bildRepository.save(bild);



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


    @GetMapping(value = "/zeigeCheckout")
    public String zeigeCheckout(){
        return "checkout";
    }







}