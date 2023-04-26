package application.museum.People;

import application.museum.Departments.Public_Education;

import java.util.ArrayList;
import java.util.Date;

public class Students extends internals{
    private Integer StudentID;
    private String institute_name;
    private course course_name;
    private Date Starting_date;
    private Date Finishing_date;
    private ArrayList<course> allcourses=new ArrayList<>();


    public Students(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress, Integer studentID, String instituteName, course courseName, Date startingDate) {
        super(name, gender, mobile_no, photo, email, dob, adress);
        this.StudentID = studentID;
        this.institute_name = instituteName;
        this.course_name = courseName;
        this.Starting_date = startingDate;
        this.Finishing_date=null;
        this.allcourses.add(courseName);
        //this.isCoursefinished=false;
    }
    public Students(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress, Integer studentID, String instituteName, course courseName, Date startingDate,Date finishing_date) {
        super(name, gender, mobile_no, photo, email, dob, adress);
        this.StudentID = studentID;
        this.institute_name = instituteName;
        this.course_name = courseName;
        this.Starting_date = startingDate;
        this.Finishing_date=finishing_date;
        //this.isCoursefinished=false;
        this.allcourses.add(courseName);
    }
    public String getInstitute_name(){
        return this.institute_name;
    }
    public void setInstitute_name(String instituteName){
        this.institute_name=instituteName;
    }
    public Integer getStudentID(){
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
        //this.isCoursefinished = isCoursefinished;
        this.course_name.setIscourseCompleted(isCoursefinished);
    }
    public void addCourse(course courseName, Date StartingDate) {
        this.course_name = courseName;
        this.Starting_date = StartingDate;
        this.Finishing_date = null;
        //this.isCoursefinished = false;
        this.allcourses.add(courseName);
    }
    public Date getStarting_date(){
        return this.Starting_date;
    }
    public ArrayList<course> Courses(){
        return this.allcourses;
    }

}
