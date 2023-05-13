package application.museum.Departments;

public class Security extends Non_Curatorial_Dept{
    private String[] camera;
    private String watcher;

    private Integer row;
    public Security()
    {
        this.camera = null;
        this.watcher = null;
    }
    public Security(String[] cam, String watch, Integer row)
    {
        this.camera = cam;
        this.watcher = watch;
        this.row = row;
    }
    public void setCamera(String[] cam)
    {
        this.camera = cam;
    }
    public String[] getCamera()
    {
        return this.camera;
    }
    public void setWatcher(String watch)
    {
        this.watcher = watch;
    }
    public String getWatcher()
    {
        return this.watcher;
    }
    public Integer getRow(){
        return this.row;
    }

}
