package application.museum.Departments;
import java.util.Date;
import java.util.ArrayList;

public class Curatorial_dept implements Departments{
    private String DeptName;
    private int DeptLevel;
    private String CuratorName;
    private String GuideName;
    private Date lastUpdate;

    public Curatorial_dept()
    {
        DeptName = null;
        DeptLevel = 0;
        CuratorName = null;
        GuideName = null;
        lastUpdate = null;
    }

    public void setDeptName(String name)
    {
        this.DeptName = name;
    }
    public String getDeptName() {
        return this.DeptName;
    }
    public void setDeptLevel(int floor)
    {
        this.DeptLevel = floor;
    }
    public int getDeptLevel()
    {
        return this.DeptLevel;
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
}
