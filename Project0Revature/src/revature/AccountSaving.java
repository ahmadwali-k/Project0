package revature;

import java.text.NumberFormat;
import java.util.Scanner;

interface IAccount  {
    double deposit();
    double withdraw();
    void getBalance();

}


 class AccountSaving implements IAccount  {
    private double  balance;
    private static double balance2 = 0;
    private int accountId;
    private double perDayLimit;
    //private double amount;
    // create new account.
    Scanner scanner = new Scanner(System.in);

     NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
     @Override
     public double deposit() {
         System.out.println("Enter the amount you want to deposit to Saving Account.");
         double amount = scanner.nextDouble();
         balance = balance + amount;
         System.out.println("Amount Deposited: " + currencyFormat.format(amount));
         System.out.println("New Amount after deposit: " + currencyFormat.format(balance));

         return balance;
     }

     @Override


     public double withdraw() {
         System.out.println("Enter the amount you want to withdraw from Saving Account: ");
         double amount = scanner.nextDouble();

             balance2 = balance2 - amount;
             System.out.println("Transaction successful.");
             System.out.println("New Amount after withdrawal: " + currencyFormat.format(balance2));


        return balance2;
     }

     @Override
     public void getBalance() {
         System.out.println("Saving Account balance " + currencyFormat.format(balance));
     }

 }

