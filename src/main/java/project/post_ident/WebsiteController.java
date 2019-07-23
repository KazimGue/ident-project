package project.post_ident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.post_ident.classes.BildHochladenLogik;
import project.post_ident.classes.OCRResultObject;
import project.post_ident.entities.Personendaten;
import project.post_ident.entities.TempPersonendaten;
import project.post_ident.repository.PersonenDatenRepository;
import project.post_ident.repository.TempPersonenDatenRepository;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.post_ident.entities.Bild;
import project.post_ident.repository.BildRepository;


import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class WebsiteController {

    @Autowired
    private PersonenDatenRepository personenDatenRepository;

    @Autowired
    private TempPersonenDatenRepository tempPersonenDatenRepository;

    @Autowired
    private BildRepository bildRepository;

    // Startseite öffnen
    @GetMapping(value = "/")
    public String startSeiteOeffnen(Model model) throws IOException {

        Bild bild = new Bild();
        model.addAttribute("neuesBild", bild);

        return "startseite";
    }

    // Bild hochladen Methode
    @RequestMapping(value = "bildhochladen")
    public String bildHochladen(Model model) throws IOException {

        BildHochladenLogik dummy= new BildHochladenLogik();
        TempPersonendaten gescannteDaten=dummy.bildHochladenLogik();

        tempPersonenDatenRepository.save(gescannteDaten);

       List<TempPersonendaten> tempPersonenDatenListe = tempPersonenDatenRepository.findAll();
        model.addAttribute("tempPersonenDatenListe", tempPersonenDatenListe);
        model.addAttribute("originalPerson", new Personendaten());
        return "vergleich";
    }


    @PostMapping("/filehochladen") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        //Save the uploaded file to this folder
        String UPLOADED_FOLDER = "src\\main\\resources\\static\\images\\";


        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("/message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
             Files.write(path, bytes);
            //Umbenennung des Bildes
            Files.move(path, path.resolveSibling("persoUpload.png"));

            redirectAttributes.addFlashAttribute("successMessage",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }


        Bild bild = new Bild();
        bild.setName(file.getOriginalFilename());

        bildRepository.save(bild);

        return "redirect:/bildhochladen";
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
        neuePerson.setGeburtstag(tempPersonendaten.getGeburtstag());

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
        originalPerson.setGeburtstag(tempPersonenDatenListe.get(0).getGeburtstag());
        originalPerson.setStrasse(tempPersonenDatenListe.get(0).getStrasse());
        originalPerson.setHausnummer(tempPersonenDatenListe.get(0).getHausnummer());
        originalPerson.setPlz(tempPersonenDatenListe.get(0).getPlz());
        originalPerson.setStadt(tempPersonenDatenListe.get(0).getStadt());

        personenDatenRepository.save(originalPerson);

        return "checkout";
    }

    @GetMapping(value = "/zeigeCheckout")
    public String zeigeCheckout() throws IOException {

        return "checkout";
    }






}