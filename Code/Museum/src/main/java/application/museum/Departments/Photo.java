package application.museum.Departments;

import java.util.Date;

public class Photo extends Non_Curatorial_Dept
{
    private Date date;
    private int catalog_no;
    private int doc_no;
    private String title;
    private String description;
    private String department;
    private int position;
    private int room;
    private String imagePath;

    public Photo()
    {
        this.date = null;
        this.catalog_no = 0;
        this.doc_no = 0;
        this.title = "";
        this.description = "";
        this.department = "";
        this.position = 0;
        this.room = 0;
        this.imagePath = "";

    }
    public Photo(Date date, int catalog_no, int doc_no, String title, String description, String department, int position, int room, String imagePath)
    {
        this.date = date;
        this.doc_no = doc_no;
        this.catalog_no = catalog_no;
        this.title = title;
        this.description = description;
        this.department = department;
        this.position = position;
        this.room = room;
        this.imagePath = imagePath;
    }
    // Photo class contains details of a photo
    public Date getDate()
    {
        return date;
    }
    public int getCatalog_no()
    {
        return catalog_no;
    }
    public int getDoc_no()
    {
        return doc_no;
    }
    public String getTitle()
    {
        return title;
    }
    public String getDescription()
    {
        return description;
    }
    public String getDepartment()
    {
        return department;
    }
    public int getPosition()
    {
        return position;
    }
    public int getRoom()
    {
        return room;
    }
    public String getImagePath()
    {
        return this.imagePath;
    }
}
