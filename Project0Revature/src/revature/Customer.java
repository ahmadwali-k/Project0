package revature;

import java.sql.ResultSet;
import java.sql.Statement;

public class Customer extends User {
    private int custId;
    private String name;
    public String acctType;
    public String status;
    private double initBalance;

    public Customer() {}
    public Customer(int custId, String name) {
        this.custId = custId;
        this.name = name;
    }
    public Customer(int custId) {
        this.custId = custId;
    }
    public Customer(int custId, String name, String acctType, String status, double initBalance) {
        this.custId= custId;
        this.name = name;
        this.acctType = acctType;
        this.status = status;
        this.initBalance = initBalance;
    }

    // apply for a new bank account with starting balance
    // view balance (ch, sv)

    // withdraw, deposit      -- (transaction)
    // Transfer to and from account           -- (transaction)

    //getters and setters

    public int getCustId() {return custId;}

    public String getName() {
        return name;
    }

    public void setcustId(int custId) {
        this.custId = custId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setCustId(int custId) {this.custId = custId;}

    public double getInitBalance() {
        return initBalance;
    }

    public void setInitBalance(double initBalance) {
        this.initBalance = initBalance;
    }



}
