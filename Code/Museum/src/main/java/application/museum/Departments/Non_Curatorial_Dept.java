package application.museum.Departments;

import java.util.Date;

public class Non_Curatorial_Dept implements Departments{
    private String DeptName;
    private Integer DeptLevel;
    private String DeptRoom;

    private Date date;
    private String cleanerName;

    public Non_Curatorial_Dept()
    {
        this.DeptName = "New NC Department";
        this.DeptLevel = 0;
        this.DeptRoom = "No room attached";
    }
    public Non_Curatorial_Dept(String deptName,String cleanerName, Date date)
    {
        this.DeptName = deptName;
        this.DeptLevel = 0;
        this.DeptRoom = "nai";
        this.date = date;
        this.cleanerName=cleanerName;
    }
    public Non_Curatorial_Dept(String name, int floor, String room, Date date)
    {
        this.DeptName = name;
        this.DeptLevel = floor;
        this.DeptRoom = room;
        this.date = date;
    }
    public void setDeptName(String name)
    {
        this.DeptName = name;
    }
    public String getDeptName()
    {
        return this.DeptName;
    }
    public String getDeptRoom(){
        return this.DeptRoom;
    }

    @Override
    public int getDeptLevel() {
        return this.DeptLevel;
    }

    public void setDeptLevel(int floor)
    {
        this.DeptLevel = floor;
    }

    public void addDept(String name, int floor)
    {
        this.DeptName = name;
        this.DeptLevel = floor;
    }
    public void setCleaner(String[] s)
    {

    }
    public String getCleaner()
    {
        return "Cleaner er kaaj ki?";
    }
    public Date getDate(){
        return this.date;
    }
    public String getCleanerName()
    {
        return this.cleanerName;
    }
}
