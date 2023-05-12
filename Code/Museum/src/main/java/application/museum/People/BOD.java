package application.museum.People;

import java.util.Date;

public class BOD extends internals{
    private Posts designation;

    private Integer Id;
    public BOD(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress, Posts designation, Integer id)
    {
        super(name, gender, mobile_no, photo, email, dob, adress);
        this.designation = designation;
        Id = id;
    }
    public void updateDesignation(Posts designation){
        this.designation=designation;
    }
    public Posts getDesignation()
    {
        return this.designation;
    }

    public Integer getId(){
        return this.Id;
    }
}
