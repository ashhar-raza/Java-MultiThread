package BankProblem;

public class BankAccount {

    private long balance;

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void deposit(long amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(long amount) {
        if (balance > amount && amount > 0) {
            balance -= amount;
        }
        else {
            System.out.println("Insufficient funds for withdrawal of " + amount + ". Current balance: " + balance);
        }
    }
}
