package service;

import java.util.Scanner;
import model.Account;
import model.CurrentAccount;
import model.Customer;
import model.FixedDepositAccount;
import model.InterestBearing;
import model.Premium;
import model.SavingsAccount;
import model.WithdrawRule;
import model.Command;
import model.exception.BankException;
import model.exception.InsufficientFundsException;
import model.exception.InvalidAmountException;
import util.AnnotationValidator;
import util.CommandParser;
import util.StatementFormatter;

import static util.Validator.isValidMobile;

record BankInfo(String name, String branch) { }

enum MenuOption {
    OPEN_ACCOUNT,
    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    EXIT
}

class AuditLog implements AutoCloseable {
    private String session;

    public AuditLog(String session) {
        this.session = session;
        System.out.println("[AuditLog] Session started: " + session);
    }

    public void log(String message) {
        System.out.println("[AuditLog] " + session + " -> " + message);
    }

    @Override
    public void close() {
        System.out.println("[AuditLog] Session closed: " + session);
    }
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

        System.out.println("\n--- Deposit / Withdraw with Exception Handling ---");

        try {
            accounts[0].deposit(1000);
            System.out.println("Deposited 1000 to " + accounts[0].getAccountNumber());

            accounts[1].deposit(2000);
            System.out.println("Deposited 2000 to " + accounts[1].getAccountNumber());

            accounts[0].withdraw(500);
            System.out.println("Withdrew 500 from " + accounts[0].getAccountNumber());

            accounts[2].withdraw(2000);
            System.out.println("Withdrew 2000 from " + accounts[2].getAccountNumber());

        } catch (InvalidAmountException e) {
            System.out.println("Amount error: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("Funds error: " + e.getMessage() + " | Shortfall: " + e.getShortfall());
        } finally {
            System.out.println("Initial setup operations complete.");
        }

        System.out.println("\n--- Transfer Demo with Exception Handling ---");
        try {
            accounts[0].transfer(accounts[1], 10000);
        } catch (InvalidAmountException e) {
            System.out.println("Transfer failed - Amount error: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("Transfer failed - " + e.getMessage());
        }

        System.out.println("\n--- Try-With-Resources: AuditLog Demo ---");
        try (AuditLog log = new AuditLog("LAB-08")) {
            log.log("Validated accounts on startup");
            accounts[0].deposit(500);
            log.log("Deposited 500 to " + accounts[0].getAccountNumber());
        } catch (InvalidAmountException e) {
            System.out.println("Audit deposit failed: " + e.getMessage());
        }

        System.out.println("\n--- Invalid Amount Demo ---");
        try {
            accounts[0].deposit(-500);
        } catch (InvalidAmountException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        System.out.println("\nAccount Details & Yearly Interest:");

        for (Account account : accounts) {
            System.out.println(account.toString() + " | Interest Rate: " + account.interestRate() + "%"
                    + " | Yearly Interest: " + account.yearlyInterest());
            if (account instanceof Premium) {
                System.out.println("-> Premium Account verified");
            }
        }

        WithdrawRule rule1 = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return account.canWithdraw(amount);
            }
        };

        WithdrawRule rule2 = (account, amount) -> account.canWithdraw(amount);

        System.out.println("\nWithdrawal Rule Tests:");
        System.out.println("Anonymous Rule (withdraw 1000 from Savings): " + rule1.allow(accounts[0], 1000));
        System.out.println("Lambda Rule (withdraw 1000 from FixedDeposit): " + rule2.allow(accounts[2], 1000));

        System.out.println("\nAnnotation Validation Tests:");
        Account invalidAccount = new SavingsAccount("InvalidUser", -100, 500);
        String[] validationErrors = AnnotationValidator.validate(invalidAccount);
        System.out.println("Errors for negative balance account:");
        for (String err : validationErrors) {
            System.out.println("- " + err);
        }

        System.out.println("\nStatic Import Validator Test:");
        System.out.println("Valid Mobile: " + isValidMobile("9876543210"));

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
