package application.museum.People;

import java.util.Date;

public class internals extends People_class{
    private String photo;
    private String email;
    private Date dob;
    private String Adress;
    public internals(String name, Gender gender, String mobile_no, String photo, String email, Date dob, String adress) {
        super(name, gender, mobile_no);
        this.photo = photo;
        this.email = email;
        this.dob = dob;
        this.Adress = adress;
    }
    public String get_photo(){
        return this.photo;
    }
    public void set_photo(String photo){
        this.photo=photo;
    }
    public String get_email(){
        return this.email;
    }
    public void set_email(String email){
        this.email=email;
    }
    public Date getDateOfBirth(){
        return this.dob;
    }
    public String get_Adress(){
        return this.Adress;
    }
    public void set_Adress(String Adress){
        this.Adress=Adress;
    }

}
