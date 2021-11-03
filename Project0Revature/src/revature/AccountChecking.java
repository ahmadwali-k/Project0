package revature;

import java.text.NumberFormat;
import java.util.Scanner;

class AccountChecking implements IAccount {
    private static double balance2 = 50;
    private double balance;
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    @Override
    public double deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the amount you want to deposit. to Checking Account ");
        double amount = scanner.nextDouble();
        balance = balance + amount;
        System.out.println("Amount Deposited: " + currencyFormat.format(amount));
        System.out.println("New Amount after deposit: " + currencyFormat.format(balance));
        return balance;
    }

    @Override
    public double withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the amount you want to withdraw from Checking Account: ");
        double amount = scanner.nextDouble();

        balance2 = balance2 - amount;
        System.out.println("New Amount after withdrawal: " + currencyFormat.format(balance2));


        return balance2;
    }

    @Override
    public void getBalance() {
        System.out.println("Checking Account balance " + currencyFormat.format(balance));
    }
}