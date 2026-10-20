import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = "java is simple java is powerful java is popular";
        HashMap<String, Integer> count = new HashMap<>();

        for (String word : paragraph.toLowerCase().split("\\s+")) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(count.entrySet());
        entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
