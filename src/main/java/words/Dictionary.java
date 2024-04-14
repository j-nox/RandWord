package words;

import com.jayway.jsonpath.JsonPath;

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.*;

public class Dictionary {
    private static String json;
    private static File file = new File("/Users/web/IdeaProjects/RandWord/src/main/webapp/WEB-INF/en5000.json");
    private static List<String> words = new ArrayList<String>();
    private static List<String> translations = new ArrayList<String>();

    public Dictionary() {
        try {
            json = new String(Files.readAllBytes(file.toPath()));
            words = JsonPath.read(json, "$.store.book[*].word");
            translations = JsonPath.read(json, "$.store.book[*].rus");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, ArrayList<String>> getWord() {
        ArrayList<String> randTranslations = new ArrayList<String>();
        Random random = new Random();
        int randomIndex = random.nextInt(5001);
        String randWord = words.get(randomIndex);
        randTranslations.add(translations.get(randomIndex));
        for (int i = 0; i < 4; i++) {
            randomIndex = random.nextInt(5001);
            randTranslations.add(translations.get(randomIndex));
        }
        Collections.shuffle(randTranslations);
        HashMap<String, ArrayList<String>> word = new HashMap<>();
        word.put(randWord, randTranslations);

        return word;
    }

    public static String getTranslation() {
        String translation = translations.get(2);
        return translation;
    }

    public List<String> getWords() {
        return words;
    }

    public List<String> getTranslations() {
        return translations;
    }

    public boolean checkWord(String currentWord, String translate) {
        int wordIndex = words.indexOf(currentWord);
        int translateIndex = translations.indexOf(translate);
        if (wordIndex == translateIndex) {
            return true;
        } else {
            return false;
        }
    }

}