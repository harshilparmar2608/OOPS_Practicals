package service;

import model.Account;

public class StatementFormatter {
    public static String buildStatement(Account account) {
        StringBuilder result = new StringBuilder();
        result.append("Account Number: ").append(account.getAccountNumber()).append("\n");
        result.append("Owner: ").append(account.getOwnerName()).append("\n");
        result.append("Balance: ").append(account.getBalance());
        return result.toString();
    }
}
