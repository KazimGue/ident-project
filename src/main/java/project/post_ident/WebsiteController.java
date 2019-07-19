package project.post_ident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.post_ident.entities.Bild;
import project.post_ident.repository.BildRepository;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @PostMapping(value = "bildhochladen")
    public String bildHochladen(
            Model model,
            @ModelAttribute ("neuesBild") Bild bild) {

        //Save the uploaded file to this folder
        String UPLOADED_FOLDER = "C:\\Users\\kcoep\\";

        try {
            // Get the file and save it somewhere
            byte[] bytes = bild.getBild();
            Path path = Paths.get(UPLOADED_FOLDER + bild.getName());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

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