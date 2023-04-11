package Departments;

import People.Students;

import java.util.ArrayList;

public class Public_Education extends Non_Curatorial_Dept{
    private String EducatorName;
    private ArrayList<People.Students> Students;

    public Public_Education()
    {
        this.EducatorName = "No Name set";
        this.Students = null;
    }
    public Public_Education(String name, ArrayList<Students> StudentList)
    {
        this.EducatorName = name;
        this.Students = StudentList;
    }
    public void setEducatorName(String name)
    {
        this.EducatorName = name;
    }
    public String getEducatorName()
    {
        return this.EducatorName;
    }
    public void setStudents(ArrayList<Students> StudentList)
    {
        this.Students = StudentList;
    }
    public ArrayList<Students> getStudents()
    {
        return this.Students;
    }
}
