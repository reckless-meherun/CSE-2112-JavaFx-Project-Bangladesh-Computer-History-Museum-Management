package application.museum.People;

import java.util.Date;

public class course {
    private String CourseName;
    private Date StartingDate;
    private Date finishingDate;
    private educator instructor;
    private Boolean courseCompleted;

    private Integer studencnt;

    public course(String courseName, Date startingDate, Date finishingDate, educator instructor, boolean isCoursecompleted,Integer studencnt) {
        CourseName = courseName;
        StartingDate = startingDate;
        this.finishingDate = finishingDate;
        this.instructor = instructor;
        this.courseCompleted = isCoursecompleted;
        this.studencnt=studencnt;
    }
    public course(String courseName, Date startingDate, educator instructor,boolean isCoursecompleted,Integer studencnt) {
        CourseName = courseName;
        StartingDate = startingDate;
        this.finishingDate = null;
        this.instructor = instructor;
        this.courseCompleted = isCoursecompleted;
        this.studencnt=studencnt;
    }
    public course(String courseName, Date startingDate,boolean isCoursecompleted,Integer studencnt) {
        CourseName = courseName;
        StartingDate = startingDate;
        this.finishingDate = null;
        this.instructor = null;
        this.courseCompleted = isCoursecompleted;
        this.studencnt=studencnt;
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
//    public Boolean isCoursecompleted(){
//        return this.courseCompleted;
//    }
    public void setIscourseCompleted(boolean data){
        this.courseCompleted=data;
    }
    public educator getInstructor(){
        return this.instructor;
    }
    public void updateInstuctor(educator Instructor){
        this.instructor=Instructor;
    }

    public Integer getStudencnt() {
        return studencnt;
    }
    public void setStudencnt(Integer x){
        this.studencnt=x;
    }

    public Boolean getCourseCompleted() {
        return courseCompleted;
    }
}
