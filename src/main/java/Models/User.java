package words;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class User {
    private static final Logger LOG = LogManager.getLogger(words.Dictionary.class);
    Random random = new Random();
    private int id = random.nextInt(1000);
    private int score = 0;

    public User() {
        LOG.info("New user has been created");
    }

    public void addScore() {
        score++;
    }
    public void deleteScore() {
        if (score > 0) {
            score--;
        }
    }

    public int getScore() {
        return score;
    }

    public int getId() {
        return id;
    }
}
