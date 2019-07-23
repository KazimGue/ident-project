package project.post_ident.repository;

import project.post_ident.classes.ImageFilters;
import project.post_ident.classes.PatternRecognition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static project.post_ident.classes.BildZuschneiden.bildZuschneiden;

public class DummyMain {
    public static void main(String[] args) {

        /*String result=" Namslsnmamn/Nom -\n" +
                "MUSTE RMAN N\n" +
                "\n" +
                "(SE B . GA BLER\n" +
                "Vomameaniven names/Prénoms\n" +
                "E R I KA\n" +
                "\n";*/

       /*String result="5:33.333]; 23:; :1 hiﬂh/ ﬁlaﬁiagﬁgR&Bhal’iakeﬂlNaﬂonalﬂyl 12 . 08 .1964 DEUTSCH Geburlwﬂ/Place of hll’ilI/Lleu de nalmnm B E R L I N";*/
        String result="Berlin12. 08. 2 0 19  dchksdvBERLINhj";
        String result2=result.replaceAll("\\s",""); ;
        System.out.println(result2);

    /*    PatternRecognition.allesInEinerMethode(result2);
        Pattern pattern = Pattern.compile("Prénoms");
        Matcher matcher = pattern.matcher(result2);
        System.out.println(matcher.find());
        System.out.println(matcher.start()+"   " +matcher.end());

        Pattern pattern = Pattern.matches("[789]{1}[0-9]{9}",result2);
        String result3=result2.substring(matcher.start(),matcher.end());
        System.out.println(result3);
        System.out.println(Pattern.matches("[789]{1}[0-9]{9}", result2));*/

         /*Pattern DATE_PATTERN = Pattern.compile("^.*?\\d{2}.\\d{2}.\\d{4}.*$");*/
         Pattern DATE_PATTERN = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})");

       /* System.out.println(DATE_PATTERN.matcher(result2).matches());
        Matcher replaced2 = DATE_PATTERN.matcher(result2);
        System.out.println(replaced2.group(1));*/

        String line = result2;
        Pattern pattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})");
        Matcher m = pattern.matcher(line);

        if (m.find()) { System.out.println("Found value => " + m.group(1)); }

        String sampleDate="svajsc12.08.1973DEUTSCH";
        Pattern pattern2 = Pattern.compile("(\\d{2}.\\d{2}.\\d{4})");
        Matcher m2 = pattern2.matcher(sampleDate);
        if (m2.find()) {
            String datum=m2.group(1);
            /*System.out.println("Geburtsdatun: " +datum);*/
        }

        /*Pattern patternUC=Pattern.compile("^.*?([A-Z][a-z].*)$");
        Matcher matchUC=patternUC.matcher(result);
        String result4=matchUC.group();
        System.out.println(result4);*/

       /* Pattern pattern = Pattern.compile("^.*?([A-Z][a-z].*)$");
        String original = "THIS IS A TEST - - +++ This is a test";
        String replaced = pattern.matcher(original).replaceAll("$1");
        System.out.println(replaced);*/

      /*  Pattern pattern2 = Pattern.compile("^.*?([[A-Z][A-Z]]2+).*$");
        String original2 = result2;
        String replaced2 = pattern2.matcher(original2).replaceAll("$1");
        System.out.println(pattern2.matcher(result2).matches());
        System.out.println(replaced2);
        String group = pattern2.matcher(original2).group();
        System.out.println(pattern2.matcher(result).group(1));*/

        /*Pattern pattern3 = Pattern.compile("^.*?(\\d{2}.\\d{2}.\\d{4}\\*)$");
        String original3 = result;
        String replaced3 = pattern3.matcher(original3).replaceAll("$1");
        System.out.println(replaced3);*/


        String olli= "OCR Result1: LLJLJSMW/Nom\n" +
                "\n" +
                "HOCK\n" +
                "\n" +
                "Vomamen/leen names/Pvénams\n" +
                "OLIVER LUDGER\n" +
                "\n" +
                "\n" +
                "OCR Result2: 8:&“§§E§£E§Aﬁ:‘b\"‘\"’ aLﬁzsaé-‘ewgiééf‘fwwﬁ?’\n" +
                "13.08.1971 DEUTSCH :\n" +
                "Geburlsorl/Piace ca binn/Lteu de nalssaﬁce ' L f: ,\n" +
                "BECKUM _' ‘ >\n" +
                "Sulliq hIs/Dake u! exnlvyl _ L»";
    }
}
