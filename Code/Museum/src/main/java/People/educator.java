package People;

import java.util.Date;

public class educator extends Employee{
    public educator(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress, String employeeId, String department, String designation, String workTime, Date joiningDate, Date resigningDate) {
        super(name, gender, mobile_no, photo, email, dob, adress, employeeId, department, designation, workTime, joiningDate, resigningDate);
    }

    public educator(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress, String employeeId, String department, String designation, String workTime, Date joiningDate) {
        super(name, gender, mobile_no, photo, email, dob, adress, employeeId, department, designation, workTime, joiningDate);
    }
}
