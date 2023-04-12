package Departments;

public class Auditorium extends Non_Curatorial_Dept{
    private String GalleryName;
    private String AttendantName;
    private String TechnicianName;
    private String SpeakerName;

    public Auditorium()
    {
        this.GalleryName = "Not Set";
        this.AttendantName = "Not Set";
        this.SpeakerName = "Not Set";
        this.TechnicianName = "Not Set";
    }
    public Auditorium(String gal, String att, String spk, String tech)
    {
        this.GalleryName = gal;
        this.AttendantName = att;
        this.SpeakerName = spk;
        this.TechnicianName = tech;
    }
    public Auditorium(String[] s)
    {
        this.GalleryName = s[0];
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
    public void setGalleryName(String gal)
    {
        this.GalleryName = gal;
    }
    public String getGalleryName()
    {
        return this.GalleryName;
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
