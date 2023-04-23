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
    public String getPhoto(){
        return this.photo;
    }
    public void setPhoto(String photo){
        this.photo=photo;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public Date getDob(){
        return this.dob;
    }
    public String getAdress(){
        return this.Adress;
    }
    public void setAdress(String Adress){
        this.Adress=Adress;
    }

}
