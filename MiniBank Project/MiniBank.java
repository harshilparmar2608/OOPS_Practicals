import java.util.Scanner;

record BankInfo(String name, String branch) { }

enum MenuOption {
    OPEN_ACCOUNT,
    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    EXIT
}

public class MiniBank {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankInfo bank = new BankInfo("Mini Bank", "Vadodara Branch");

        System.out.println();
        System.out.println("       " + bank);
        System.out.println();

        Account[] accounts = new Account[3];

        accounts[0] = new SavingsAccount("Harshil", 5000, 1000);
        accounts[1] = new CurrentAccount("Bunny", 3000, 2000);
        accounts[2] = new FixedDepositAccount("Happiiee", 10000);

        accounts[0].deposit(1000);
        accounts[1].deposit(2000);
        accounts[2].deposit(5000);

        accounts[0].withdraw(500);
        accounts[1].withdraw(1000);
        accounts[2].withdraw(2000);

        System.out.println("\nAccount Details & Polymorphic Interest Rates:");

        for (Account account : accounts) {
            System.out.println(account.toString() + " | Interest Rate: " + account.interestRate() + "%");
            if (account instanceof SavingsAccount sa) {
                System.out.println("-> Savings minimum balance requirement: " + sa.getMinBalance());
            } else if (account instanceof CurrentAccount ca) {
                System.out.println("-> Current overdraft limit: " + ca.getOverdraftLimit());
            } else if (account instanceof FixedDepositAccount) {
                System.out.println("-> Fixed deposit funds are locked");
            }
        }

        Account account1 = accounts[0];
        Account account2 = accounts[1];

        System.out.println("\nEquals Test:");
        System.out.println("Account 1 equals Account 2: " + account1.equals(account2));

        System.out.println("\nInstanceof Test:");
        Object obj = accounts[0];
        if (obj instanceof Account) {
            System.out.println("Object is an Account.");
        } else {
            System.out.println("Object is not an Account.");
        }

        Customer customer = new Customer(
                "C001",
                "Harshil",
                "harshil@gmail.com",
                "9876543210"
        );

        Customer.Address address = new Customer.Address(
                "Main Road",
                "Vadodara",
                "390001"
        );

        customer.setAddress(address);

        System.out.println("\nCustomer Address:");
        System.out.println("Line: " + customer.getAddress().getLine());
        System.out.println("City: " + customer.getAddress().getCity());
        System.out.println("Pincode: " + customer.getAddress().getPincode());

        Customer copy = customer.clone();

        System.out.println("\nClone Test:");
        System.out.println("Original Customer: " + customer.getName());
        System.out.println("Cloned Customer: " + copy.getName());

        System.out.println("\nValidation & Command Parser Tests:");
        System.out.println("Valid Mobile (9876543210): " + Validator.isValidMobile("9876543210"));
        System.out.println("Invalid Mobile (12345): " + Validator.isValidMobile("12345"));
        Command cmd = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println("Parsed Command: " + cmd.type() + " " + cmd.accountNumber() + " " + cmd.amount());
        System.out.println(StatementFormatter.buildStatement(accounts[0]));

        while (true) {
            System.out.println("\n MENU ");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    MenuOption option = MenuOption.OPEN_ACCOUNT;
                    System.out.println(option + " - To be implemented in a later lab.");
                }
                case 2 -> {
                    MenuOption option = MenuOption.DEPOSIT;
                    System.out.println(option + " - To be implemented in a later lab.");
                }
                case 3 -> {
                    MenuOption option = MenuOption.WITHDRAW;
                    System.out.println(option + " - To be implemented in a later lab.");
                }
                case 4 -> {
                    MenuOption option = MenuOption.TRANSFER;
                    System.out.println(option + " - To be implemented in a later lab.");
                }
                case 5 -> {
                    MenuOption option = MenuOption.EXIT;
                    System.out.println(option);
                    System.out.println("Thank you for using Mini Bank!");
                }
                default -> System.out.println("Invalid Choice!");
            }
            if (choice == 5) {
                break;
            }
        }
        sc.close();
    }
}