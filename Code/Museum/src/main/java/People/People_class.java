package People;

public class People_class implements people{
    private String name;
    private Gender gender;
    private String mobile_no;

    public People_class()
    {
        this.name  = "NOT SET";
        this.gender = null;
        this.mobile_no = "NOT SET";
    }
    public People_class(String name, Gender gen, String mob)
    {
        this.name = name;
        this.gender = gen;
        this.mobile_no = mob;
    }
    public People_class(Gender gen, String ... s)
    {
        this.name = s[0];
        this.gender = gen;
        this.mobile_no = s[1];
    }
    public String Get_name()
    {
        return this.name;
    }
    public Gender Get_gender()
    {
        return this.gender;
    }
    public String Get_mobile_no()
    {
        return this.mobile_no;
    }
    public void set_mobile_no(String mob)
    {
        this.mobile_no = mob;
    }
}
