package project.post_ident.classes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class OCRResultObject {

    String result = null;
    String result2 = null;

    public OCRResultObject(String result, String result2) {
        this.result = result;
        this.result2 = result2;
    }


    public static void vorName (){
        // VERSUCH String zu teilen
        String vornameString = "Namslsnmamn/Nom - MUSTE RMAN N (SE B . GA BLER Vomameaniven names/Prénoms E R I KA";
        vornameString = vornameString.replaceAll("\\s","");


        // split Zeilenumbrüche: ("\\r?\\n")
        // split bestimmte Zeichen: ("([/(\\-])")
        String[] vorname = vornameString.split("([/(\\-])");
        System.out.println(vorname);

//        for (String a : vorname) {
//            String[] inh = a.split("(?=[a-z])");
//            for(String b:inh) {
//                System.out.println("For Schleife " + b);
//                if (b.length()>=3){
//                    System.out.println("Vorname ");
//                }
//

//        String str = Arrays.toString(vorname);
//        System.out.println(str);
//        String[] inhalt = str.split("(?=[a-z])");
//
        // (?=[a-z])
        ArrayList<String> daten = new ArrayList<>();
//
//        String[] inh = null;
//        for (String a : vorname) {
//             inh = a.split("(?=[a-z])");
//            System.out.println(inh);
//        }
        for(String a : vorname){
            System.out.println("vorher " + a);
            String[] inhalt = a.split("(?=[a-z])");

        for (String b : inhalt) {
                System.out.println("For Schleife " + b);
                if (b.length() > 5) {
                    daten.add(b);

//                    for(int i=0; i<b.length();i++) {
//                        daten[i] = new String(b);
//                    }

                }
        }
        }

        if(daten.size() <= 2){
            System.out.println("Nachname "+ daten.get(0));
            System.out.println("Vorname " + daten.get(1)) ;
        } else if(daten.size() <=3){
            System.out.println("Nachname " + daten.get(0) + " " + daten.get(1));
            System.out.println("Vorname "+ daten.get(2));
        }






//        vornameString = vornameString.replaceAll("\\s","");
//
//        System.out.println(vornameString);
//
//        for(int i = 0; i < vornameString.length(); i++) {
//            Character ch = vornameString.charAt(i);
//                if (Character.isUpperCase(ch)) {
//                    vorname += "" + ch;
//                }
//
//        }
//        System.out.println("Vorname lautet " + vorname);

    }



    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }
}
