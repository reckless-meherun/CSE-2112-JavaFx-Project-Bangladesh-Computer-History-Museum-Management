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
    public Auditorium(String attendantName, String speakerName, String technicianName,String name, int floor, String room, Date date)
    {
        super(name, floor, room, date);
        this.AttendantName = attendantName;
        this.SpeakerName = speakerName;
        this.TechnicianName = technicianName;
    }
    public Auditorium(String attendantName, String speakerName, String technicianName,String name, String cleanerName, Date date)
    {
        super(name, cleanerName, date);
        this.AttendantName = attendantName;
        this.SpeakerName = speakerName;
        this.TechnicianName = technicianName;
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
        return this.SpeakerName;
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
