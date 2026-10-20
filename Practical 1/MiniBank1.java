import java.util.Scanner;

public class MiniBank1 {

    record BankInfo(String name, String branch) {}

    enum MenuOption {
        OPEN_ACCOUNT,
        DEPOSIT,
        WITHDRAW,
        TRANSFER,
        EXIT
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankInfo bank = new BankInfo("MiniBank", "CHARUSAT Branch");

        System.out.println("=================================");
        System.out.println(bank.name());
        System.out.println(bank.branch());
        System.out.println("=================================");

        boolean exit = false;

        while (!exit) {

            System.out.println("\n------ MENU ------");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            String message = switch (choice) {

                case 1 -> {
                    MenuOption option = MenuOption.OPEN_ACCOUNT;
                    yield option + " - To be implemented in a later lab.";
                }

                case 2 -> {
                    MenuOption option = MenuOption.DEPOSIT;
                    yield option + " - To be implemented in a later lab.";
                }

                case 3 -> {
                    MenuOption option = MenuOption.WITHDRAW;
                    yield option + " - To be implemented in a later lab.";
                }

                case 4 -> {
                    MenuOption option = MenuOption.TRANSFER;
                    yield option + " - To be implemented in a later lab.";
                }

                case 5 -> {
                    exit = true;
                    yield "Thank you for using MiniBank!";
                }

                default -> "Invalid choice! Please try again.";
            };

            System.out.println(message);
        }

        sc.close();
    }
}