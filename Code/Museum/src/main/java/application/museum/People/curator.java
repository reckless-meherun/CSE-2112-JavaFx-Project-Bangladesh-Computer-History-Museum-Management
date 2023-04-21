package application.museum.People;

import java.util.Date;

public class curator extends Employee{
    private String Field;
    public curator(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress, String employeeId, String department, String designation, String workTime, Date joiningDate, Date resigningDate, String field) {
        super(name, gender, mobile_no, photo, email, dob, adress, employeeId, department, designation, workTime, joiningDate, resigningDate);
        Field = field;
    }

    curator(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress, String employeeId, String department, String designation, String workTime, Date joiningDate, String field) {
        super(name, gender, mobile_no, photo, email, dob, adress, employeeId, department, designation, workTime, joiningDate);
        Field = field;
    }
    public void setField(String filed){
        this.Field=filed;
    }
    public String getField(){
        return this.Field;
    }
}
