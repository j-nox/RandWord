package words;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class UserTest {
    private User user;

    @BeforeEach
    void init() {
        user = new words.User();
    }

    @Test
    void testAddScore() {
        user.addScore();
        assertEquals(1, user.getScore());
        user.deleteScore();
    }

    @Test
    void testDeleteScore() {
        user.addScore();
        user.deleteScore();
        assertEquals(0, user.getScore());
    }

    @Test
    void testDeleteScoreZeroStop() {
        user.deleteScore();
        assertEquals(0, user.getScore());
    }

    @Test
    void testId() {
        assertTrue(user.getId() > 0);
    }

    @Test
    void testBigId() {
        assertTrue(user.getId() < 1001);
    }

}