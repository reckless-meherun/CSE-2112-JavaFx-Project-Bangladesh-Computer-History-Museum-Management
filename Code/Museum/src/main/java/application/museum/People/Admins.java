package application.museum.People;

public class Admins{
    private Integer id;
    private String username;
    private String password;


    public Admins(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getUsername(){
        return this.username;
    }
    public Integer getId(){
        return this.id;
    }
    public String getPassword(){
        return this.password;
    }
}

