package minibank.service;

import minibank.model.Account;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionProcessor {
    private final ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    public void add(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account get(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void deposit(String accountNumber, long amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
        }
    }

    public ConcurrentHashMap<String, Account> getAccounts() {
        return accounts;
    }
}
