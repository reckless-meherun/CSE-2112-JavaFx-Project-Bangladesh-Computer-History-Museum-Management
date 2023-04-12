package People;

public class general_people implements people {
    private String name;
    private Gender gender;
    private String mobile_no;
    public general_people(String name, Gender gender, String mobile_no){
        this.name=name;
        this.gender=gender;
        this.mobile_no=mobile_no;
    }
    @Override
    public String Get_name() {
        return this.name;
    }

    @Override
    public Gender Get_gender() {
        return this.gender;
    }

    @Override
    public String Get_mobile_no() {
        return this.mobile_no;
    }

    @Override
    public void set_mobile_no(String mobile_no) {
        this.mobile_no=mobile_no;
    }
}
