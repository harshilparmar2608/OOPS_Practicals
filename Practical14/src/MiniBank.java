import model.Account;
import repository.Repository;
import service.*;
import util.*;
import exception.BankException;

import java.nio.file.Path;
import java.io.IOException;
import java.util.Scanner;

public class MiniBank {
    private static final Path DATA = Path.of("accounts.dat");
    private static final Path LOG = Path.of("logs", "transactions.log");

    public static void main(String[] args) {
        Repository<Account> repository = new Repository<>();
        TransactionProcessor processor = new TransactionProcessor();
        BankService bank = new BankService(repository, processor);
        TransactionLog transactionLog = new TransactionLog(LOG);

        try {
            Account[] saved = StatePersister.load(DATA);
            repository.saveAll(saved);
            processor.addAll(repository.findAll());
        } catch (Exception e) {
            System.out.println("Could not load saved data");
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Statement");
            System.out.println("6. List Accounts");
            System.out.println("7. Report");
            System.out.println("8. Batch");
            System.out.println("9. Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();

            try {
                if (choice.equals("1")) {
                    System.out.print("Account number: ");
                    String number = scanner.nextLine();
                    System.out.print("Owner name: ");
                    String name = scanner.nextLine();
                    System.out.print("Initial balance: ");
                    long balance = Long.parseLong(scanner.nextLine());
                    bank.openAccount(number, name, balance);
                    System.out.println("Account opened");
                } else if (choice.equals("2")) {
                    System.out.print("Account number: ");
                    String number = scanner.nextLine();
                    System.out.print("Amount: ");
                    long amount = Long.parseLong(scanner.nextLine());
                    bank.deposit(number, amount);
                    transactionLog.log("DEPOSIT " + number + " " + amount);
                    System.out.println("Deposit successful");
                } else if (choice.equals("3")) {
                    System.out.print("Account number: ");
                    String number = scanner.nextLine();
                    System.out.print("Amount: ");
                    long amount = Long.parseLong(scanner.nextLine());
                    bank.withdraw(number, amount);
                    transactionLog.log("WITHDRAW " + number + " " + amount);
                    System.out.println("Withdrawal successful");
                } else if (choice.equals("4")) {
                    System.out.print("From account: ");
                    String from = scanner.nextLine();
                    System.out.print("To account: ");
                    String to = scanner.nextLine();
                    System.out.print("Amount: ");
                    long amount = Long.parseLong(scanner.nextLine());
                    bank.transfer(from, to, amount);
                    transactionLog.log("WITHDRAW " + from + " " + amount);
                    transactionLog.log("DEPOSIT " + to + " " + amount);
                    System.out.println("Transfer successful");
                } else if (choice.equals("5")) {
                    System.out.print("Account number: ");
                    System.out.println(bank.statement(scanner.nextLine()));
                } else if (choice.equals("6")) {
                    for (Account account : bank.listAccounts()) {
                        System.out.println(account);
                    }
                } else if (choice.equals("7")) {
                    System.out.println(FilesReport.generate(Path.of("logs")));
                } else if (choice.equals("8")) {
                    processor.processBatch();
                    System.out.println("Batch processed");
                } else if (choice.equals("9")) {
                    StatePersister.save(repository.findAll().toArray(new Account[0]), DATA);
                    System.out.println("Data saved");
                    break;
                } else {
                    System.out.println("Invalid choice");
                }
            } catch (BankException | IOException | InterruptedException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
