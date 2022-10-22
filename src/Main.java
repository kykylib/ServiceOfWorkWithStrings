public class Main {
    public static void main(String[] args) {
        String text = "the artificial     intelligence - or AI for short - is technology that enables a computer to think or act in a more 'human' way. it does this by taking in information from its surroundings, and deciding its response based on what it learns or senses.\n" +
                "It affects the way we live, work          and have fun in our spare time - and sometimes without us even realising.\n" +
                "AI is becoming a bigger part of our lives, as the technology behind it becomes more and more advanced. Machines are improving their ability to 'learn' from mistakes and change how they approach a task the next time they try it.\n" +
                "Some researchers are even trying       to teach robots about feelings and emotions.\n";
        System.out.println(text);
        ServiceWithMethodsOfStrings serviceWithMethodsOfStrings = new ServiceWithMethodsOfStrings(text);
        //System.out.println(serviceWithMethodsOfStrings.getSentenceByIndex(1));
       // System.out.println(serviceWithMethodsOfStrings.getAllUniqueWords());
        System.out.println();
//        System.out.println(serviceWithMethodsOfStrings.fixSyntax());
       // System.out.println(serviceWithMethodsOfStrings.removeArticles());
       // System.out.println(serviceWithMethodsOfStrings.countLetters());
        System.out.println(serviceWithMethodsOfStrings.groupWordsByNumber());

    }
}
