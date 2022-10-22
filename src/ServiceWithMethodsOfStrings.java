import java.util.*;

public class ServiceWithMethodsOfStrings {

    private String someText;

    public ServiceWithMethodsOfStrings(String someText) {
        this.someText = someText;
    }

    public String getSentenceByIndex(int index){
        List<String> arrayOfSentences = new ArrayList<>();
        String text = this.someText;
        while(true){
          int indexOfEnter = text.indexOf(".");
            boolean hasSentenceByIndex = indexOfEnter != -1;
            if(hasSentenceByIndex){
              arrayOfSentences.add(text.substring(0,indexOfEnter+1));
              text=text.substring(indexOfEnter+2);
              continue;
          }
            break;
        }
        if(index>arrayOfSentences.size() || index<1){
            return null;
        }
        return arrayOfSentences.get(index-1);
    }

    public List<String> getAllUniqueWords(){
        List<String> arrayOfWords = getAllWords();
        int index = 0;
        for(int i=index;i<arrayOfWords.size();i++){
            boolean isEmpty = arrayOfWords.get(i).isEmpty();
            if(isEmpty){
                arrayOfWords.remove(i);
            }
            for (int j=i;j<arrayOfWords.size()-1;j++){
                String comparableWord = arrayOfWords.get(i);
                String currentWord = arrayOfWords.get(j+1);
                boolean wordsIsSame = comparableWord.toLowerCase().equals(currentWord.toLowerCase());
                if(wordsIsSame){
                    arrayOfWords.remove(j+1);
                }
            }
            index++;
        }
        return arrayOfWords;
    }

    private List<String> getAllWords(){
        List<String> arrayOfWords = new ArrayList<>();
        String text = getCleanText();
        text = text.replaceAll("\\.","");
        text = text.replaceAll("-","");
        text = text.replaceAll("_","");
        text = text.replaceAll(",","");
        text = text.replaceAll("'","");
        text = text.replaceAll("\n","");
        while(true){
            int indexOfEnter = text.indexOf(" ");
            boolean hasWord = indexOfEnter != -1;
            if(hasWord){
                arrayOfWords.add(text.substring(0,indexOfEnter));
                text=text.substring(indexOfEnter+1);
                continue;
            }
            break;
        }
        int index = 0;
        for(int i=index;i<arrayOfWords.size();i++){
            boolean isEmpty = arrayOfWords.get(i).isEmpty();
            if(isEmpty){
                arrayOfWords.remove(i);
            }
        }
        return arrayOfWords;
    }

    private String getCleanText(){
        String text = this.someText;
        while(true){
            boolean hasSeveralWhiteSpace = text.contains("  ");
            if(hasSeveralWhiteSpace){
                text = text.replaceAll("  "," ");
                continue;
            }
            break;
        }
        return text;
    }

    public String fixSyntax(){
        String text = getCleanText();
        String firstCharOfWord = String.valueOf(text.charAt(0));
        boolean isBlank = firstCharOfWord.isBlank();
        if(isBlank){
            firstCharOfWord = String.valueOf(text.charAt(1));
            firstCharOfWord = firstCharOfWord.toUpperCase();
            text = firstCharOfWord.concat(text.substring(2));
        }
        if(!isBlank){
            firstCharOfWord = firstCharOfWord.toUpperCase();
            text = firstCharOfWord.concat(text.substring(1));
        }

        int indexStartOfSearch = 0;
        while(true){
            int indexOfEnter = text.indexOf(".",indexStartOfSearch);
            boolean hasNextPointOfEntry = text.indexOf(".",indexOfEnter+1) != -1;
            if(!hasNextPointOfEntry){
                return text;
            }
            firstCharOfWord = String.valueOf(text.charAt(indexOfEnter+1));
            isBlank = firstCharOfWord.isBlank();
            if(isBlank){
                firstCharOfWord = String.valueOf(text.charAt(indexOfEnter+2));
                firstCharOfWord = firstCharOfWord.toUpperCase();
                text = text.substring(0,indexOfEnter+2).concat(firstCharOfWord.concat(text.substring(indexOfEnter+3)));
            }
            if(!isBlank){
                firstCharOfWord = firstCharOfWord.toUpperCase();
                text = text.substring(0,indexOfEnter+1).concat(firstCharOfWord.concat(text.substring(indexOfEnter+2)));
            }
            indexStartOfSearch = indexOfEnter+1;
        }
    }

    public String removeArticles(){
        String text = getCleanText();

        int previousIndexOfWhiteSpace = text.indexOf(" ");
        String firstWord = text.substring(0,previousIndexOfWhiteSpace);
        if(firstWord.toLowerCase().equals("the")){
            text = text.substring(previousIndexOfWhiteSpace+1);
        }
        if(firstWord.toLowerCase().equals("a")){
            text = text.substring(previousIndexOfWhiteSpace+1);
        }
        if(firstWord.toLowerCase().equals("an")){
            text = text.substring(previousIndexOfWhiteSpace+1);
        }

        previousIndexOfWhiteSpace = 0;
        int indexOfWhiteSpace;
        boolean isFinishRemoveArticles = false;

        while(!isFinishRemoveArticles){
            indexOfWhiteSpace = text.indexOf(" ", previousIndexOfWhiteSpace+1);
            if(indexOfWhiteSpace==-1){
                isFinishRemoveArticles = true;
                continue;
            }
            String currentWord = text.substring(previousIndexOfWhiteSpace,indexOfWhiteSpace);
            if(currentWord.toLowerCase().equals("the")){
                text = text.substring(0,previousIndexOfWhiteSpace).concat(text.substring(indexOfWhiteSpace));
                continue;
            }
            if(currentWord.toLowerCase().equals("a")){
                text = text.substring(0,previousIndexOfWhiteSpace).concat(text.substring(indexOfWhiteSpace));
                continue;
            }
            if(currentWord.toLowerCase().equals("an")){
                text.substring(0,previousIndexOfWhiteSpace).concat(text.substring(indexOfWhiteSpace));
                continue;
            }
            previousIndexOfWhiteSpace = indexOfWhiteSpace+1;
        }

        while(true){
            boolean hasSeveralWhiteSpace = text.contains("  ");
            if(hasSeveralWhiteSpace){
                text = text.replaceAll("  "," ");
                continue;
            }
            break;
        }

        return text;
    }

    public int countLetters(){
        List<String> arrayOfWords = getAllWords();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i =0;i<arrayOfWords.size();i++){
            stringBuilder.append(arrayOfWords.get(i));
        }
        return stringBuilder.length();
    }

    public Map<Integer,List<String>> groupWordsByNumber(){
        Map<Integer,List<String>> mapOfWords= new HashMap<>();
        List<String> arrayOfWords = getAllWords();
        List<CounterOfWord> listOfCounterOfWord = new ArrayList<>();
        String searchWord;
        String currentWord;
        int countOfTheSameWords = 1;
        int generatorKeys = 0;
        for(int i=1;i<arrayOfWords.size();i++){
            searchWord = arrayOfWords.get(0);
            currentWord = arrayOfWords.get(i);
            if(searchWord.equals(currentWord)){
                countOfTheSameWords++;
                arrayOfWords.remove(i);
                i-=1;
            }
            if(i==arrayOfWords.size()-1){
                CounterOfWord counterOfWord = new CounterOfWord(countOfTheSameWords,searchWord);
                listOfCounterOfWord.add(counterOfWord);
                arrayOfWords.remove(0);
                countOfTheSameWords=1;
                i=0;
            }
            if(arrayOfWords.size()==1){
                searchWord = arrayOfWords.get(0);
                CounterOfWord counterOfWord = new CounterOfWord(countOfTheSameWords,searchWord);
                listOfCounterOfWord.add(counterOfWord);
            }
        }

        for(int i=1;i<listOfCounterOfWord.size();i++){
            List<String> keyWords = new ArrayList<>();
            for(int j=0;j<listOfCounterOfWord.size();j++){
                CounterOfWord counterOfWord = listOfCounterOfWord.get(j);
                currentWord = counterOfWord.getWord();
                int countOfRepeat = counterOfWord.getCountOfRepeatedWords();
                if(i==countOfRepeat){
                    keyWords.add(currentWord);
                }
            }
            if(!(keyWords.isEmpty())){
                mapOfWords.put(i,keyWords);
            }
        }
        return mapOfWords;
    }

}
