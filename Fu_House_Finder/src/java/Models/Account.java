package Models;

import java.util.Date;

public class Account {

    private int id;
    private String email;
    private String password;
    private int roleId;
    private int employeeId;
    private Date dateStart;
    private Date dateEnd;

    public Account() {
    }

    public Account(int id, String email, String password, int roleId, int employeeId, Date dateStart, Date dateEnd) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.employeeId = employeeId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", email=" + email + ", password=" + password + ", roleId=" + roleId + ", employeeId=" + employeeId + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + '}';
    }

}
