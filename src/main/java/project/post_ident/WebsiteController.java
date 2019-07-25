package project.post_ident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import project.post_ident.classes.BildHochladenLogik;
import project.post_ident.classes.OCRResultObject;
import project.post_ident.entities.Personendaten;
import project.post_ident.entities.TempPersonendaten;
import project.post_ident.repository.PersonenDatenRepository;
import project.post_ident.repository.TempPersonenDatenRepository;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.post_ident.entities.Bild;
import project.post_ident.repository.BildRepository;


import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static project.post_ident.classes.Tess4J.getResult;


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
    String path;

    private static final String fileSeparator = System.getProperty("file.separator");

    private Path uploadFolder;

    @PostConstruct
    public void init() {
        path = System.getProperty("user.home") + fileSeparator + "uploads";
        uploadFolder = Paths.get(path);
        try {
            Files.createDirectories(uploadFolder);
        } catch (IOException e) {
            throw new RuntimeException("Could not create uploads directory.", e);
        }
    }


    // Startseite öffnen
    @GetMapping(value = "/")
    public String startSeiteOeffnen(Model model) {

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
    public String singleFileUpload(Model model,@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException {



        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        pathname=path+fileSeparator+fileName;

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





        InputStream inputStream = file.getInputStream();
        Files.copy(inputStream, uploadFolder.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

        model.addAttribute("targetFileName", "readImage/" + fileName);
        return "vergleich";
    }


    @GetMapping("/readImage/{imageName}")
    @ResponseBody
    public byte[] readImage(@PathVariable(value = "imageName") String imageName) throws IOException {

        File file = new File(uploadFolder + fileSeparator + imageName);
        byte[] bytes = Files.readAllBytes(file.toPath());

        return bytes;
    }

    //Bringt zu der HTML-Seite, auf der ein neues Objekt hinzugefügt wird
    @GetMapping(value = "/datenAendern")
    public String personenDatenAendern(Model model) {


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
        neuePerson.setStrasse(tempPersonendaten.getStrasse());
        neuePerson.setHausnummer(tempPersonendaten.getHausnummer());
        neuePerson.setPlz(tempPersonendaten.getPlz());
        neuePerson.setStadt(tempPersonendaten.getStadt());
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
        originalPerson.setStrasse(tempPersonendaten1.getStrasse());
        originalPerson.setHausnummer(tempPersonendaten1.getHausnummer());
        originalPerson.setPlz(tempPersonendaten1.getPlz());
        originalPerson.setStadt(tempPersonendaten1.getStadt());

        personenDatenRepository.save(originalPerson);
        tempPersonenDatenRepository.deleteAll();

        return "checkout";
    }

    @GetMapping(value = "/zeigeCheckout")
    public String zeigeCheckout(){
        return "checkout";
    }






}