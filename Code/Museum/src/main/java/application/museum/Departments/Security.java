package application.museum.Departments;

import java.util.Date;

public class Security extends Non_Curatorial_Dept{
    private String[] camera;
    private String watcher;

    private Integer row;

    private Integer total_camera;
    public Security()
    {
        this.camera = null;
        this.watcher = null;
        this.row=0;
        this.total_camera=0;
    }
    public Security(String[] cam, String watch, Integer row,String name, int floor, String room, Date date)
    {
        super(name,floor,room,date);
        this.camera = cam;
        this.watcher = watch;
        this.row = row;
        this.total_camera=camera.length;
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
    public Integer getTotal_camera(){
        return this.total_camera=camera.length;
    }

}
