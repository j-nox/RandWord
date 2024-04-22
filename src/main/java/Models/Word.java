package words;

import java.util.ArrayList;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Word {
    private String word;
    private ArrayList<String> translations;

    public Word(String word, ArrayList<String> translations) {
        this.word = word;
        this.translations = translations;
    }

    public String getWord() {
        return word;
    }

    public ArrayList<String> getTranslations() {
        return translations;
    }
}
