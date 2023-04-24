package application.museum.Departments;

public class Non_Curatorial_Dept implements Departments{
    private String DeptName;
    private String DeptFloor;
    private String DeptRoom;

    public Non_Curatorial_Dept()
    {
        this.DeptName = "New NC Department";
        this.DeptFloor = "No floor attached";
        this.DeptRoom = "No room attached";
    }
    public Non_Curatorial_Dept(String[] s)
    {
        this.DeptName = s[0];
        this.DeptFloor = s[1];
        this.DeptRoom = s[2];
    }
    public Non_Curatorial_Dept(String name, String floor, String room)
    {
        this.DeptName = name;
        this.DeptFloor = floor;
        this.DeptRoom = room;
    }
    public void setDeptName(String name)
    {
        this.DeptName = name;
    }
    public String getDeptName()
    {
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
