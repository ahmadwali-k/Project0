package revature;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    // CRUD
    //create, add, delete, update.

    void userLogin();
    // to add a customer
    void userRegistration(User user) throws SQLException;
    List<User> getUsers() throws SQLException;
    List<Customer> getCustomers() throws SQLException;

    //customer
    void viewBalance() throws SQLException;
    void createTbBalance() throws SQLException;
    void updateBalanceDeposit(AccountChecking accountChecking, AccountSaving accountSaving) throws SQLException;
    void updateBalanceDepositCK() throws SQLException;
    void updateBalanceWithdrawSA() throws SQLException;


    // withdraw, deposit      -- (transaction)
    // Transfer to and from account           -- (transaction)

    //CRUD
    void updateCustomer(Customer customer) throws SQLException;
    List<Customer> addCustomers() throws SQLException;
    Customer getCustomerById(int id) throws SQLException;

    // Employee
    void approveCustomer() throws SQLException;
    void toString1() throws SQLException;
    // view customer account
    // view all transactions.
    //void addEmployee(Employee employee) throws SQLException;

    //CRUD
    void updateEmployee(Employee employee) throws SQLException;
    void deleteEmployee(int id) throws SQLException;
    List<Employee> getEmployees() throws SQLException;
    Employee getEmployeeById(int id) throws SQLException;


}
