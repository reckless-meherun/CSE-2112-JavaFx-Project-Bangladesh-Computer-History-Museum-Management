package Departments;

import java.util.ArrayList;

public class Curatorial_dept implements Departments{
    private String DeptName;
    private String DeptFloor;
    private String CuratorName;
    private String GuideName;
    private ArrayList<String> Artifacts; // Here I did not understand things. - Himel
    private ArrayList<String> Visitors; // Same goes for this

    public void setDeptName(String name)
    {
        this.DeptName = name;
    }
    public String getDeptName() {
        return this.DeptName;
    }
    public void setDeptFloor(String floor)
    {
        this.DeptFloor = floor;
    }
    public String getDeptFloor()
    {
        return this.DeptFloor;
    }
    public void addDept(String name, String floor)
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
}
