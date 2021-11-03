package revature;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class UserDaoImpl implements UserDao {
    Connection connection;

    public UserDaoImpl() {
        this.connection = ConnectionFactory.getConnection();
    }


    // method to login a new user as a guest.
    @Override
    public void userLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("To loggin as User");
        System.out.println("Hint: Use 'guest' for name and password to login.\n");
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        boolean isLogedIn = false;
        if (!isLogedIn) {
            if (name.equals("guest") && (password.equals("guest"))) {
                isLogedIn = true;
                System.out.println("you have successfully logged in \n");
            } else
                System.out.println("login failed, try again.");
        } else
            System.out.println("user already logged in.");

    }

    // method to register a new Customer
    @Override
    public void userRegistration(User user) throws SQLException {
        /*Statement statement = connection.createStatement();
        statement.addBatch("INSERT INTO EMPLOYEE(ID, NAME, DESIGNATION) "
                + "VALUES ('1','EmployeeName','Designation')");
        statement.addBatch("INSERT INTO EMP_ADDRESS(ID, EMP_ID, ADDRESS) "
                + "VALUES ('10','1','Address')");
        statement.executeBatch();*/


        String sql1 = "create table Customer (custId integer primary key auto_increment, name char(50), acctType char(50), status char(50), initBalance double(40,2));";
        PreparedStatement preparedStatement = connection.prepareStatement(sql1);
        preparedStatement.addBatch(sql1);
        preparedStatement.executeBatch();
    }

    @Override
    public void createTbBalance() throws SQLException {
        String sql2 = "create table Balance (checking double(40,2), saving double(40,2));";
        PreparedStatement preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.addBatch(sql2);
        preparedStatement.executeBatch();
    }

    @Override
    public void updateBalanceDeposit(AccountChecking accountChecking, AccountSaving accountSaving) throws SQLException {

        String sql2 = "insert into Balance (checking, saving) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setDouble(1, accountChecking.deposit());
        preparedStatement.setDouble(2, accountSaving.deposit());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Balance has been updated");
        } else
            System.out.println("Update Failed");
    }

    public void updateBalanceDepositCK() throws SQLException {
        AccountChecking accountChecking = new AccountChecking();
        String sql2 = "insert into Balance (checking, saving) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setDouble(2, accountChecking.deposit());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Balance has been updated");
        } else
            System.out.println("Update Failed");
    }
    public void updateBalanceWithdrawSA() throws SQLException {
        AccountSaving accountSaving = new AccountSaving();
        String sql2 = "insert into Balance (checking, saving) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setDouble(2, accountSaving.withdraw());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Balance has been updated");
        }
        else
            System.out.println("Update Failed");
    }

    @Override
    public void viewBalance() throws SQLException {
        String sql = "select * from balance";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
    }
    // method to save users.
    @Override
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "select * from Users";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int userid = resultSet.getInt(1);
            String name = resultSet.getString(2);
            User user = new User(userid, name) {
            };
            users.add(user);
        }

        return users;
    }
    @Override
    public void approveCustomer() throws SQLException {
        Employee employee = new Employee();
        Customer customer = new Customer();
       // customer = new Customer(custId, name, acctType, status, initBalance);
       String approve;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Approve the request: (y/n) ");
        String input = scanner.next();
        if (input.equals("y")) {
            System.out.println("Approved");

            customer.status = "approved";
            updateCustomer(customer);

        } else {
            System.out.println("Rejected");
            customer.status = "rejected";
            updateCustomer(customer);


        }
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        String sql2 = "insert into Customer (custId, name, acctType, status, initBalance) values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(1, customer.getCustId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.acctType);
        preparedStatement.setString(4, customer.status);
        preparedStatement.setDouble(5, customer.getInitBalance());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Updated Successfully\n");
        }
        else
            System.out.println("Update Failed");
    }
    @Override
    public List<Customer> getCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from Customer";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int custId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String acctType = resultSet.getString(3);
            String status = resultSet.getString(4);
            Double initBalance = resultSet.getDouble(5);
            Customer customer = new Customer(custId, name, acctType, status, initBalance);
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public List<Customer> addCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from Customer";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int custId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String acctType = resultSet.getString(3);
            String status = "Pending";
            double initBalance = resultSet.getDouble(5);

            Customer customer = new Customer(custId, name, acctType, status, initBalance);
            customers.add(customer);
            //boolean pending = false;
            /*boolean approve = true;
            if (employee.equals(approve)) {
                customers.add(customer);
                System.out.println("approved");
            } else {
                System.out.println("pending");
            }*/
        }

        return customers;
    }
    @Override
    public Customer getCustomerById(int custId) throws SQLException {

        Customer customer = new Customer();
        String sql = "select * from Customer where custId = " + custId;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        if (resultSet != null) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            customer = new Customer(id, name);
        }
        else
            System.out.println("No Records found.");

        return customer;
    }

    // adding new employees
    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "update Employee set name = ? where empId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setInt(2, employee.getEmpId());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Employee has been updated");
        }
        else
            System.out.println("Update Failed");
    }

    @Override
    public void deleteEmployee(int empId) throws SQLException {
        // delete employee by id number.
        String sql = "delete from Employee where empId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, empId);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Employee successfully deleted.");
        } else
            System.out.println("Could not processed with deleting.");

    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "select * from Employee";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            Employee employee = new Employee(id, name);
            employees.add(employee);
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(int empId) throws SQLException {
        Employee employee = new Employee();
        String sql = "select * from Employee where empId = " + empId;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        if (resultSet != null) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            employee = new Employee(id, name);
        }
        else
            System.out.println("No Records found.");

        return employee;
    }
    public void toString1() throws SQLException {

        UserDaoImpl userDao = new UserDaoImpl();
        String sql = "Select * from customer";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.print("Id: " + resultSet.getInt(1) + ", Name: " +
                    resultSet.getString(2) + ", Account type: " + resultSet.getString(3)
                    + ", Status: " + resultSet.getString(4)
                    + ", Initial balance: " + resultSet.getDouble(5));
            System.out.println();
        }
    }
}
