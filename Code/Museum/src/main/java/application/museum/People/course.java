package application.museum.People;

import java.util.Date;

public class course {
    private String CourseName;
    private Date StartingDate;
    private Date finishingDate;
    private educator instructor;
    private boolean isCoursecompleted;

    public course(String courseName, Date startingDate, Date finishingDate, educator instructor, boolean isCoursecompleted) {
        CourseName = courseName;
        StartingDate = startingDate;
        this.finishingDate = finishingDate;
        this.instructor = instructor;
        this.isCoursecompleted = isCoursecompleted;
    }
    public course(String courseName, Date startingDate, educator instructor) {
        CourseName = courseName;
        StartingDate = startingDate;
        this.finishingDate = null;
        this.instructor = instructor;
        this.isCoursecompleted = false;
    }
    public course(String courseName, Date startingDate) {
        CourseName = courseName;
        StartingDate = startingDate;
        this.finishingDate = null;
        this.instructor = null;
        this.isCoursecompleted = false;
    }

    public String getCourseName(){
        return this.CourseName;
    }
    public Date getStartingDate(){
        return this.StartingDate;
    }
    public void updateStartingDate(Date date){
        this.StartingDate=date;
    }
    public void setFinishingDate(Date date){
        this.finishingDate=date;
    }
    public Date getFinishingDate(){
        return this.finishingDate;
    }
    public boolean Iscompleted(){
        return this.isCoursecompleted;
    }
    public void setIscourseCompleted(boolean data){
        this.isCoursecompleted=data;
    }
    public educator getInstructor(){
        return this.instructor;
    }
    public void updateInstuctor(educator Instructor){
        this.instructor=Instructor;
    }
}
