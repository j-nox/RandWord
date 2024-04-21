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
    private static File file = new File("/Users/web/IdeaProjects/RandWord/src/main/resources/en5000.json");
    // Список англиских слов
    private static List<String> words = new ArrayList<String>();
    // Список переводов
    private static List<String> translations = new ArrayList<String>();

    public static Dictionary getInstance() {
        // Синглтон
        if (INSTANCE == null) {
            INSTANCE = new Dictionary();
        }
        return INSTANCE;
    }

    private Dictionary() {
        // Первоначальное наполнение списков одним словом и одним переводом
        words.add("World");
        translations.add("Мир");
        // Создание потока для наполнения списка слов
        Runnable task = new Runnable() {
            public void run() {
                // Пробуем распарсить json файл
                try {
                    json = new String(Files.readAllBytes(file.toPath()));
                    synchronized (words) {
                        // Наполняем список английских слов
                        words = JsonPath.read(json, "$.store.book[*].word");
                    }
                    synchronized (translations) {
                        // Наполняем список переводов к английским словам
                        translations = JsonPath.read(json, "$.store.book[*].rus");
                    }
                    // Пишем в лог, что списки наполнены
                    LOG.info("Dictionary has been created");
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
    public HashMap<String, ArrayList<String>> getWord() {
        ArrayList<String> randTranslations = new ArrayList<String>();
        Random random = new Random();
        String randWord;
        int randomIndex;
        // Захватываем список words
        synchronized (words) {
            // Если поток не завершен
            if (thread.isAlive()) {
                randomIndex = 0;
                randWord = words.get(randomIndex);
            // Если поток завершен
            } else {
                randomIndex = random.nextInt(words.size());
                randWord = words.get(randomIndex);
            }
        }
        // Захватываем список translations
        synchronized (translations) {
            // Помещаем в ответ правильный перевод слов
            randTranslations.add(translations.get(randomIndex));
            // Помещаем в ответ 4 неправильных переводов слова
            for (int i = 0; i < 4; i++) {
                randomIndex = random.nextInt(translations.size());
                randTranslations.add(translations.get(randomIndex));
            }
        }
        // Перемешиваем список переводов к слову
        Collections.shuffle(randTranslations);
        HashMap<String, ArrayList<String>> word = new HashMap<>();
        // Возвращаем английское слово и 5 переводов к нему
        word.put(randWord, randTranslations);

        return word;
    }
    // Проверка перевода слова
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