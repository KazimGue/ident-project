package project.post_ident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.post_ident.classes.BildHochladenLogik;
import project.post_ident.entities.Personendaten;
import project.post_ident.entities.TempPersonendaten;
import project.post_ident.repository.PersonenDatenRepository;
import project.post_ident.repository.TempPersonenDatenRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.post_ident.entities.Bild;
import project.post_ident.repository.BildRepository;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Controller
public class WebsiteController {

    @Autowired
    private PersonenDatenRepository personenDatenRepository;

    @Autowired
    private TempPersonenDatenRepository tempPersonenDatenRepository;

    @Autowired
    private BildRepository bildRepository;



    Long tempID;
    String pathname;
    String filename;

    // Startseite öffnen
    @GetMapping(value = "/")
    public String startSeiteOeffnen(Model model) {
        File file = new File("src\\main\\resources\\static\\images\\persoUpload.png");
        /*File file = new File("src\\main\\resources\\static\\images\\persoUpload.png");*/
        file.delete();

        Bild bild = new Bild();
        model.addAttribute("neuesBild", bild);
        tempPersonenDatenRepository.deleteAll();
        return "startseite";
    }

    // Bild hochladen Methode
    @RequestMapping(value = "bildhochladen")
    public String bildHochladen(Model model) throws IOException {

        BildHochladenLogik dummy= new BildHochladenLogik();
        TempPersonendaten gescannteDaten=dummy.bildHochladenLogik(pathname);

        tempPersonenDatenRepository.save(gescannteDaten);

        //Finde ID der Person in Datenbank
        ArrayList<TempPersonendaten> tempPersonendatenList;
        //Repository.findAll Ergebnis wird in eine ArrayList gecastet
        tempPersonendatenList= (ArrayList<TempPersonendaten>) tempPersonenDatenRepository.findAll();
        tempID=tempPersonendatenList.get(0).getPersonID();


        Optional<TempPersonendaten> tempPersonendaten=tempPersonenDatenRepository.findById(tempID);
        TempPersonendaten tempPersonendaten2=tempPersonendaten.get();
        System.out.println(pathname);
        model.addAttribute("pathname",pathname);
        String source="/images/"+filename;
        model.addAttribute("filename",filename);
        model.addAttribute("source",source);
        model.addAttribute("tempPersonendaten2", tempPersonendaten2);
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
            String rndName= UUID.randomUUID().toString();
            filename=rndName+"Upload.png";
            pathname=UPLOADED_FOLDER+filename;
            Files.move(path, path.resolveSibling(filename));

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

       // String source="/images/"+filename;
        model.addAttribute("filename",filename);


        try {
            ArrayList<TempPersonendaten> tempPersonendatenList;
            //Repository.findAll Ergebnis wird in eine ArrayList gecastet
            tempPersonendatenList= (ArrayList<TempPersonendaten>) tempPersonenDatenRepository.findAll();
            tempID=tempPersonendatenList.get(0).getPersonID();

            /*TempPersonendaten tempPersonendaten=tempPersonendaten1.get();*/
            Optional<TempPersonendaten> tempPersonendaten = tempPersonenDatenRepository.findById(tempID);
            TempPersonendaten tempPersonendaten1=tempPersonendaten.get();
            model.addAttribute("tempPersonenDaten", tempPersonendaten1);
        } catch (Exception e) {
           return "startseite";
        }


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
        neuePerson.setNationalitaet(tempPersonendaten.getNationalitaet());
        neuePerson.setAdresse(tempPersonendaten.getAdresse());
        neuePerson.setPlz(tempPersonendaten.getPlz());
        neuePerson.setGeburtsort(tempPersonendaten.getGeburtsort());
        neuePerson.setGeburtstag(tempPersonendaten.getGeburtstag());

       personenDatenRepository.save(neuePerson);
       tempPersonenDatenRepository.deleteAll();

        return "checkout";
    }

    @PostMapping(value ="originalSpeichern")
    public String originalDatenSpeichern(Model model,
         @ModelAttribute("originalPerson") Personendaten originalPerson){

        //Finde ID des Datensatzes in DB
        ArrayList<TempPersonendaten> tempPersonendatenList;
           //Repository.findAll Ergebnis wird in eine ArrayList gecastet
            tempPersonendatenList= (ArrayList<TempPersonendaten>) tempPersonenDatenRepository.findAll();
            tempID=tempPersonendatenList.get(0).getPersonID();

        Optional<TempPersonendaten> tempPersonendaten = tempPersonenDatenRepository.findById(tempID);
        TempPersonendaten tempPersonendaten1=tempPersonendaten.get();
        model.addAttribute("tempPersonenDaten", tempPersonendaten1);

        originalPerson.setVorname(tempPersonendaten1.getVorname());
        originalPerson.setNachname(tempPersonendaten1.getNachname());
        originalPerson.setPersoNr(tempPersonendaten1.getPersoNr());
        originalPerson.setGeburtstag(tempPersonendaten1.getGeburtstag());
        originalPerson.setNationalitaet(tempPersonendaten1.getNationalitaet());
        originalPerson.setAdresse(tempPersonendaten1.getAdresse());
        originalPerson.setPlz(tempPersonendaten1.getPlz());
        originalPerson.setGeburtsort(tempPersonendaten1.getGeburtsort());

        personenDatenRepository.save(originalPerson);
        tempPersonenDatenRepository.deleteAll();

        return "checkout";
    }

    @GetMapping(value = "/zeigeCheckout")
    public String zeigeCheckout(){
        return "checkout";
    }






}