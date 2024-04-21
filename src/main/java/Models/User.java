package words;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class User {
    private static final Logger LOG = LogManager.getLogger(words.Dictionary.class);
    Random random = new Random();
    // Генерируем случайный id пользователя
    private int id = random.nextInt(1000);
    // Количество изученных слов пользователя
    private int score = 0;
    // Пишем в лог, что пользователь был создан
    public User() {
        LOG.info("New user has been created");
    }
    // Увеличиваем на 1 счетчик количества изученных слов
    public void addScore() {
        score++;
    }
    public void deleteScore() {
        if (score > 0) { score--; }
    }
    public int getScore() {
        return score;
    }

    public int getId() {
        return id;
    }
}
