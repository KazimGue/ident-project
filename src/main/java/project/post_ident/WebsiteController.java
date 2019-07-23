package project.post_ident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.post_ident.classes.OCRResultObject;
import project.post_ident.entities.Personendaten;
import project.post_ident.entities.TempPersonendaten;
import project.post_ident.repository.PersonenDatenRepository;
import project.post_ident.repository.TempPersonenDatenRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.post_ident.entities.Bild;
import project.post_ident.repository.BildRepository;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    // Startseite öffnen
    @GetMapping(value = "/")
    public String startSeiteOeffnen(Model model) {
        Bild bild = new Bild();
        model.addAttribute("neuesBild", bild);
        return "startseite";
    }

    // Bild hochladen Methode
    @RequestMapping(value = "bildhochladen")
    public String bildHochladen(Model model) throws IOException {

        OCRResultObject ocrResultObject = getResult();
        String resultOCR = ocrResultObject.getResult();
        String resultOCR2 = ocrResultObject.getResult2();
        System.out.println(resultOCR+resultOCR2);

        // Namen und Vornamen in einzelne Strings umwandeln
        String vornameString = resultOCR;

        String[] vorname = vornameString.split("\\r?\\n");

        ArrayList<String> daten = new ArrayList<>();

        for(String a : vorname){
            System.out.println("vorher " + a);
            String[] inhalt = a.split("(?=[a-z])");

            for (String b : inhalt) {
                System.out.println("For Schleife " + b);
                if (b.length() > 3) {
                    daten.add(b);
                }
            }
        }

        String nachname = "";
        String geborenNamen = "";
        String name = "";

        if(daten.size() <= 2){
            nachname = daten.get(0);
            nachname = nachname.replaceAll(" \\s", "");
            System.out.println("Nachname nach leerzeichen bei 2: " + nachname);

            name = daten.get(1);
            name = name.replaceAll("\\s","");
            System.out.println("Name nach leerzeichen bei 2: " + name);

        } else if(daten.size() <=3){
            nachname = daten.get(0);
            nachname = nachname.replaceAll("\\s","");
            System.out.println("Nachname nach leerzeichen bei 3: " + nachname);

            geborenNamen = daten.get(1);
            geborenNamen = geborenNamen.replaceAll("\\s", "");
            geborenNamen = geborenNamen.replaceAll("\\(","");
            System.out.println("GeborenName nach leerzeichen bei 3: " + geborenNamen);

            name = daten.get(2);
            name = name.replaceAll("\\s", "");
            System.out.println("Vorname nach leerzeichen bei 3: " + name);
        }

        // Datum zuordnen
        String datum = "";
        String sampleDate=resultOCR2.replaceAll("\\s", "");
        Pattern pattern2 = Pattern.compile("(\\d{2}.\\d{2}.\\d{4})");
        Matcher m2 = pattern2.matcher(sampleDate);
        if (m2.find()) {
            datum=m2.group(1);
            System.out.println("Geburtsdatun: " +datum);
        }







        TempPersonendaten gescannteDaten = new TempPersonendaten();
        gescannteDaten.setNachname(nachname);
        gescannteDaten.setVorname(name);
        gescannteDaten.setPersoNr("Personummer");
        gescannteDaten.setGeburtstag(datum);
        gescannteDaten.setStrasse("Straße");
        gescannteDaten.setHausnummer("HausNr");
        gescannteDaten.setPlz(12345);
        gescannteDaten.setStadt("Stadt");

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
        originalPerson.setGeburtstag(tempPersonenDatenListe.get(0).getGeburtstag());
        originalPerson.setStrasse(tempPersonenDatenListe.get(0).getStrasse());
        originalPerson.setHausnummer(tempPersonenDatenListe.get(0).getHausnummer());
        originalPerson.setPlz(tempPersonenDatenListe.get(0).getPlz());
        originalPerson.setStadt(tempPersonenDatenListe.get(0).getStadt());

        personenDatenRepository.save(originalPerson);


        return "checkout";
    }

    @GetMapping(value = "/zeigeCheckout")
    public String zeigeCheckout(){
        return "checkout";
    }


    @GetMapping (value = "/ocrResult")
    public String ocrImage(Model model) throws IOException {


       OCRResultObject ocrResultObject =getResult();
       String resultOCR=ocrResultObject.getResult();
       String resultOCR2=ocrResultObject.getResult2();

       model.addAttribute("ocrResult",resultOCR);
       model.addAttribute("ocrResult2",resultOCR2);
        System.out.println("OCR Result1: " +resultOCR);
        System.out.println("OCR Result2: " +resultOCR);


        return "ocrResult";


    }





}