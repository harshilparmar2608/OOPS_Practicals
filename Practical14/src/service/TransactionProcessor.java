package service;

import model.Account;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionProcessor {
    private final ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    public void add(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public void addAll(Iterable<Account> values) {
        for (Account account : values) {
            add(account);
        }
    }

    public void deposit(String accountNumber, long amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
        }
    }

    public void withdraw(String accountNumber, long amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            synchronized (account) {
                if (amount > 0 && amount <= account.getBalance()) {
                    try {
                        account.withdraw(amount);
                    } catch (IllegalArgumentException ignored) {
                    }
                }
            }
        }
    }

    public void processBatch() throws InterruptedException {
        Thread[] threads = new Thread[4];
        for (int i = 0; i < threads.length; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                for (Account account : accounts.values()) {
                    account.deposit(index + 1);
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    public ConcurrentHashMap<String, Account> getAccounts() {
        return accounts;
    }
}
