package application.museum.People;


import java.util.ArrayList;
import java.util.Date;

public class educator extends Employee
{
    private ArrayList<String> specializations;
    private ArrayList<course> courses;
    private course cur_course;
    private Integer numOfcourses;
    public educator(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress, Integer employeeId, String department, String designation, String workTime, Date joiningDate, Date resigningDate, ArrayList<String> specializations, ArrayList<course> courses, course curCourse) {
        super(name, gender, mobile_no, photo, email, dob, adress, employeeId, department, designation, workTime, joiningDate, resigningDate);
        this.specializations = specializations;
        this.courses = courses;
        cur_course = curCourse;
        this.numOfcourses=courses.size();
    }

    public educator(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress, Integer employeeId, String department, String designation, String workTime, Date joiningDate, ArrayList<String> specializations, ArrayList<course> courses, course curCourse) {
        super(name, gender, mobile_no, photo, email, dob, adress, employeeId, department, designation, workTime, joiningDate);
        this.specializations = specializations;
        this.courses = courses;
        cur_course = curCourse;
        this.numOfcourses=courses.size();
    }
    public ArrayList<String> getSpecializations(){
        return this.specializations;
    }
    public ArrayList<course> getCourses(){
        return this.courses;
    }
    public course getCur_course(){
        return this.cur_course;
    }
    public void addSpecializations(String specialization){
        specializations.add(specialization);
    }
    public void finishCur_course(Date date){
        cur_course.setIscourseCompleted(true);
        cur_course.setFinishingDate(date);
    }
    public void add_course(educator e, Date date,String couse_name){
        cur_course= new course(couse_name,date,false,0);
        courses.add(cur_course);
        numOfcourses++;
    }
    public Integer getNumOfcourses(){
        return this.numOfcourses;
    }

}
