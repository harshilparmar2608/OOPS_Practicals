package model;

import model.exception.InsufficientFundsException;
import model.exception.InvalidAmountException;

public interface Transactable {
    long getBalance();
    void deposit(long amount) throws InvalidAmountException;
    boolean withdraw(long amount) throws InvalidAmountException, InsufficientFundsException;
}
