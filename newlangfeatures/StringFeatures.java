package newlangfeatures;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class StringFeatures {

    public record Person(String name, int age){}

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        String str = "hello";
        Optional<Character> optional = firstNonRepeatingChar(str);
        if(optional.isPresent())
            System.out.println("Forst non-repeating char in string %s is %c \n".formatted(str, optional.get()));
        else
            System.out.println("No non-repeating chars found in str %s\n ".formatted(str));

        ///// Remove dupes call
        String dupeStr = "hheelloooo";
        System.out.println("For string %s, String without dupes is %s".formatted(dupeStr, removeDupeChars(dupeStr)));

        /// Comparators
        List<Person> people = new ArrayList<>();
        people.add(new Person("Pishu", 40));
        people.add(new Person("Meow", 36));
        people.add(new Person("pranu", 3));



    }

    public static Optional<Character> firstNonRepeatingChar(String str) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();

        for(char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        return map.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey())
                .findFirst();
    }

    public static String removeDupeChars(String str) {
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> map = new LinkedHashMap<>();
        for(char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for(Map.Entry entry : map.entrySet()) {
            sb.append(entry.getKey());
        }
        return sb.toString();
    }

    public static String removeDupeCharsUsingStream(String str) {

        return str.chars().mapToObj(i -> (char)i) // maps from int to char
                .distinct()
                .map(String::valueOf) // maps from other type to String
                .collect(Collectors.joining());
    }

    public static char maxOccurringChar(String str) {
        Map<Character,Integer> map = new HashMap<>();

        for(char ch : str.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        Map.Entry<Character, Integer> maxEntry = map.entrySet().stream()
                .max(Map.Entry.comparingByValue()).get();

        return maxEntry.getKey();

    }
}
