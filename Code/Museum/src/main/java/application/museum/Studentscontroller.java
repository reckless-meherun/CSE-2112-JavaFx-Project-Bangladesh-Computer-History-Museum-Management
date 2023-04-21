package application.museum;

import application.museum.People.Gender;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Studentscontroller implements Initializable{

    @FXML
    private Button BOD;

    @FXML
    private Button GoBackButton;

    @FXML
    private TextField Id;

    @FXML
    private TextField Institutename;

    @FXML
    private Button LogoutButton;

    @FXML
    private Text ProfileIcon;

    @FXML
    private Button Clear;

    @FXML
    private AnchorPane SceneTwo;

    @FXML
    private AnchorPane SceneTwo1;

    @FXML
    private Button aboutus;

    @FXML
    private Button add;

    @FXML
    private Button admins;

    @FXML
    private TextField adress;

    @FXML
    private ImageView temp;

    @FXML
    private Button articles;

    @FXML
    private Button bar1;

    @FXML
    private Button bar2;

    @FXML
    private Button bar3;

    @FXML
    private Button bar4;

    @FXML
    private TextField course;

    @FXML
    private ComboBox<?> courseteacher;

    @FXML
    private Button curator;

    @FXML
    private Button delete;

    @FXML
    private Button departments;

    @FXML
    private Button developer;

    @FXML
    private DatePicker dob;

    @FXML
    private Button educator;

    @FXML
    private TextField email;

    @FXML
    private Button employee;

    @FXML
    private DatePicker finishingdate;

    @FXML
    private ComboBox<?> gender;

    @FXML
    private Button home;

    @FXML
    private Button image;

    @FXML
    private TextField name;

    @FXML
    private AnchorPane paneside;

    @FXML
    private TextField phonenumber;

    @FXML
    private Button photogallery;

    @FXML
    private AnchorPane scene2;

    @FXML
    private ImageView show;

    @FXML
    private DatePicker staringdate;

    @FXML
    private Button student;

    @FXML
    private Text studentName;

    @FXML
    private Button tickets;

    @FXML
    private Button update;

    private static File photo=null;

    private String url="jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\Aboutus.db";

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    @FXML
    void goBack(ActionEvent event) throws IOException {
        if(DBUtils.prevfxml.empty()){
            return;
        }
        String fxml=DBUtils.prevfxml.pop();
        //DBUtils.prevfxml.pop();
        System.out.println(fxml);
        if(fxml=="DashboardScene.fxml"){
            DBUtils.changeScene(event,fxml,DBUtils.username);
        }
        else {
            DBUtils.changeScene(event, fxml, true);
        }
    }

    @FXML
    void switchToHome(ActionEvent event) {
        DBUtils.prevfxml.push("studnets.fxml");
        DBUtils.changeScene(event,"DashboardScene.fxml",DBUtils.username);
    }

    @FXML
    void switchToSceneOne(ActionEvent event) throws IOException {
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
    void switchToemployee(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("students.fxml");
        DBUtils.changeScene(event,"employee.fxml",false);
    }
    @FXML
    void switchTobod(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("students.fxml");
        DBUtils.changeScene(event,"aboutus.fxml",false);
    }
    @FXML
    void switchTocurato(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("students.fxml");
        DBUtils.changeScene(event, "curator.fxml", false);
    }
    @FXML
    void switchTodevloper(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("students.fxml");
        DBUtils.changeScene(event,"developer.fxml",false);
    }

    @FXML
    void switchToGallery(ActionEvent event) throws IOException {
        Studentscontroller.pushtostack();
        DBUtils.changeScene(event,"PhotoGalleryScene.fxml",false);
    }
    @FXML
    void switchToInventory(ActionEvent event) throws IOException {
        Studentscontroller.pushtostack();
        DBUtils.changeScene(event,"Inventory.fxml",false);
    }

    static void pushtostack(){
        DBUtils.prevfxml.push("students.fxml");
    }
    @FXML
    public void switchTotickets(ActionEvent event) throws IOException
    {
        Studentscontroller.pushtostack();
        DBUtils.changeScene(event, "Tickets.fxml", false);
    }
    @FXML
    void switchToeducator(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("students.fxml");
        DBUtils.changeScene(event,"educator.fxml",false);
    }
    @FXML
    void switchToadmins(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("students.fxml");
        DBUtils.changeScene(event,"admins.fxml",false);
    }
    @FXML
    void run1(ActionEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneside);
        slide.setToX(0);
        slide.play();

        paneside.setTranslateX(-160);

        slide.setOnFinished((ActionEvent e)->{
            bar1.setVisible(false);
            bar2.setVisible(true);
        });

    }
    @FXML
    void insertImage(ActionEvent event) throws IOException {
        FileChooser open= new FileChooser();
        Stage stage=(Stage) scene2.getScene().getWindow();
        File file=open.showOpenDialog(stage);
        if(file!=null){
            StringBuilder im=new StringBuilder( file.toPath().toString());
            photo=file;
            for(int i=0;i<im.length();i++){
                if(im.charAt(i)=='\\'){
                    im.setCharAt(i,'/');
                }
            }
            String destination= im.toString();
            System.out.println(destination);
            Image img = new Image(destination);
            show.setImage(img);
            temp.setVisible(false);
        }
        else{
            System.out.println("Student pic is missing");
        }
    }
    public void Combo_box()
    {
        List<Gender> list=new ArrayList<>();
        for(Gender data:Gender.values())
        {
            list.add(data);
        }
        ObservableList data_list= FXCollections.observableArrayList(list);
        gender.setItems(data_list);

//        List<String> class_list=new ArrayList<>();
//        for(String data:Combo_subject)
//        {
//            class_list.add(data);
//        }
//        ObservableList data_list_class= FXCollections.observableArrayList(class_list);
//        crud_subject.setItems(data_list_class);
    }
    public StringBuilder copyImageToResources(File imageFile) throws IOException {
        // Determine the path to the resources folder
        StringBuilder resourcesPath = new StringBuilder(getClass().getResource("").getPath());
        //int n=resourcesPath.length();
        resourcesPath.deleteCharAt(0);
        for(int i=0;i<resourcesPath.length();i++){
            if(resourcesPath.charAt(i)=='/'){

                resourcesPath.replace(i,i+1,"\\\\");
            }
            if(resourcesPath.charAt(i)=='%'){
                resourcesPath.replace(i,i+3," ");
            }
            if(resourcesPath.charAt(i)=='m'){
                resourcesPath.delete(i+1,resourcesPath.length());
                break;
            }
        }
        System.out.println(resourcesPath);

        String fileName = "image_" + System.currentTimeMillis() + ".png";
        //StringBuilder destination= new StringBuilder("StudentsPhotos\\\\" + fileName);

        // Copy the image file to the resources folder with the unique file name
        Path sourcePath = imageFile.toPath();
        Path destinationPath = Paths.get(  resourcesPath +"\\src\\main\\resources\\application\\museum\\StudentsPhotos\\"+fileName);
        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
//        direction=destinationPath.toString();
        return new StringBuilder(destinationPath.toString());
    }
    @FXML
    public void clear()
    {
        Id.setText("");
        name.setText("");
        gender.getSelectionModel().clearSelection();
        show.setImage(null);
        temp.setVisible(true);
        Institutename.setText("");
        email.setText("");
        phonenumber.setText("");
        adress.setText("");
        course.setText("");
        courseteacher.getSelectionModel().clearSelection();
        staringdate.setValue(null);
        finishingdate.setValue(null);
        dob.setValue(null);
        photo=null;

    }
    @FXML
    void insert(ActionEvent event) {
        String sql="INSERT INTO Students VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";


        try {
            connect= DBUtils.connectDB(url);
            if(Id.getText().isEmpty() | name.getText().isEmpty()  | show.getImage()==null | course.getText().isEmpty() |
                    Institutename.getText().isEmpty() | dob.getValue()==null | staringdate.getValue()==null )
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            }
            else
            {
                StringBuilder im= copyImageToResources(photo);
                for(int i=0;i<im.length();i++){
                    if(im.charAt(i)=='\\'){
                        im.setCharAt(i,'/');
                    }
                }
                String file_path= im.toString();


                prepare=connect.prepareStatement(sql);
                prepare.setString(1,Id.getText());
                prepare.setString(2,name.getText());
                prepare.setString(3,Institutename.getText());
                prepare.setString(4,email.getText());
                prepare.setString(5,phonenumber.getText());
                prepare.setString(6,adress.getText());
                prepare.setString(7,course.getText());
                prepare.setString(8, (String) courseteacher.getSelectionModel().getSelectedItem());
                prepare.setDate(9,(Date.valueOf(staringdate.getValue())));
                prepare.setDate(10,(Date.valueOf(finishingdate.getValue())));
                prepare.setDate(11,(Date.valueOf(dob.getValue())) );
                prepare.setString(13, (String) gender.getSelectionModel().getSelectedItem());
                prepare.setString(12,file_path);

                prepare.execute();
                System.out.println("ok12");
                clear();
            }


        }catch (Exception e) {
            System.out.println(e);
        }
        finally
        {
            try
            {
                connect.close();
                result.close();
                prepare.close();
                statement.close();

            }catch (Exception e)
            {

            }
        }

    }



    @FXML
    void run2(ActionEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneside);
        slide.setToX(-160);
        slide.play();

        paneside.setTranslateX(0);

        slide.setOnFinished((ActionEvent e)->{
            bar1.setVisible(true);
            bar2.setVisible(false);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Combo_box();
        paneside.setTranslateX(0);
        bar2.setVisible(true);
        bar1.setVisible(false);
        bar3.setVisible(true);
        bar4.setVisible(false);
        scene2.setTranslateX(378);
    }
    @FXML
    void run3(ActionEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.6));
        slide.setNode(scene2);
        slide.setToX(0);
        slide.play();

        scene2.setTranslateX(378);

        slide.setOnFinished((ActionEvent e)->{
            bar3.setVisible(false);
            bar4.setVisible(true);
        });
    }

    @FXML
    void run4(ActionEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.6));
        slide.setNode(scene2);
        slide.setToX(378);
        slide.play();

        scene2.setTranslateX(0);

        slide.setOnFinished((ActionEvent e)->{
            bar4.setVisible(false);
            bar3.setVisible(true);
        });
    }

}
