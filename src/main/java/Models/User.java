package words;

import java.util.Random;

public class User {
    Random random = new Random();
    int randomId = random.nextInt(1000);
    private int id = randomId;
    private int score = 0;

    public void addScore() {
        score++;
    }
    public void deleteScore() {
        score--;
    }

    public int getScore() {
        return score;
    }

    public int getId() {
        return id;
    }
}
