import java.util.Scanner;

public class Practical1B {

    record Vehicle(String number, String type) {}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int totalToll = 0;
        int bike = 0, car = 0, truck = 0;

        while (true) {
            System.out.print("Enter vehicle number (or done): ");
            String number = sc.next();

            if (number.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter vehicle type (bike/car/truck): ");
            String type = sc.next().toLowerCase();

            Vehicle v = new Vehicle(number, type);

            int toll = switch (v.type()) {
                case "bike" -> 20;
                case "car" -> 50;
                case "truck" -> 150;
                default -> 0;
            };

            totalToll += toll;

            switch (v.type()) {
                case "bike":
                    bike++;
                    break;
                case "car":
                    car++;
                    break;
                case "truck":
                    truck++;
                    break;
            }
        }

        String mostFrequent;

        if (bike >= car && bike >= truck) {
            mostFrequent = "bike";
        } else if (car >= bike && car >= truck) {
            mostFrequent = "car";
        } else {
            mostFrequent = "truck";
        }

        System.out.println("Total toll: " + totalToll);
        System.out.println("Most frequent: " + mostFrequent);

        sc.close();
    }
}