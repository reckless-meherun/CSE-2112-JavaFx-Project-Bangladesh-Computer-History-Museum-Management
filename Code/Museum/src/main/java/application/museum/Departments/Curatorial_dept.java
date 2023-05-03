package application.museum.Departments;

import java.util.ArrayList;

public class Curatorial_dept implements Departments{
    private String DeptName;
    private int DeptFloor;
    private String CuratorName;
    private String GuideName;
    private ArrayList<String> Artifacts; // Here I did not understand things. - Himel
    private ArrayList<String> Visitors; // Same goes for this

    public Curatorial_dept()
    {
        System.out.println("Curatorial Department created\n");
    }

    public void setDeptName(String name)
    {
        this.DeptName = name;
    }
    public String getDeptName() {
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
}
