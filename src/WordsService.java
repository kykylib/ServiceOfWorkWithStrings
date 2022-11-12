import java.util.*;

public class WordsService {

    private String someText;

    public String getSomeText() {
        return someText;
    }

    public WordsService(String someText) {
        this.someText = someText;
        this.someText = getCleanText();
    }

    public String sentence(int index) {
        List<String> arrayOfSentences = List.of(someText.split("\\."));
        return arrayOfSentences.get(index - 1);
    }

    public HashSet<String> getAllUniqueWords() {
        List<String> arrayOfWords = getAllWords();
        return new HashSet<>(arrayOfWords);
    }

    private List<String> getAllWords() {
        List<String> arrayOfWords = List.of(someText.split(" "));
        List<String> cleanedArrayOfWords = new ArrayList<>();
        String currentWord;
        for (int i = 0; i < arrayOfWords.size(); i++) {
            currentWord = arrayOfWords.get(i);
            currentWord = currentWord.replace(",", "");
            currentWord = currentWord.replace(".", "");
            currentWord = currentWord.replace("'", "");
            currentWord = currentWord.replace("\n", "");
            currentWord = currentWord.replace("-", "");
            if (currentWord.isBlank()) {
                continue;
            }
            cleanedArrayOfWords.add(currentWord);
        }
        return cleanedArrayOfWords;
    }

    private String getCleanText() {
        while (true) {
            boolean hasSeveralWhiteSpace = someText.contains("  ");
            if (hasSeveralWhiteSpace) {
                someText = someText.replaceAll("  ", " ");
                continue;
            }
            break;
        }
        return someText;
    }

    public List<String> fixSyntax() {
        List<String> sentences = List.of(someText.split("\\."));
        List<String> fixSentences = new ArrayList<>();
        String currentSentences;
        for(int i=0;i<sentences.size();i++){
            currentSentences = sentences.get(i).trim();
            if(currentSentences.isBlank()){
                continue;
            }
            fixSentences.add(currentSentences.substring(0,1).toUpperCase()+currentSentences.substring(1));
        }
        return fixSentences;
    }

    public String removeArticles() {
        String textWithoutArticles = someText;
        textWithoutArticles = textWithoutArticles.replaceAll("^the\s"," ");
        textWithoutArticles = textWithoutArticles.replaceAll("^The\s"," ");
        textWithoutArticles = textWithoutArticles.replaceAll("^a\s"," ");
        textWithoutArticles = textWithoutArticles.replaceAll("^A\s"," ");
        textWithoutArticles = textWithoutArticles.replaceAll("^an\s"," ");
        textWithoutArticles = textWithoutArticles.replaceAll("^An\s"," ");
        textWithoutArticles = textWithoutArticles.replaceAll("\sa\s"," ");
        textWithoutArticles = textWithoutArticles.replaceAll("\sthe\s"," ");
        textWithoutArticles = textWithoutArticles.replaceAll("\san\s"," ");
        return textWithoutArticles;
    }

    public int countLetters() {
        List<String> arrayOfWords = getAllWords();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arrayOfWords.size(); i++) {
            stringBuilder.append(arrayOfWords.get(i));
        }
        return stringBuilder.length();
    }

    public Map<Integer, List<String>> groupWordsByNumber() {
        Map<Integer, List<String>> mapOfWords = new HashMap<>();
        List<String> arrayOfWords = getAllWords();
        List<CounterOfWord> listOfCounterOfWord = new ArrayList<>();
        String searchWord;
        String currentWord;
        int countOfTheSameWords = 1;
        int generatorKeys = 0;
        for (int i = 1; i < arrayOfWords.size(); i++) {
            searchWord = arrayOfWords.get(0);
            currentWord = arrayOfWords.get(i);
            if (searchWord.equals(currentWord)) {
                countOfTheSameWords++;
                arrayOfWords.remove(i);
                i -= 1;
            }
            if (i == arrayOfWords.size() - 1) {
                CounterOfWord counterOfWord = new CounterOfWord(countOfTheSameWords, searchWord);
                listOfCounterOfWord.add(counterOfWord);
                arrayOfWords.remove(0);
                countOfTheSameWords = 1;
                i = 0;
            }
            if (arrayOfWords.size() == 1) {
                searchWord = arrayOfWords.get(0);
                CounterOfWord counterOfWord = new CounterOfWord(countOfTheSameWords, searchWord);
                listOfCounterOfWord.add(counterOfWord);
            }
        }

        for (int i = 1; i < listOfCounterOfWord.size(); i++) {
            List<String> keyWords = new ArrayList<>();
            for (int j = 0; j < listOfCounterOfWord.size(); j++) {
                CounterOfWord counterOfWord = listOfCounterOfWord.get(j);
                currentWord = counterOfWord.getWord();
                int countOfRepeat = counterOfWord.getCountOfRepeatedWords();
                if (i == countOfRepeat) {
                    keyWords.add(currentWord);
                }
            }
            if (!(keyWords.isEmpty())) {
                mapOfWords.put(i, keyWords);
            }
        }
        return mapOfWords;
    }

}
