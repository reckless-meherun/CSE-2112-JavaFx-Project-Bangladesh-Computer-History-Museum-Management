package application.museum;

import Tickets.Notice;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import java.awt.Desktop;

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
    private TextField noticefield1;

    @FXML
    private TextField noticefield2;

    @FXML
    private TextField noticefield3;

    @FXML
    private TextField noticefield4;


    @FXML
    private Text studentName;


    @FXML
    private Button update1;

    @FXML
    private Button update2;

    @FXML
    private Button update3;

    @FXML
    private Button update4;


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

    ArrayList<Notice> datalist = new ArrayList<>();

    private String url = "jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\aboutus.db";
    private String url1 = "jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\database.db";

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
        noticefield1.setVisible(false);
        noticefield2.setVisible(false);
        noticefield3.setVisible(false);
        noticefield4.setVisible(false);
        update1.setOnAction(e->{
            noticefield1.setVisible(true);
        });
        update2.setOnAction(e->{
            noticefield2.setVisible(true);
        });
        update3.setOnAction(e->{
            noticefield3.setVisible(true);
        });
        update4.setOnAction(e->{
            noticefield4.setVisible(true);
        });
        noticefield1.setOnKeyPressed(e->{
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    update_Crud(1,noticefield1.getText());
                    noticefield1.setVisible(false);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        noticefield2.setOnKeyPressed(e->{
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    update_Crud(2,noticefield2.getText());
                    noticefield2.setVisible(false);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        noticefield3.setOnKeyPressed(e->{
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    update_Crud(3,noticefield3.getText());
                    noticefield3.setVisible(false);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        noticefield4.setOnKeyPressed(e->{
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    update_Crud(4,noticefield4.getText());
                    noticefield4.setVisible(false);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        notice1.setOnMouseClicked(e->{
            showFile(datalist.get(0).getFile());
        });
        notice2.setOnMouseClicked(e->{
            showFile(datalist.get(1).getFile());
        });
        notice3.setOnMouseClicked(e->{
            showFile(datalist.get(3).getFile());
        });
        notice4.setOnMouseClicked(e->{
            showFile(datalist.get(3).getFile());
        });
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
                //System.out.println(bod.getName());
                //System.out.println(bod.getDesignation());






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
    public ArrayList<Notice> detalist()
    {
        ArrayList<Notice> datalist = new ArrayList<>();

        String sql;
        sql ="SELECT * FROM notice";

        try {
            connect= DBUtils.connectDB(url1);
            prepare = connect.prepareStatement(sql);
            result=prepare.executeQuery();


            while(result.next())
            {
                StringBuilder resourcesPath=getrespath();
                resourcesPath.append(result.getString("file"));
                Notice notice =new Notice(result.getInt("Id"),result.getString("name"),result.getDate("udate"),resourcesPath.toString());
                datalist.add(notice);








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
                //System.out.println(president.getPhoto());
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
                //System.out.println(vpres.getPhoto());
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
        ArrayList<Notice> list=detalist();
        datalist.clear();
        datalist=list;
        if(list.get(0).getName()!=null){
            notice1.setText(list.get(0).getName());
            dateField1.setText(list.get(0).getDate().toString());
        }
        else{
            notice1.setText("Notice One");
            dateField1.setText("dateField1");
        }
        if(list.get(1).getName()!=null){
            notice2.setText(list.get(1).getName());
            dateField2.setText(list.get(1).getDate().toString());
        }
        else{
            notice2.setText("Notice Two");
            dateField2.setText("dateField2");
        }
        if(list.get(2).getName()!=null){
            notice3.setText(list.get(2).getName());
            dateField3.setText(list.get(2).getDate().toString());
        }
        else{
            notice3.setText("Notice Three");
            dateField3.setText("dateField3");
        }
        if(list.get(3).getName()!=null){
            notice4.setText(list.get(3).getName());
            dateField4.setText(list.get(3).getDate().toString());
        }
        else{
            notice4.setText("Notice Four");
            dateField4.setText("dateField4");
        }

    }
    @FXML
    void update_Crud(Integer a, String notice) throws IOException
    {
        String sql="";
        LocalDate date=LocalDate.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        FileChooser open = new FileChooser();
        Stage stage = (Stage) deptPane.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        if(file!=null) {
            StringBuilder im = copyFileToResources(file);
            for (int i = 0; i < im.length(); i++) {
                if (im.charAt(i) == '\\') {
                    im.setCharAt(i, '/');
                }
            }
            String file_path = im.toString();

            sql = "UPDATE notice SET `name` = '" + notice + "', `udate` = '" + java.sql.Date.valueOf(date) + "', `file` = '" + file_path + "' WHERE Id = '" + a + "'";
        }



        try
        {
            connect = DBUtils.connectDB(url1);
            if (file== null )
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            } else
            {
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("                                      Update Successfull!!!");
                alert.setHeaderText("       ");
                alert.setContentText("                             Successfully updated the data. ");
                alert.showAndWait();
                showData();

            }

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                connect.close();
                result.close();
                prepare.close();
                statement.close();

            } catch (Exception e)
            {

            }
        }
    }
    public StringBuilder copyFileToResources(File imageFile) throws IOException
    {
        // Determine the path to the resources folder
        StringBuilder resourcesPath = new StringBuilder(getClass().getResource("").getPath());
        //int n=resourcesPath.length();
        resourcesPath.deleteCharAt(0);
        for (int i = 0; i < resourcesPath.length(); i++)
        {
            if (resourcesPath.charAt(i) == '/')
            {

                resourcesPath.replace(i, i + 1, "\\\\");
            }
            if (resourcesPath.charAt(i) == '%')
            {
                resourcesPath.replace(i, i + 3, " ");
            }
            if(resourcesPath.charAt(i)=='t'&&resourcesPath.charAt(i+1)=='a'&&resourcesPath.charAt(i+2)=='r'&& resourcesPath.charAt(i+3)=='g'&& resourcesPath.charAt(i+4)=='e'&& resourcesPath.charAt(i+5)=='t'&& resourcesPath.charAt(i+6)=='/'){
                resourcesPath.delete(i-1,resourcesPath.length());
                break;
            }
        }
        //System.out.println(resourcesPath);

        String name = imageFile.getName();
        String extension = "";

        int dotIndex = name.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < name.length() - 1)
        {
            extension = name.substring(dotIndex + 1);
        }

        String fileName = "image_" + System.currentTimeMillis() + "." + extension;
        StringBuilder destination = new StringBuilder("\\src\\main\\resources\\application\\museum\\notices\\" + fileName);

        // Copy the image file to the resources folder with the unique file name
        Path sourcePath = imageFile.toPath();
        Path destinationPath = Paths.get(resourcesPath + "\\src\\main\\resources\\application\\museum\\notices\\" + fileName);
        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
//        direction=destinationPath.toString();
        return destination;
    }
    @FXML
    public void showFile(String path){
        File file = new File(path);
        if (file.exists()) {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Desktop not supported.");
            }
        } else {
            System.out.println("File not found.");
        }

    }
}