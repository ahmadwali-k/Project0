package revature;

public abstract class User {
    public String name;
    public String status;
    public double initBalance;
    private int userid;
    private int password;
    private String email;
    public String gender;
    public String acctType;

    public User() {
    }
    public User(int userid, String name) {
        this.userid = userid;
        this.name = name;
    }
    public User( int password, String email, String gender, String acctType) {

        this.password = password;
        this.email = email;
        this.gender = gender;
        this.acctType = acctType;
    }

    // apply for a new bank account
    public int getUserid() {return userid;}
    public int getPassword() {return password;}
    public void setUserid(int userid) {this.userid = userid;}
    public void setPassword(int password) {this.password = password;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
}
