package BankProblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankSimulation {

    public static void main(String[] args) throws InterruptedException {

        // BankAccount account = new BankAccount();
        BankAccountSol account = new BankAccountSol();

        Runnable depositTask = () -> {
            for (long i = 1; i <= 1000; i++) {
                account.deposit(i);
            }
        };

        Runnable withdrawTask = () -> {
            for (long i = 1; i <= 1000; i++) {
                account.withdraw(i);
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(4);

        long start = System.currentTimeMillis();

        es.execute(depositTask);
        es.execute(depositTask);
        es.execute(withdrawTask);
        es.execute(withdrawTask);

        es.shutdown();
        es.awaitTermination(5, TimeUnit.SECONDS);

        long end = System.currentTimeMillis();

        System.out.println("Final balance: " + account.getBalance());
        System.out.println("Execution time: " + (end - start) + " ms");

    }

}
// terminal executable command
// javac BankProblem/BankAccount.java BankProblem/BankSimulation.java BankProblem/BankAccountSol.java
// java BankProblem.BankSimulation