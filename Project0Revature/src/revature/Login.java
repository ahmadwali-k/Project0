package revature;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Scanner;

public class Login {

    public Login() {}

    UserDao dao = UserDaoFactory.getUserDao();
    //AccountDao dao2 = AccountDaoFactory.getAccountDao();




    public void login() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        //int end = scanner.nextInt();
        System.out.println("/////////////////////////////////////|||||||||||||||||||\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$                _____________");
        System.out.println("Welcome to National Bank                                   ________________");
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$                                             ___________");
        System.out.println("////////////////////////////////////||||||||||||||||||||\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        Boolean flag = true;
        while (flag){

            System.out.println();
            System.out.println("LOGIN MENU");
            System.out.println("PRESS 1: FOR USER ");
            System.out.println("PRESS 2: FOR CUSTOMER ");
            System.out.println("PRESS 3: FOR EMPLOYEE ");
            System.out.println("PRESS 4: EXIT ");

            int input = scanner.nextInt();
            boolean flag1 = true;
            switch (input) {
                case 1: {
                    // for User

                    while (flag1){
                        // sub menu
                        System.out.println("Sub Menu");
                        System.out.println("PRESS 1: TO LOGIN AS A GUEST");
                        System.out.println("PRESS 2: TO REGISTER FOR ACCOUNT.");
                        System.out.println("PRESS 3: TO EXIT USER");
                        int new_input = scanner.nextInt();
                        switch (new_input) {

                            case 1: {
                                // condition 2 falls under 1?
                                // can you only register for a customer acct once your logged in?
                                dao.userLogin();
                                break;
                            }
                            case 2: {
                                // To register a new Customer
                                System.out.println("Enter a Customer ID");
                                int custId = scanner.nextInt();
                                System.out.println("Enter Customer name");
                                String name = scanner.next();
                                System.out.println("Enter Account type (checking or saving)");
                                String acctType = scanner.next();
                                System.out.println("What is your starting balance.");
                                double initBalance = scanner.nextDouble();
                                String status = "Pending";
                                Customer customer = new Customer(custId, name, acctType, status, initBalance );
                                dao.userRegistration(customer);
                                dao.updateCustomer(customer);
                                System.out.println("waiting on account approval............\n");
                                // show the table.
                                break;
                            }
                            case 3: {
                                System.out.println("Exiting");
                                flag1 = false;
                                return;
                            }
                            default: {
                                System.out.println("choose a valid entry");
                            }
                        }
                    }
                }
                case 2: {
                    boolean flag3 = true;
                    while (flag3){
                        // for customer
                        // sub menu
                        System.out.println("Sub Menu (CUSTOMER)");
                        System.out.println("PRESS 1: TO Create a new Bank Account. ");
                        System.out.println("PRESS 2: TO Deposit, Withdraw and View Balance. ");
                        System.out.println("PRESS 3: TO EXIT Customer ");
                        int input3 = scanner.nextInt();
                        switch (input3) {
                            case 1: {
                                System.out.println("Create new Bank Account.");
                                // To register a new Customer
                                System.out.println("Enter a Customer ID");
                                int custId = scanner.nextInt();
                                System.out.println("Enter Customer name");
                                String name = scanner.next();
                                System.out.println("Enter Account Type (checking or saving)");
                                String acctType = scanner.next();
                                String status = "Pending";
                                System.out.println("Enter initial balance.");
                                double initBalance = scanner.nextDouble();
                                Customer customer = new Customer(custId, name, acctType, status, initBalance);
                                dao.userRegistration(customer);
                                dao.updateCustomer(customer);
                                System.out.println("waiting on employee approval............\n");
                                break;
                            }
                            case 2: {
                                dao.createTbBalance();
                                System.out.println("Would you like to deposit to Saving Account (y/n)");
                                String input2 = scanner.next().toLowerCase();
                                if (input2.equals("y")) {
                                    AccountSaving accountSaving = new AccountSaving();
                                    accountSaving.deposit();
                                    AccountChecking accountChecking = new AccountChecking();
                                    dao.updateBalanceDeposit(accountChecking,accountSaving);


                                } else if (input2.equals("n")) {
                                    AccountChecking accountChecking = new AccountChecking();
                                    accountChecking.deposit();
                                }
                                break;
                            }
                            /*case 3: {
                                System.out.println("Would you like to withdraw from Saving Account (y/n)");
                                String input4 = scanner.next().toLowerCase();
                                if (input4.equals("y")) {
                                    AccountSaving accountSaving = new AccountSaving();
                                    accountSaving.withdraw();
                                    AccountChecking accountChecking = new AccountChecking();
                                    dao.updateBalanceDeposit(accountChecking,accountSaving);

                                } else if (input4.equals("n")) {
                                    AccountChecking accountChecking = new AccountChecking();
                                    accountChecking.withdraw();
                                }
                                break;
                            }*/

                            case 3: {
                                System.out.println("exit");
                                flag3 = false;
                                return;
                            }
                            default:
                            System.out.println("you choose customer");
                        }
                    }
                }
                case 3: {
                    // for employee

                while (flag){
                    System.out.println("PRESS 1: Approve or deny pending Customer Accounts.");
                    System.out.println("PRESS 2: View Customer Accounts.");
                    System.out.println("PRESS 3: TO EXIT Employee");
                    int new_input2 = scanner.nextInt();
                    switch (new_input2) {

                        case 1: {
                            dao.approveCustomer();
                            break;
                        }
                        case 2: {
                            dao.toString1();

                            break;
                        }

                        case 3: {
                            System.out.println("Exiting");
                            flag = false;
                            break;
                        }
                        default:
                            System.out.println("choose a valid entry");
                    }
                    }
                }
                case 4: {
                    // to exit
                    System.out.println("Thank for Visiting the National Bank");
                    //System.out.println("Exiting now");
                    flag = false;
                    System.exit(0);
                }
                default:
                    System.out.println("Choose a valid Entry.");
            }
        }
    }
}
