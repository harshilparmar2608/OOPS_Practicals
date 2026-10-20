import java.util.Scanner;
import java.util.Random;

public class RPS {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        String[] choice = {"Rock", "Paper", "Scissors"};

        System.out.println("=== Rock Paper Scissors Game ===");
        System.out.println("1. Rock");
        System.out.println("2. Paper");
        System.out.println("3. Scissors");

        System.out.print("Enter your choice (1-3): ");
        int user = sc.nextInt();

        int computer = r.nextInt(3) + 1;

        System.out.println("You chose: " + choice[user - 1]);
        System.out.println("Computer chose: " + choice[computer - 1]);

        if (user == computer) {
            System.out.println("Match Draw!");
        }
        else if ((user == 1 && computer == 3) ||
                 (user == 2 && computer == 1) ||
                 (user == 3 && computer == 2)) {
            System.out.println("You Win!");
        }
        else {
            System.out.println("Computer Wins!");
        }

        sc.close();
    }
}