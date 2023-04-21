package application.museum.Departments;

public class Hardware extends Curatorial_dept{
    private String[] Monument;
    private String EnvironmentControllerName;

    public Hardware()
    {

    }
    public void setMonument(String[] monu)
    {
        this.Monument = monu;
    }
    public String[] getMonument()
    {
        return this.Monument;
    }
    public void setEnvironmentControllerName(String EnvContrName)
    {
        this.EnvironmentControllerName = EnvContrName;
    }
    public String getEnvironmentControllerName()
    {
        return this.EnvironmentControllerName;
    }
}
