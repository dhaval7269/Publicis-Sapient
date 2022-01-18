import java.util.List;

public interface WordsAvailable {
    List<String> getDictionary();
    boolean isEnglishWord(String word);
}
