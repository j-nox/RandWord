package words;

import com.jayway.jsonpath.JsonPath;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dictionary {
    private static final Logger LOG = LogManager.getLogger(Dictionary.class);
    Thread thread;
    private static Dictionary INSTANCE;
    private static String json;
    private static File file = new File("/Users/web/IdeaProjects/RandWord/src/main/resources/en5000.json");
    private static List<String> words = new ArrayList<String>();
    private static List<String> translations = new ArrayList<String>();

    public static Dictionary getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Dictionary();
        }
        return INSTANCE;
    }

    private Dictionary() {
        words.add("World");
        translations.add("Мир");
        Runnable task = new Runnable() {
            public void run() {
                try {
                    json = new String(Files.readAllBytes(file.toPath()));
                    synchronized (words) {
                        words = JsonPath.read(json, "$.store.book[*].word");
                    }
                    synchronized (translations) {
                        translations = JsonPath.read(json, "$.store.book[*].rus");
                    }
                    LOG.info("Dictionary has been created");
                } catch (Exception e) {
                    e.printStackTrace();
                    LOG.error(e.toString());
                }
            }
        };

        thread = new Thread(task);
        thread.start();
    }

    public HashMap<String, ArrayList<String>> getWord() {

        ArrayList<String> randTranslations = new ArrayList<String>();
        Random random = new Random();
        String randWord;
        int randomIndex;
        synchronized (words) {
            if (thread.isAlive()) {
                randomIndex = 0;
                randWord = words.get(randomIndex);
            } else {
                randomIndex = random.nextInt(words.size());
                randWord = words.get(randomIndex);
            }
        }
        synchronized (translations) {
            randTranslations.add(translations.get(randomIndex));
            for (int i = 0; i < 4; i++) {
                randomIndex = random.nextInt(translations.size());
                randTranslations.add(translations.get(randomIndex));
            }
        }
        Collections.shuffle(randTranslations);
        HashMap<String, ArrayList<String>> word = new HashMap<>();
        word.put(randWord, randTranslations);

        return word;
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