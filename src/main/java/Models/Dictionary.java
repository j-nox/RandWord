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
    // Синглтон
    private static Dictionary INSTANCE;
    private static String json;
    // Путь к json файлу с массивом слов
    private static File file = new File("/Users/web/IdeaProjects/RandWord/src/main/resources/englishWords50003.json");
    // Список англиских слов
    private static List<String> words = new ArrayList<String>();
    // Список переводов
    private static List<String> translations = new ArrayList<String>();
    private static List<HashMap<String, String>> englishWordsRaw;
    private static ArrayList<words.Word> listEnglishWords = new ArrayList<>();

    public static Dictionary getInstance() {
        // Синглтон
        if (INSTANCE == null) {
            INSTANCE = new Dictionary();
        }
        return INSTANCE;
    }

    private Dictionary() {
        // Первоначальное наполнение списков одним словом и одним переводом
        ArrayList<String> randTranslations2 = new ArrayList<String>();
        listEnglishWords.add(new Word("World", "Мир", randTranslations2));
        // Создание потока для наполнения списка слов
        Runnable task = new Runnable() {
            public void run() {
                // Пробуем распарсить json файл
                try {
                    json = new String(Files.readAllBytes(file.toPath()));
                    englishWordsRaw = JsonPath.read(json, "$.store.words[*]");
                    for (HashMap<String, String> map : englishWordsRaw) {
                        Map.Entry<String,String> entry = map.entrySet().iterator().next();
                        listEnglishWords.add(new words.Word(entry.getKey(), entry.getValue(), randTranslations2));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    // Выводим ошибки в лог
                    LOG.error(e.toString());
                }
            }
        };
        // Запускаем поток
        thread = new Thread(task);
        thread.start();
    }
    // Метод для получения случайного слова из списка инглийских слов
    public Word getWord() {
        ArrayList<String> randTranslations = new ArrayList<String>();
        Random random = new Random();
        int randomIndex;
        randomIndex = random.nextInt(listEnglishWords.size());

        // Возвращаем английское слово и 5 переводов к нему
        Word resultWord = listEnglishWords.get(randomIndex);
        randTranslations.add(resultWord.getTranslation());
        for (int i = 0; i < 4; i++) {
            randomIndex = random.nextInt(listEnglishWords.size());
            Word translation = listEnglishWords.get(randomIndex);
            randTranslations.add(translation.getTranslation());
        }

        resultWord.setTranslations(randTranslations);

        return resultWord;
    }

    // Проверка перевода слова
    public boolean checkWord(String currentWord, String translate) {
        boolean result = false;
        for (Word word : listEnglishWords) {
            if ((word.getWord().equals(currentWord)) & (word.getTranslation().equals(translate))) {
                result = true;
            }
        }
        return result;
    }

}