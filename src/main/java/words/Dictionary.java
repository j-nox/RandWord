package words;

import com.jayway.jsonpath.JsonPath;

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private int score = 0;
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

    public static String getWord() {
        String word = words.get(2);
        return word;
    }

    public static String getTranslation() {
        String translation = translations.get(2);
        return translation;
    }

    public int getScore() {
        return score;
    }

}