package application.museum.People;

import java.util.Date;

public class Employee extends internals {
    private Integer employee_id;
    private String department;
    private String Designation;
    private String WorkTime;
    private Date JoiningDate;
    private Date resigningDate;

    public Employee(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress, Integer employeeId, String department, String designation, String workTime, Date joiningDate, Date resigningDate) {
        super(name, gender, mobile_no, photo, email, dob, adress);
        this.employee_id = employeeId;
        this.department = department;
        this.Designation = designation;
        this.WorkTime = workTime;
        this.JoiningDate = joiningDate;
        this.resigningDate = resigningDate;
    }

    public Employee(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress, Integer employeeId, String department, String designation, String workTime, Date joiningDate) {
        super(name, gender, mobile_no, photo, email, dob, adress);
        this.employee_id = employeeId;
        this.department = department;
        this.Designation = designation;
        this.WorkTime = workTime;
        this.JoiningDate = joiningDate;
        this.resigningDate = null;

    }
    public void setDepartment(String department){
        this.department=department;
    }
    public String getDepartment(){
        return this.department;
    }
    public Integer getEmployee_id(){
        return this.employee_id;
    }
    public String getDesignation(){
        return this.Designation;
    }
    public void setDesignation(String designation){
        this.Designation=designation;
    }
    public Date getJoiningDate(){
        return this.JoiningDate;
    }
    public void setResigningDate(Date resigningDate){
        this.resigningDate=resigningDate;
    }
    public Date getResigningDate(){
        return this.resigningDate;
    }
    public String getWorkTime(){
        return this.WorkTime;
    }
    public void setWorkTime(String workTime){
        this.WorkTime=workTime;
    }

}

