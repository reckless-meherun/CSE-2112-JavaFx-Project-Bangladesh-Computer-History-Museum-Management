package application.museum;

import application.museum.People.BOD;
import application.museum.People.Gender;
import application.museum.People.Posts;
import application.museum.People.Students;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardSceneController extends NavigationHandler implements Initializable
{
    @FXML
    public TreeView<String> treeView;
    @FXML
    AnchorPane deptPane;
    @FXML
    Label loggedIn;
    @FXML
    private Button home;
    @FXML
    private Button departments;
    @FXML
    private Button photogallery;
    @FXML
    private Button articles;
    @FXML
    private Button aboutus;
    @FXML
    private Button tickets;
    @FXML
    private Button LogoutButton;
    @FXML
    private Button GoBackButton;
    @FXML
    private Text ProfileIcon;
    @FXML
    private AnchorPane SceneTwo;


    @FXML
    private Text dateField1;

    @FXML
    private Text dateField2;

    @FXML
    private Text dateField3;

    @FXML
    private Text dateField4;


    @FXML
    private ImageView imageViewPres;

    @FXML
    private ImageView imageViewPres1;

    @FXML
    private ImageView imageViewPres11;

    @FXML
    private ImageView imageViewPres2;

    @FXML
    private Text nameFieldPres;

    @FXML
    private Text nameFieldVicePres;

    @FXML
    private Text notice1;

    @FXML
    private Text notice2;

    @FXML
    private Text notice3;

    @FXML
    private Text notice4;


    @FXML
    private Text studentName;


    @FXML
    private Text upcomingclassteachername2;

    @FXML
    private Text upcomingclassteachername3;

    @FXML
    private Text upcomingclassteachername4;

    @FXML
    private Text upcomingclassteachername5;

    @FXML
    private Text upcomingclasstime2;

    @FXML
    private Text upcomingclasstime3;

    @FXML
    private Text upcomingclasstime4;

    @FXML
    private Text upcomingclasstime5;

    private String url = "jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\aboutus.db";

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public static void pushtostack()
    {
        DBUtils.prevfxml.push("DashboardScene.fxml");
    }

    public void displayName(String username)
    {
        loggedIn.setText("Hello " + username + " !");
    }

    @FXML
    public void logout(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Do you want to save?");

        if (alert.showAndWait().get() == ButtonType.OK)
        {
            Stage stage = (Stage) SceneTwo.getScene().getWindow();
            System.out.println("You have successfully logged out");
            stage.close();
        }
    }

    @FXML
    public void switchToSceneOne(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Do you want to save?");

        if (alert.showAndWait().get() == ButtonType.OK)
        {
            DBUtils.prevfxml.clear();
            Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1101, 680);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException
    {
        if (DBUtils.prevfxml.empty())
        {
            return;
        }
        String fxml = DBUtils.prevfxml.pop();
//        DBUtils.prevfxml.pop();
        System.out.println(fxml);
        if (fxml == "LoginScene.fxml")
        {
            DBUtils.prevfxml.push(fxml);
            this.switchToSceneOne(event);
        } else if (fxml == "DashboardScene.fxml")
        {
            DBUtils.changeScene(event, fxml, DBUtils.username);
        } else
        {
            DBUtils.changeScene(event, fxml, true);
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            NavigationHandler.HandleNavigation("DashboardScene.fxml", home, treeView, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        showData();
    }
    StringBuilder getrespath(){
        StringBuilder resourcesPath = new StringBuilder(getClass().getResource("").getPath());
        //int n=resourcesPath.length();
        resourcesPath.deleteCharAt(0);
        for(int i=0;i<resourcesPath.length();i++){
            if(resourcesPath.charAt(i)=='%'){
                resourcesPath.replace(i,i+3," ");
            }
            if(resourcesPath.charAt(i)=='t'&&resourcesPath.charAt(i+1)=='a'&&resourcesPath.charAt(i+2)=='r'&& resourcesPath.charAt(i+3)=='g'&& resourcesPath.charAt(i+4)=='e'&& resourcesPath.charAt(i+5)=='t'&& resourcesPath.charAt(i+6)=='/'){
                resourcesPath.delete(i-1,resourcesPath.length());
                break;
            }
        }
        return resourcesPath;
    }
    public ArrayList<BOD> datalist()
    {
        ArrayList<BOD> datalist = new ArrayList<>();

        String sql;
        sql ="SELECT * FROM BOD";

        try {
            connect= DBUtils.connectDB(url);
            prepare = connect.prepareStatement(sql);
            result=prepare.executeQuery();


            while(result.next())
            {
                StringBuilder resourcesPath=getrespath();
                resourcesPath.append(result.getString("photo"));
                Gender gm;
                if(result.getString("Gender").equals("MALE")){
                    gm= Gender.MALE;
                } else if (result.getString("Gender").equals("FEMALE")) {
                    gm=Gender.FEMALE;
                }
                else gm= Gender.OTHER;
                Students emp;
                Posts p=null;
                for(Posts post:Posts.values()){
                    if(post.toString().equals(result.getString("post"))){
                        p=post;
                    }
                }
                BOD bod= new BOD(result.getString("name"),gm,result.getString("phone"),resourcesPath.toString(),result.getString("email"),result.getDate("dob"),result.getString("adress"),p,result.getInt("ID"));
                datalist.add(bod);
                System.out.println(bod.getName());
                System.out.println(bod.getDesignation());






            }

        }catch (Exception e) {
            System.out.println("Students database error");
            e.printStackTrace();
        }
        finally
        {
            try
            {
                connect.close();
                result.close();
                prepare.close();
                if(statement!=null) {
                    statement.close();
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return datalist;
    }
    public void showData()
    {
        ArrayList<BOD> Datalist=datalist();
        BOD president=null;
        for(int i=Datalist.size()-1;i>=0;i--){
            if(Datalist.get(i).getDesignation().toString().equals("President")){
                president=Datalist.get(i);
                break;
            }
        }
        //System.out.println(president);
        BOD vpres=null;
        for(int i=Datalist.size()-1;i>=0;i--){
            if(Datalist.get(i).getDesignation().toString().equals("Vice_President")){
                vpres=Datalist.get(i);
                break;
            }
        }

        if(president!=null){
            nameFieldPres.setText(president.getName());
            imageViewPres.setVisible(false);

            imageViewPres2.setVisible(true);
            try {
                imageViewPres2.setImage(new Image("file:/"+president.getPhoto()));
                //System.out.println(president.getPhoto());
                //System.out.println("11");
            }
            catch (Exception e){
                System.out.println(president.getPhoto());
            }

        }
        else{
            nameFieldPres.setText("name");
            imageViewPres2.setVisible(false);
            imageViewPres.setVisible(true);
        }
        if(vpres!=null){
            nameFieldVicePres.setText(vpres.getName());
            imageViewPres1.setVisible(false);

            imageViewPres11.setVisible(true);
            try {
                imageViewPres11.setImage(new Image("file:/"+vpres.getPhoto()));
                System.out.println(vpres.getPhoto());
            }
            catch (Exception e){
                System.out.println(vpres.getPhoto());
            }

        }
        else{
            nameFieldVicePres.setText("name");
            imageViewPres11.setVisible(false);
            imageViewPres1.setVisible(true);
        }

    }
}