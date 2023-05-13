package application.museum.Departments;

import java.util.Date;

public class Auditorium extends Non_Curatorial_Dept{

    private String AttendantName;
    private String TechnicianName;
    private String SpeakerName;

    public Auditorium()
    {

        this.AttendantName = "Not Set";
        this.SpeakerName = "Not Set";
        this.TechnicianName = "Not Set";
    }
    public Auditorium(String gal, String att, String spk, String tech,String name, int floor, String room, Date date)
    {
        super(name, floor, room, date);
        this.AttendantName = att;
        this.SpeakerName = spk;
        this.TechnicianName = tech;
    }
    public Auditorium(String[] s,String name, int floor, String room, Date date)
    {
        super(name, floor, room, date);
        this.AttendantName = s[1];
        this.SpeakerName = s[2];
        this.TechnicianName = s[3];
    }
    public void setAttendantName(String name)
    {
        this.AttendantName = name;
    }
    public String getAttendantName()
    {
        return this.AttendantName;
    }

    public void setTechnicianName(String tech)
    {
        this.TechnicianName = tech;
    }
    public String getSpeakerName()
    {
        return this.TechnicianName;
    }
    public void setSpeakerName(String speaker)
    {
        this.SpeakerName = speaker;
    }
    public String getTechnicianName()
    {
        return this.TechnicianName;
    }
}
