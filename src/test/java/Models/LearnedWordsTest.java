package words;

import java.util.Dictionary;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class LearnedWordsTest {
    private words.LearnedWords learnedWords;

    @BeforeEach
    void init() {
        learnedWords = new words.LearnedWords();
    }

    @Test
    void testAddLearnedWord() {
        learnedWords.addLearnedWord("access");
        assertTrue((learnedWords.getLearnedWords().size() > 0));
    }
}