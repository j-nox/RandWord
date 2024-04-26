package words;

import java.util.Dictionary;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class DictionaryTest {
    private words.Dictionary dictionary;

    @BeforeEach
    void init() {
        dictionary = words.Dictionary.getInstance();
    }

    @Test
    void testCheckWord() {
        assertTrue(dictionary.checkWord(new Word("access", "доступ")));
    }

}