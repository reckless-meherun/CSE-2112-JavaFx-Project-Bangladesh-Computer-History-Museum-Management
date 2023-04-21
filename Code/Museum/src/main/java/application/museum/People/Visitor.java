package application.museum.People;

import java.util.Date;

public class Visitor extends People_class {
    private Date last_vis_date;
    private String email;
    private String occupation;
    private int age;
    private int TotalVisitCount;

    public Visitor() {
        this.last_vis_date = null;
        this.email = null;
        this.occupation = null;
        this.age = 0;
        this.TotalVisitCount = 0;
    }

    public Visitor(String name, String mobile, Gender gender, Date last_vis, String email, String occu, int ages,
            int cnt) {
        super(name, gender, mobile);
        this.TotalVisitCount = 0;
        this.age = ages;
        this.email = email;
        this.occupation = occu;
        this.last_vis_date = last_vis;
    }

    public void setLast_vis_date(Date last_vis_date) {
        this.last_vis_date = last_vis_date;
    }
    public Date getLast_vis_date() {
        return this.last_vis_date;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    public String getOccupation() {
        return occupation;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }

    @Override
    public void set_mobile_no(String mob) {
        super.set_mobile_no(mob);
    }
    @Override
    public String Get_mobile_no() {
        return super.Get_mobile_no();
    }

    public void setTotalVisitCount(int totalVisitCount) {
        TotalVisitCount = totalVisitCount;
    }
    public int getTotalVisitCount() {
        return TotalVisitCount;
    }

    @Override
    public Gender Get_gender() {
        return super.Get_gender();
    }
    @Override
    public String Get_name() {
        return super.Get_name();
    }
}
