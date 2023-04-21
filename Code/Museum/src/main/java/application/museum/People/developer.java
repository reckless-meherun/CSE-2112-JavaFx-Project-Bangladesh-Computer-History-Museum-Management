package application.museum.People;

import java.util.ArrayList;
import java.util.Date;

public class developer extends Employee{
    private ArrayList<String> deveopment_sectors;
    private ArrayList<String> projects;
    private String cur_project;
    public developer(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress, String employeeId, String department, String designation, String workTime, Date joiningDate, Date resigningDate, ArrayList<String> projects, String cuProject, ArrayList<String> deveopmentSectors, String curProject) {
        super(name, gender, mobile_no, photo, email, dob, adress, employeeId, department, designation, workTime, joiningDate, resigningDate);
        this.projects = projects;
        deveopment_sectors = deveopmentSectors;
        this.cur_project = curProject;

    }

    developer(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress, String employeeId, String department, String designation, String workTime, Date joiningDate, ArrayList<String> projects, String cuProject, ArrayList<String> deveopmentSectors, String curProject) {
        super(name, gender, mobile_no, photo, email, dob, adress, employeeId, department, designation, workTime, joiningDate);
        this.projects = projects;
        deveopment_sectors = deveopmentSectors;
        this.cur_project = curProject;

    }
    public  ArrayList<String> getDeveopment_sectors(){
        return this.deveopment_sectors;
    }
    public ArrayList<String> getProjects(){
        return this.projects;
    }
    public String getCur_project(){
        return this.cur_project;
    }
    public void setCur_project(String project){
        this.cur_project=project;
        this.projects.add(project);
    }
    public void adddevelopmentSector(String sector){
        this.deveopment_sectors.add(sector);
    }
    public void addproject(String project){
        this.projects.add(project);
    }
    public Integer getnumberOfprojects(){
        return this.projects.size();
    }

}
