package application.museum.Departments;

import java.util.Date;

public class Non_Curatorial_Dept implements Departments{
    private String DeptName;
    private int DeptFloor;
    private String DeptRoom;

    private Date date;

    public Non_Curatorial_Dept()
    {
        this.DeptName = "New NC Department";
        this.DeptFloor = 0;
        this.DeptRoom = "No room attached";
    }
    public Non_Curatorial_Dept(String[] s, Date date)
    {
        this.DeptName = s[0];
        this.DeptFloor = Integer.valueOf(s[1]);
        this.DeptRoom = s[2];
        this.date = date;
    }
    public Non_Curatorial_Dept(String name, int floor, String room, Date date)
    {
        this.DeptName = name;
        this.DeptFloor = floor;
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
    public void setDeptFloor(int floor)
    {
        this.DeptFloor = floor;
    }
    public int getDeptFloor()
    {
        return this.DeptFloor;
    }
    public void addDept(String name, int floor)
    {
        this.DeptName = name;
        this.DeptFloor = floor;
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
}
