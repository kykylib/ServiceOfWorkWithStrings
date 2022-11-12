import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String text = "the artificial the     intelligence - or AI for short - is technology that enables a computer to think or act in a more 'human' way. it does this by taking in information from its surroundings, and deciding its response based on what it learns or senses.\n" +
                "It affects the way we live, work          and have fun in our spare time - and sometimes without us even realising.\n" +
                "AI is becoming a bigger part of our lives, as the technology behind it becomes more and more advanced. Machines are improving their ability to 'learn' from mistakes and change how they approach a task the next time they try it.\n" +
                "Some researchers are even trying       to teach robots about feelings and emotions.\n";
        System.out.println(text);
        WordsService wordsService = new WordsService(text);
        System.out.println(wordsService.removeArticles());
    }
}
