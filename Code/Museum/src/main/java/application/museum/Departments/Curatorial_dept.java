package application.museum.Departments;
import net.sf.jasperreports.engine.util.JRExpressionUtil;
import net.sf.jasperreports.renderers.SimpleRenderToImageAwareDataRenderer;

import java.util.Date;
import java.util.ArrayList;

public class Curatorial_dept implements Departments{
    private String deptName;
    private int deptLevel;
    private int room;
    private String cleanerName;
    private String guideName;
    private String envConName;
    private Date lastUpdate;

    public Curatorial_dept()
    {
        deptName = null;
        deptLevel = 0;
        envConName = null;
        guideName = null;
        cleanerName = null;
        lastUpdate = null;
    }
    public Curatorial_dept(Date lastUpdate, String deptName,  String guideName, String cleanerName, String envConName, int room,  int deptLevel)
    {
        this.lastUpdate = lastUpdate;
        this.deptName = deptName;
        this.deptLevel = deptLevel;
        this.cleanerName = cleanerName;
        this.guideName = guideName;
        this.envConName = envConName;
        this.room = room;
    }

   public Date getLastUpdate()
   {
       return this.lastUpdate;
   }
   public String getDeptName()
   {
       return this.deptName;
   }
   public int getDeptLevel()
   {
       return this.deptLevel;
   }
   public String getCleanerName()
   {
       return this.cleanerName;
   }
   public String getGuideName()
   {
       return this.guideName;
   }
   public String getEnvConName()
   {
       return this.envConName;
   }
   public int getRoom()
   {
       return this.room;
   }
}
