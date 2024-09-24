package org.translation;

import java.util.ArrayList;
import java.util.List;

// Extra Task: if your group has extra time, you can add support for another country code in this class.

/**
 * An implementation of the Translator interface which translates
 * the country code "can" to several languages.
 */
public class InLabByHandTranslator implements Translator {

    public static final String CANADA = "can";
    public static final String GERMAN = "de";
    public static final String CHINESE = "zh";
    public static final String ENGLISH = "en";
    public static final String SPANISH = "es";
    public static final String HEBREW = "he";
    public static final String ARABIC = "ar";
    public static final String FRENCH = "fr";
    public static final String BELARUSIAN = "be";
    public static final String JAPANESE = "ja";

    /**
     * Returns the language abbreviations for all languages whose translations are
     * available for the given country.
     *
     * @param country the country
     * @return list of language abbreviations which are available for this country
     */
    @Override
    public List<String> getCountryLanguages(String country) {
        if (CANADA.equals(country)) {
            return new ArrayList<>(List.of(GERMAN, ENGLISH, CHINESE, SPANISH, HEBREW, ARABIC, FRENCH, BELARUSIAN, JAPANESE));
        }
        return new ArrayList<>();
    }

    /**
     * Returns the country abbreviations for all countries whose translations are
     * available from this Translator.
     *
     * @return list of country abbreviations for which we have translations available
     */
    @Override
    public List<String> getCountries() {
        return new ArrayList<>(List.of("can"));
    }

    /**
     * Returns the name of the country based on the specified country abbreviation and language abbreviation.
     *
     * @param country  the country
     * @param language the language
     * @return the name of the country in the given language or null if no translation is available
     */
    @Override
    public String translate(String country, String language) {
        String value = null;
        if (country.equals(CANADA)) {
            switch (language) {
                case GERMAN:
                    value = "Kanada";
                    break;
                case ENGLISH:
                case FRENCH:
                    value = "Canada";
                    break;
                case CHINESE:
                    value = "加拿大";
                    break;
                case SPANISH:
                    value = "Canadá";
                    break;
                case HEBREW:
                    value = "קנדה";
                    break;
                case ARABIC:
                    value = "كندا";
                    break;
                case BELARUSIAN:
                    value = "Канада";
                    break;
                case JAPANESE:
                    value = "カナダ";
                    break;
                default:
                    break;
            }
        }
        return value;
    }
}
