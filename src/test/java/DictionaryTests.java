
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Test
public class DictionaryTests {

    private Dictionary dictionary;
    private WordsAvailable words;
    List<String> dictionaryWords;

    @BeforeMethod
    public void setUp() {
        dictionary = new Dictionary();
        words = mock(WordsAvailable.class);
        dictionary.setDictionaryService(words);

        when(words.getDictionary()).thenReturn(createDictionaryArray());
        dictionaryWords = words.getDictionary();
    }


    static List<String> createDictionaryArray() {
        List<String> listDictionary = new ArrayList<String>();
        BufferedReader reader;

        try {
            ClassLoader loader = DictionaryTests.class.getClassLoader();
            File file = new File(loader.getResource("EnglishWords").getFile());
            reader = new BufferedReader(new FileReader(file));
            //reader = new BufferedReader(new FileReader("EnglishWords"));
            String line = reader.readLine();
            while (line != null) {
                listDictionary.add(line);
                line = reader.readLine(); // read next line
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listDictionary;
    }


    public boolean isThisEnglish(String word) {
        for (String w : dictionaryWords) {
            if (w.equals(word.toLowerCase())) {
                System.out.println(word + " is a valid english word");
                return true;
            }
        }
        return false;
    }


    @Test
    public void validateWorkingWord() {
        when(words.isEnglishWord("WORKING")).thenReturn(isThisEnglish("WORKING"));
        Assert.assertTrue(dictionary.isEnglishWord("WORKING"));
        // Print all possible words
        dictionary.findPossibleWords("WORKING");
    }
}
