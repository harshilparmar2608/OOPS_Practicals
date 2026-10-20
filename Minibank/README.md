# MiniBank Project

A console-based Java banking application demonstrating core Object-Oriented Programming concepts.

## Features

- Create Savings and Current accounts
- Deposit money
- Withdraw money
- Transfer money between accounts
- Check balance
- Display account details
- View transaction history
- Display all accounts
- Delete an account after clearing its balance
- Input validation and custom exception handling

## OOP Concepts Demonstrated

1. **Class and Object** – `Bank`, `Account`, `SavingsAccount`, `CurrentAccount`
2. **Encapsulation** – private fields with controlled access through methods
3. **Abstraction** – `Account` is an abstract class
4. **Inheritance** – `SavingsAccount` and `CurrentAccount` extend `Account`
5. **Polymorphism** – overridden `withdraw()`, `getAccountType()` and `getMinimumBalance()`
6. **Constructor** – used to initialize account objects
7. **Exception Handling** – custom `BankException`
8. **Collections** – `LinkedHashMap` for accounts and `ArrayList` for transactions
9. **Static Member** – automatic account-number generation

## Requirements

- Java JDK 17 or later

## Run

Open a terminal in this folder and execute:

```bash
javac *.java
java Main
```

## Note

The application starts with one demo Savings Account:

- Account Number: 1001
- Customer: Demo User
- Initial Balance: ₹5000

Additional accounts can be created from the menu.
