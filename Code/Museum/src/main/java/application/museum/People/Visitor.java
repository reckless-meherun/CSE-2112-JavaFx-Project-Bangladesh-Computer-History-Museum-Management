package application.museum.People;

import java.util.Date;

public class Visitor extends People_class
{
    private Date last_vis_date;
    private String email;
   // private String occupation;
    private int age;
    private int TotalVisitCount;
    private String language;

    public Visitor()
    {
        this.last_vis_date = null;
        this.email = null;
        //this.occupation = null;
        this.age = 0;
        this.TotalVisitCount = 0;
        this.language = null;
    }

    public Visitor(Date last_vis, String name, int ages, Gender gender, String email, String mobile, int cnt, String language)
    {
        super(name, gender, mobile);
        this.TotalVisitCount = cnt;
        this.age = ages;
        this.email = email;
//        this.occupation = occup;
        this.last_vis_date = last_vis;
        this.language = language;
    }

    public Date getLast_vis_date()
    {
        return this.last_vis_date;
    }

    public void setLast_vis_date(Date last_vis_date)
    {
        this.last_vis_date = last_vis_date;
    }

//    public String getOccupation()
//    {
//        return occupation;
//    }

   // public void setOccupation(String occupation)
//    {
//        this.occupation = occupation;
//    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    @Override
    public void set_mobile_no(String mob)
    {
        super.set_mobile_no(mob);
    }

    @Override
    public String getMobile_no()
    {
        return super.getMobile_no();
    }

    public int getTotalVisitCount()
    {
        return TotalVisitCount;
    }

    public void setTotalVisitCount(int totalVisitCount)
    {
        TotalVisitCount = totalVisitCount;
    }

    @Override
    public Gender getGender()
    {
        return super.getGender();
    }

    @Override
    public String getName()
    {
        return super.getName();
    }

    public String getLanguage()
    {
        return this.language;
    }
}
