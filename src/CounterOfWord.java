public class CounterOfWord {
    final private int countOfRepeatedWords;
    final private String word;

    public int getCountOfRepeatedWords() {
        return countOfRepeatedWords;
    }

    public String getWord() {
        return word;
    }

    public CounterOfWord(int countOfRepeatedWords, String word) {
        this.countOfRepeatedWords = countOfRepeatedWords;
        this.word = word;
    }
}
