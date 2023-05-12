package Tickets;

import java.util.Date;

public class Notice {
    private Integer Id;
    private String name;
    private Date date;
    private String file;

    public Notice(Integer id, String name, Date date, String file) {
        Id = id;
        this.name = name;
        this.date = date;
        this.file = file;
    }
    public Integer getId(){
        return this.Id;
    }
    public String getName(){
        return this.name;
    }
    public Date getDate(){
        return this.date;
    }
    public String getFile(){
        return this.file;
    }
}
