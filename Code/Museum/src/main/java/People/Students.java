package People;

import java.util.ArrayList;
import java.util.Date;

public class Students extends internals{
    private String StudentID;
    private String institute_name;
    private course course_name;
    private Date Starting_date;
    private Date Finishing_date;
    private ArrayList<course> allcourses;
    private boolean isCoursefinished;

    public Students(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress, String studentID, String instituteName, course courseName, Date startingDate) {
        super(name, gender, mobile_no, photo, email, dob, adress);
        this.StudentID = studentID;
        this.institute_name = instituteName;
        this.course_name = courseName;
        this.Starting_date = startingDate;
        this.Finishing_date=null;
        this.allcourses.add(courseName);
        this.isCoursefinished=false;
    }
    public Students(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress, String studentID, String instituteName, course courseName, Date startingDate,Date finishing_date,ArrayList<course> past_courses) {
        super(name, gender, mobile_no, photo, email, dob, adress);
        this.StudentID = studentID;
        this.institute_name = instituteName;
        this.course_name = courseName;
        this.Starting_date = startingDate;
        this.Finishing_date=finishing_date;
        this.allcourses=past_courses;
        this.isCoursefinished=false;
        this.allcourses.add(courseName);
    }
    public String getInstitute_name(){
        return this.institute_name;
    }
    public void setInstitute_name(String instituteName){
        this.institute_name=instituteName;
    }
    public String getStudentID(){
        return this.StudentID;
    }
    public course getCourse(){
        return this.course_name;
    }
    public void setFinishing_date(Date finishing_date){
        this.Finishing_date=finishing_date;
        this.course_name.setFinishingDate(finishing_date);
    }
    public Date getFinishing_date(){
        return this.Finishing_date;
    }
    public void updateIsCoursefinished(boolean isCoursefinished){
        this.isCoursefinished = isCoursefinished;
        this.course_name.setIscourseCompleted(isCoursefinished);
    }
    public void addCourse(course courseName, Date StartingDate) {
        this.course_name = courseName;
        this.Starting_date = StartingDate;
        this.Finishing_date = null;
        this.isCoursefinished = false;
        this.allcourses.add(courseName);
    }
    public ArrayList<course> Courses(){
        return this.allcourses;
    }





}
