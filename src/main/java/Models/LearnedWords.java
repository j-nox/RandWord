package words;

import java.util.ArrayList;
import java.util.List;

// Изученные слова
public class LearnedWords {
    // Список изученных слов
    private List<String> learnedWords = new ArrayList<String>();
    // Добавляем слово в список изученных
    public void addLearnedWord(String word) {
        learnedWords.add(word);
    }
    // Получить весь список изученных слов
    public List<String> getLearnedWords() {
        return learnedWords;
    }
    // Поиск слова в списке изученных слов
    public boolean findLearnedWord(String word) {
        return learnedWords.contains(word);
    }
    // Удаляем слово из списка изученных слов
    public void removeWord(String word) {
        learnedWords.remove(word);
    }
}
