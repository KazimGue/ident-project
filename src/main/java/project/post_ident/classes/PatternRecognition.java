package project.post_ident.classes;

import java.util.regex.Pattern;

public class PatternRecognition {

    private static final Pattern[] inputRegexes = new Pattern[1];

    static {
        inputRegexes[0]=Pattern.compile(".*\\d.*");
        /*inputRegexes[0] = Pattern.compile(".*[A-Z].*");
        inputRegexes[1] = Pattern.compile(".*[a-z].*");
        inputRegexes[2] = Pattern.compile(".*\\d.*");
        inputRegexes[3] = Pattern.compile(".*[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*");*/
    }

    private static boolean isMatchingRegex(String input) {
        boolean inputMatches = true;
        for (Pattern inputRegex : inputRegexes) {
            if (!inputRegex.matcher(input).matches()) {
                inputMatches = false;
            }
        }
        return inputMatches;
    }

    public static boolean allesInEinerMethode(String input){

        Pattern[] inputRegexes = new Pattern[1];

        inputRegexes[0]=Pattern.compile(".*\\d.*");
        isMatchingRegex(input);
        boolean inputMatches = true;
        System.out.println("Boolean Pattern: " +inputMatches);
        return inputMatches;
    }
}
