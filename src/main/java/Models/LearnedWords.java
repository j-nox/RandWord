package words;

import java.util.ArrayList;
import java.util.List;

public class LearnedWords {
    private List<String> learnedWords = new ArrayList<String>();

    public void addLearnedWord(String word) {
        learnedWords.add(word);
    }
    public List<String> getLearnedWords() {
        return learnedWords;
    }

    public boolean findLearnedWord(String word) {
        return learnedWords.contains(word);
    }
    public void removeWord(String word) {
        learnedWords.remove(word);
    }
}
