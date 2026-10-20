public class CurrentAccount extends Account {
    private static final double MINIMUM_BALANCE = 0.0;

    public CurrentAccount(String customerName, String phone, double initialDeposit)
            throws BankException {
        super(customerName, phone, initialDeposit);
    }

    @Override
    public String getAccountType() {
        return "Current";
    }

    @Override
    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }

    @Override
    public void withdraw(double amount) throws BankException {
        basicWithdraw(amount);
    }
}
