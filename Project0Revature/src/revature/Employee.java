package revature;

public class Employee extends User {
    private int empId;
    private String name;


    public Employee() {

    }

    public  Employee(int empId, String name) {
        this.empId = empId;
        this.name = name;
    }

    // getters, setters.
    public String getName() {
        return name;
    }
    public int getEmpId() {
        return empId;
    }
    public void setName (String name){
        this.name = name;
    }
    public void setEmpId (int empId) {
        this.empId = empId;
    }
}
