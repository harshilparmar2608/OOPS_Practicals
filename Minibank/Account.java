import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private static int nextAccountNumber = 1001;

    private final String accountNumber;
    private final String customerName;
    private final String phone;
    protected double balance;
    private final List<String> transactions = new ArrayList<>();

    protected Account(String customerName, String phone, double initialDeposit)
            throws BankException {
        if (customerName == null || customerName.isBlank()) {
            throw new BankException("Customer name cannot be empty.");
        }
        if (phone == null || phone.isBlank()) {
            throw new BankException("Phone number cannot be empty.");
        }
        if (initialDeposit < 0) {
            throw new BankException("Initial deposit cannot be negative.");
        }

        this.accountNumber = String.valueOf(nextAccountNumber++);
        this.customerName = customerName;
        this.phone = phone;
        this.balance = initialDeposit;

        addTransaction("Account opened with ₹" + String.format("%.2f", initialDeposit));
    }

    public abstract String getAccountType();

    public abstract double getMinimumBalance();

    public void deposit(double amount) throws BankException {
        if (amount <= 0) {
            throw new BankException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        addTransaction("Deposited ₹" + String.format("%.2f", amount));
    }

    public abstract void withdraw(double amount) throws BankException;

    protected void basicWithdraw(double amount) throws BankException {
        if (amount <= 0) {
            throw new BankException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new BankException("Insufficient balance.");
        }
        balance -= amount;
        addTransaction("Withdrawn ₹" + String.format("%.2f", amount));
    }

    public void displayDetails() {
        System.out.println("\n------ ACCOUNT DETAILS ------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Customer Name  : " + customerName);
        System.out.println("Phone          : " + phone);
        System.out.println("Account Type   : " + getAccountType());
        System.out.printf("Balance        : ₹%.2f%n", balance);
    }

    public void displayTransactions() {
        System.out.println("\n------ TRANSACTION HISTORY ------");
        if (transactions.isEmpty()) {
            System.out.println("No transactions.");
            return;
        }
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i));
        }
    }

    protected void addTransaction(String transaction) {
        transactions.add(transaction);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhone() {
        return phone;
    }

    public double getBalance() {
        return balance;
    }
}
