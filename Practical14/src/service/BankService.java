package service;

import model.Account;
import repository.Repository;
import exception.*;

import java.util.ArrayList;

public class BankService {
    private final Repository<Account> repository;
    private final TransactionProcessor processor;

    public BankService(Repository<Account> repository, TransactionProcessor processor) {
        this.repository = repository;
        this.processor = processor;
    }

    public synchronized Account openAccount(String accountNumber, String ownerName, long balance) throws BankException {
        if (accountNumber == null || accountNumber.isBlank() || ownerName == null || ownerName.isBlank()) {
            throw new BankException("Invalid account details");
        }
        if (balance < 0) {
            throw new InvalidAmountException("Initial balance cannot be negative");
        }
        if (repository.findById(accountNumber) != null) {
            throw new BankException("Account already exists");
        }
        Account account = new Account(accountNumber, ownerName, balance);
        repository.save(account);
        processor.add(account);
        return account;
    }

    public void deposit(String accountNumber, long amount) throws BankException {
        Account account = find(accountNumber);
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }
        account.deposit(amount);
    }

    public void withdraw(String accountNumber, long amount) throws BankException {
        Account account = find(accountNumber);
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }
        synchronized (account) {
            long balance = account.getBalance();
            if (amount > balance) {
                throw new InsufficientFundsException("Insufficient funds", amount - balance);
            }
            account.withdraw(amount);
        }
    }

    public void transfer(String from, String to, long amount) throws BankException {
        Account source = find(from);
        Account target = find(to);

        Account first = from.compareTo(to) < 0 ? source : target;
        Account second = from.compareTo(to) < 0 ? target : source;

        synchronized (first) {
            synchronized (second) {
                if (amount <= 0) {
                    throw new InvalidAmountException("Amount must be positive");
                }
                if (amount > source.getBalance()) {
                    throw new InsufficientFundsException("Insufficient funds", amount - source.getBalance());
                }
                source.withdraw(amount);
                target.deposit(amount);
            }
        }
    }

    public String statement(String accountNumber) throws BankException {
        return StatementFormatter.buildStatement(find(accountNumber));
    }

    public ArrayList<Account> listAccounts() {
        return repository.findAll();
    }

    private Account find(String accountNumber) throws AccountNotFoundException {
        Account account = repository.findById(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException("Account not found: " + accountNumber);
        }
        return account;
    }
}
