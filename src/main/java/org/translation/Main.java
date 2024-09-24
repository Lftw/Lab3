package org.translation;

import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Main class for this program.
 * Complete the code according to the "to do" notes.<br/>
 * The system will:<br/>
 * - prompt the user to pick a country name from a list<br/>
 * - prompt the user to pick the language they want it translated to from a list<br/>
 * - output the translation<br/>
 * - at any time, the user can type quit to quit the program<br/>
 */
public class Main {

    private static final String QUIT_COMMAND = "quit";

    /**
     * This is the main entry point of our Translation System!<br/>
     * A class implementing the Translator interface is created and passed into a call to runProgram.
     * @param args not used by the program
     */
    public static void main(String[] args) {
        Translator translator = new JSONTranslator("sample.json");

        runProgram(translator);
    }

    /**
     * This is the method which we will use to test your overall program, since
     * it allows us to pass in whatever translator object that we want!
     * See the class Javadoc for a summary of what the program will do.
     * @param translator the Translator implementation to use in the program
     */
    public static void runProgram(Translator translator) {
        while (true) {
            String country = promptForCountry(translator);
            if (QUIT_COMMAND.equals(country)) {
                break;
            }
            CountryCodeConverter countryConverter = new CountryCodeConverter();
            String language = promptForLanguage(translator, countryConverter.fromCountry(country));
            if (QUIT_COMMAND.equals(language)) {
                break;
            }
            LanguageCodeConverter languageConverter = new LanguageCodeConverter();
            System.out.println(country + " in " + language + " is " + translator.translate(countryConverter.fromCountry(country), languageConverter.fromLanguage(language)));
            System.out.println("Press enter to continue or quit to exit.");
            Scanner s = new Scanner(System.in);
            String textTyped = s.nextLine();

            if (QUIT_COMMAND.equals(textTyped)) {
                break;
            }
        }
    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForCountry(Translator translator) {
        CountryCodeConverter converter = new CountryCodeConverter();
        String countries = translator.getCountries()
                .stream()
                .map(converter::fromCountryCode)
                .sorted()
                .collect(Collectors.joining("\n"));
        System.out.println(countries);

        System.out.println("select a country from above:");

        Scanner s = new Scanner(System.in);
        return s.nextLine();

    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForLanguage(Translator translator, String country) {

        LanguageCodeConverter converter = new LanguageCodeConverter();
        System.out.println(country);
        String languageNames = translator.getCountryLanguages(country)
                .stream()
                .map(converter::fromLanguageCode)
                .sorted()
                .collect(Collectors.joining("\n"));
        System.out.println(languageNames);

        System.out.println("select a language from above:");

        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
