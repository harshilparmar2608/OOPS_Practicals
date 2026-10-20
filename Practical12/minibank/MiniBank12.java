package minibank;

import minibank.model.Account;
import minibank.store.AccountStore;
import minibank.service.TransactionProcessor;

public class MiniBank12 {
    public static void main(String[] args) {
        AccountStore store = new AccountStore();

        Account a1 = new Account("AC0001", "Riya", 5000);
        Account a2 = new Account("AC0002", "Amit", 2000);
        Account a3 = new Account("AC0003", "Neha", 9000);

        store.add(a1);
        store.add(a2);
        store.add(a3);

        store.addTransaction("DEPOSIT AC0001 500");
        store.addTransaction("WITHDRAW AC0002 200");

        System.out.println(store.get("AC0002"));

        for (Account account : store.sortedByBalance()) {
            System.out.println(account);
        }

        TransactionProcessor processor = new TransactionProcessor();
        processor.add(a1);
        processor.add(a2);
        processor.add(a3);
        processor.deposit("AC0001", 500);

        System.out.println("After transaction: " + processor.get("AC0001"));
    }
}
