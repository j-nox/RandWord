package words;

import java.util.Random;

public class User {
    Random random = new Random();
    private int id = random.nextInt(1000);
    private int score = 0;

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
