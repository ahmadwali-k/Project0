package revature;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    Connection connection;

    public EmployeeDaoImpl () {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void createEmployee(Employee employee) throws SQLException {


    }
    @Override
    public void addEmployee(Employee employee) throws SQLException {


        String sql1 = "create table Employee (empId integer primary key AUTO_INCREMENT, name char(50));";
        PreparedStatement preparedStatement = connection.prepareStatement(sql1);
        //preparedStatement.setString(1, employee.getName());
        //preparedStatement.setInt(2, employee.getEmpId());
        preparedStatement.addBatch(sql1);

        String sql2 = "insert into Employee (empId, name) values (?, ?)";
        preparedStatement.addBatch(sql2);
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(1, employee.getEmpId());
        preparedStatement.setString(2, employee.getName());
       // statement.addBatch();
       // statement.addBatch(sql2);

        preparedStatement.executeUpdate(sql1);
        preparedStatement.executeUpdate(sql2);


    }

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
        String sql = "select * from Employee where id = " + empId;
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
}

