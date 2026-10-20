public class SavingsAccount extends Account {
    private static final double MINIMUM_BALANCE = 500.0;

    public SavingsAccount(String customerName, String phone, double initialDeposit)
            throws BankException {
        super(customerName, phone, initialDeposit);
        if (initialDeposit < MINIMUM_BALANCE) {
            throw new BankException("Savings account requires minimum initial deposit of ₹500.");
        }
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }

    @Override
    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }

    @Override
    public void withdraw(double amount) throws BankException {
        if (amount <= 0) {
            throw new BankException("Withdrawal amount must be greater than zero.");
        }
        if (balance - amount < MINIMUM_BALANCE) {
            throw new BankException(
                    "Savings account must maintain minimum balance of ₹" + MINIMUM_BALANCE);
        }
        basicWithdraw(amount);
    }
}
