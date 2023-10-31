package MutileThreading;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;


    public Bank(int n, double initialBalance) {
        this.accounts = new double[n];
        Arrays.fill(accounts,initialBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to,double amount) throws InterruptedException{
        bankLock.lock();
        try {
            while(accounts[from] < amount){
                sufficientFunds.await();
            }
            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.println("");
            accounts[to] += amount;
            System.out.printf("");
            sufficientFunds.signalAll();
        }
        finally {
            bankLock.unlock();
        }
    }

    public double getTotalBalance(){
        bankLock.lock();
        try {
            double sum = 0;
            for (double a : accounts){
                sum += a;
            }
            return sum;
        }finally {
            bankLock.unlock();
        }
    }

    public int size(){
        return accounts.length;
    }
}
