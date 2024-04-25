package words;

import java.util.ArrayList;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Word {
    private final String word;
    private final String translation;
    private ArrayList<String> translations;

    public Word(String word, String translation, ArrayList<String> translations) {
        this.word = word;
        this.translation = translation;
        this.translations = translations;
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public ArrayList<String> getTranslations() {
        return translations;
    }

    public void setTranslations(ArrayList<String> translations) {
        this.translations = translations;
    }
}