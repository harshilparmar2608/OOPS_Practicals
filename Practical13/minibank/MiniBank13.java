package minibank;

import minibank.model.Account;
import minibank.repository.Repository;

public class MiniBank13 {
    public static void main(String[] args) {
        Repository<Account> repository = new Repository<>();

        Account a1 = new Account("AC0001", "Riya", 5000);
        Account a2 = new Account("AC0002", "Amit", 2000);
        Account a3 = new Account("AC0003", "Neha", 9000);

        repository.saveAll(a1, a2, a3);

        System.out.println(repository.findById("AC0002"));

        for (Account account : repository.findAll()) {
            System.out.println(account);
        }
    }
}
