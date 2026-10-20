import java.util.*;

public class UniqueVisitors {
    public static void main(String[] args) {
        String[] names = {"riya", "harsh", "riya", "amit", "harsh", "neha", "amit"};
        HashSet<String> unique = new HashSet<>(Arrays.asList(names));
        LinkedHashSet<String> ordered = new LinkedHashSet<>(Arrays.asList(names));

        System.out.println("Unique users: " + unique.size());

        int count = 0;
        for (String name : ordered) {
            if (count == 3) {
                break;
            }
            System.out.println(name);
            count++;
        }
    }
}
