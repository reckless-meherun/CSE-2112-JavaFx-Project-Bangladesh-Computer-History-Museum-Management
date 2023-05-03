package application.museum.Departments;

public interface Departments {
    public void setDeptName(String Name);
    public String getDeptName();
    public void setDeptFloor(int floor_number);
    public int getDeptFloor();
    public void addDept(String S1, int s2);
    public void setCleaner(String[] s);
    public String getCleaner();
}
