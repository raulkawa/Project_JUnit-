package example;

public class Account {
    private String accountNumber;
    private double balance;
    private Logger logger;

    public Account(String accountNumber, Logger logger ) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.logger = logger;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            logger.log("Deposit of $" + amount + " to account " + accountNumber);
        } else {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            logger.log("Withdrawal of $" + amount + " from account " + accountNumber);
        } else {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero or equal to balance");
        }
    }

    public void printAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
    }
}
