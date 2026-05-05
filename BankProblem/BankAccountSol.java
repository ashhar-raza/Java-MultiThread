package BankProblem;

public class BankAccountSol {
    private long balance;

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public synchronized void deposit(long amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + ", new balance: " + balance);
            notifyAll();

        }
    }

    public synchronized void withdraw(long amount) {

        while (balance < amount) {
            try {
                System.out.println("Waiting to withdraw: " + amount + ", Current balance: " + balance);
                wait();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                System.out.println("withdraw interrupted while waiting for sufficient funds: " + amount);

            }
        }

        balance -= amount;
        System.out.println("Withdrew " + amount + ", new balance: " + balance);
    }

}
