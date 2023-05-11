package application.museum.Departments;

import java.util.Date;

public class Artifacts
{
    private int doc_no;
    private Date lastUpdate;
    private String category;
    private String title_description;
    private String dept;
    private int row;
    private int room;
    private int level;
    private String imagePath;

    public Artifacts()
    {
        doc_no = 0;
        lastUpdate = null;
        category = null;
        title_description = null;
        dept = null;
        row = 0;
        room = 0;
        level = 0;
        imagePath = null;
    }

    public Artifacts(int doc_no, Date lastUpdate, String category, String title_description, String dept, int row, int room, int level, String imagePath)
    {
        this.doc_no = doc_no;
        this.lastUpdate = lastUpdate;
        this.category = category;
        this.title_description = title_description;
        this.dept = dept;
        this.row = row;
        this.room = room;
        this.level = level;
        this.imagePath = imagePath;
    }

    public int getDoc_no()
    {
        return doc_no;
    }
    public Date getLastUpdate()
    {
        return lastUpdate;
    }
    public String getCategory()
    {
        return category;
    }
    public String getTitle_description()
    {
        return title_description;
    }
    public int getRow()
    {
        return row;
    }
    public int getRoom()
    {
        return room;
    }
    public int getLevel()
    {
        return level;
    }

    public String getDept()
    {
        return dept;
    }

    public String getImagePath()
    {
        return imagePath;
    }
}
