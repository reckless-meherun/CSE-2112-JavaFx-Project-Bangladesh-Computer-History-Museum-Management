package application.museum.Departments;

import java.util.Date;

public class Photo extends Non_Curatorial_Dept
{
    Date date;
    int catalog_no;
    int doc_no;
    String title;
    String description;
    String department;
    int position;
    int room;

    Photo()
    {

    }
    Photo(Date date, int catalog_no, int doc_no, String title, String description, String department, int position, int room)
    {
        this.date = date;
        this.doc_no = doc_no;
        this.catalog_no = catalog_no;
        this.title = title;
        this.description = description;
        this.department = department;
        this.position = position;
        this.room = room;
    }
    // Photo class contains details of a photo
    Date getDate()
    {
        return date;
    }
    int getCatalog_no()
    {
        return catalog_no;
    }
    int getDoc_no()
    {
        return doc_no;
    }
    String getTitle()
    {
        return title;
    }
    String getDescription()
    {
        return description;
    }
    String getDepartment()
    {
        return department;
    }
    int getPosition()
    {
        return position;
    }
    int getRoom()
    {
        return room;
    }
}
