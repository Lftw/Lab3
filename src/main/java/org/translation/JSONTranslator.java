package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    // This maps alpha3 code -> (Map from lang code -> country name)
    private final Map<String, Map<String, String>> translatorMapper = new HashMap<>();

    /**
     * Constructs a JSONTranslator using data from the sample.json resources file.
     */
    public JSONTranslator() {
        this("sample.json");
    }

    /**
     * Constructs a JSONTranslator populated using data from the specified resources file.
     * @param filename the name of the file in resources to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public JSONTranslator(String filename) {
        // read the file to get the data to populate things...
        try {

            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));

            JSONArray jsonArray = new JSONArray(jsonString);

            for (Object baseJsonObj : jsonArray) {
                JSONObject jsonObject = (JSONObject) baseJsonObj;
                String alpha3 = jsonObject.getString("alpha3");
                Map<String, String> langMapping = new HashMap<>();
                for (String key : jsonObject.keySet()) {
                    if ("alpha2".equals(key) || "alpha3".equals(key) || "id".equals(key)) {
                        continue;
                    }
                    langMapping.put(key, jsonObject.getString(key));
                }
                this.translatorMapper.put(alpha3, langMapping);
            }

        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        return List.copyOf(this.translatorMapper.get(country).keySet());
    }

    @Override
    public List<String> getCountries() {
        return List.copyOf(this.translatorMapper.keySet());
    }

    @Override
    public String translate(String country, String language) {
        return this.translatorMapper.get(country).get(language);
    }
}
