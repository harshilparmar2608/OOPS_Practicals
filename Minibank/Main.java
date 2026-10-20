import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        seedDemoData();

        while (true) {
            System.out.println("\n========== MINIBANK ==========");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Check Balance");
            System.out.println("6. Account Details");
            System.out.println("7. Transaction History");
            System.out.println("8. Display All Accounts");
            System.out.println("9. Delete Account");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = readInt();

            try {
                switch (choice) {
                    case 1 -> createAccount();
                    case 2 -> deposit();
                    case 3 -> withdraw();
                    case 4 -> transfer();
                    case 5 -> checkBalance();
                    case 6 -> showDetails();
                    case 7 -> showTransactions();
                    case 8 -> bank.displayAllAccounts();
                    case 9 -> deleteAccount();
                    case 0 -> {
                        System.out.println("Thank you for using MiniBank.");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (BankException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void createAccount() throws BankException {
        System.out.print("Enter customer name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine().trim();

        System.out.println("Account Type:");
        System.out.println("1. Savings");
        System.out.println("2. Current");
        System.out.print("Choose: ");
        int type = readInt();

        System.out.print("Enter initial deposit: ");
        double amount = readDouble();

        Account account;
        if (type == 1) {
            account = new SavingsAccount(name, phone, amount);
        } else if (type == 2) {
            account = new CurrentAccount(name, phone, amount);
        } else {
            throw new BankException("Invalid account type.");
        }

        bank.addAccount(account);
        System.out.println("Account created successfully.");
        System.out.println("Account Number: " + account.getAccountNumber());
    }

    private static void deposit() throws BankException {
        Account account = findAccount();
        System.out.print("Enter amount to deposit: ");
        double amount = readDouble();
        account.deposit(amount);
        System.out.println("Deposit successful. New balance: ₹" + money(account.getBalance()));
    }

    private static void withdraw() throws BankException {
        Account account = findAccount();
        System.out.print("Enter amount to withdraw: ");
        double amount = readDouble();
        account.withdraw(amount);
        System.out.println("Withdrawal successful. New balance: ₹" + money(account.getBalance()));
    }

    private static void transfer() throws BankException {
        System.out.print("Enter sender account number: ");
        String from = sc.nextLine().trim();
        System.out.print("Enter receiver account number: ");
        String to = sc.nextLine().trim();
        System.out.print("Enter amount: ");
        double amount = readDouble();

        bank.transfer(from, to, amount);
        System.out.println("Transfer successful.");
    }

    private static void checkBalance() throws BankException {
        Account account = findAccount();
        System.out.println("Current Balance: ₹" + money(account.getBalance()));
    }

    private static void showDetails() throws BankException {
        Account account = findAccount();
        account.displayDetails();
    }

    private static void showTransactions() throws BankException {
        Account account = findAccount();
        account.displayTransactions();
    }

    private static void deleteAccount() throws BankException {
        System.out.print("Enter account number: ");
        String number = sc.nextLine().trim();
        bank.deleteAccount(number);
        System.out.println("Account deleted successfully.");
    }

    private static Account findAccount() throws BankException {
        System.out.print("Enter account number: ");
        String number = sc.nextLine().trim();
        return bank.findAccount(number);
    }

    private static int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid integer: ");
            }
        }
    }

    private static double readDouble() {
        while (true) {
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (value < 0) {
                    System.out.print("Amount cannot be negative. Enter again: ");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid amount: ");
            }
        }
    }

    private static String money(double value) {
        return String.format("%.2f", value);
    }

    private static void seedDemoData() {
        try {
            bank.addAccount(new SavingsAccount("Demo User", "9999999999", 5000));
        } catch (BankException ignored) {
        }
    }
}
