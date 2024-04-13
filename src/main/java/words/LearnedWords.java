package words;

import java.util.ArrayList;
import java.util.List;

public class LearnedWords {
    private static List<String> learnedWords = new ArrayList<String>();

    public void addLearnedWord(String word) {
        learnedWords.add(word);
    }
    public List<String> getLearnedWord() {
        return learnedWords;
    }
}
