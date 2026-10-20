import java.util.LinkedHashMap;
import java.util.Map;

public class Bank {
    private final Map<String, Account> accounts = new LinkedHashMap<>();

    public void addAccount(Account account) throws BankException {
        if (account == null) {
            throw new BankException("Account cannot be null.");
        }
        if (accounts.containsKey(account.getAccountNumber())) {
            throw new BankException("Account number already exists.");
        }
        accounts.put(account.getAccountNumber(), account);
    }

    public Account findAccount(String accountNumber) throws BankException {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new BankException("Account not found.");
        }
        return account;
    }

    public void deleteAccount(String accountNumber) throws BankException {
        Account account = findAccount(accountNumber);
        if (account.getBalance() != 0) {
            throw new BankException("Account can be deleted only when balance is ₹0.");
        }
        accounts.remove(accountNumber);
    }

    public void transfer(String fromNumber, String toNumber, double amount) throws BankException {
        if (fromNumber.equals(toNumber)) {
            throw new BankException("Sender and receiver cannot be the same.");
        }

        Account sender = findAccount(fromNumber);
        Account receiver = findAccount(toNumber);

        sender.withdraw(amount);
        try {
            receiver.deposit(amount);
        } catch (BankException e) {
            // Roll back sender withdrawal if receiver deposit unexpectedly fails.
            sender.deposit(amount);
            throw e;
        }

        sender.addTransaction("Transferred ₹" + String.format("%.2f", amount) +
                " to A/C " + toNumber);
        receiver.addTransaction("Received ₹" + String.format("%.2f", amount) +
                " from A/C " + fromNumber);
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        System.out.println("\n------ ALL ACCOUNTS ------");
        for (Account account : accounts.values()) {
            System.out.printf("%-12s %-20s %-12s ₹%.2f%n",
                    account.getAccountNumber(),
                    account.getCustomerName(),
                    account.getAccountType(),
                    account.getBalance());
        }
    }
}
