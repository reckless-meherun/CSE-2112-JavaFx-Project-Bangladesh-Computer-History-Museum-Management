package application.museum;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Curatorcontroller implements Initializable {
    @FXML
    private Button BOD;

    @FXML
    private TextField Department;

    @FXML
    private TextField Field;

    @FXML
    private Button GoBackButton;

    @FXML
    private TextField Id;

    @FXML
    private Button LogoutButton;

    @FXML
    private Text ProfileIcon;

    @FXML
    private AnchorPane SceneTwo;

    @FXML
    private Button aboutus;

    @FXML
    private Button add;

    @FXML
    private Button admins;

    @FXML
    private TextField adress;

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
    private Button curator;

    @FXML
    private Button delete;

    @FXML
    private Button departments;

    @FXML
    private TextField designation;

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
    private ComboBox<?> gender;

    @FXML
    private Button home;

    @FXML
    private Button image;

    @FXML
    private DatePicker joingdate;

    @FXML
    private DatePicker joingdate1;

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
    private Button student;

    @FXML
    private Text studentName;

    @FXML
    private Button tickets;

    @FXML
    private Button update;


    public static void pushtostack(){
        DBUtils.prevfxml.push("curator.fxml");
    }

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
        DBUtils.prevfxml.push("curator.fxml");
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
    void switchTobod(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("curator.fxml");
        DBUtils.changeScene(event,"aboutus.fxml",false);
    }

    @FXML
    void switchToemployee(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("curator.fxml");
        DBUtils.changeScene(event,"employee.fxml",false);
    }
    @FXML
    void switchTostudents(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("curator.fxml");
        DBUtils.changeScene(event,"students.fxml",false);
    }
    @FXML
    void switchTodevloper(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("curator.fxml");
        DBUtils.changeScene(event,"developer.fxml",false);
    }
    @FXML
    void switchToadmins(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("curator.fxml");
        DBUtils.changeScene(event,"admins.fxml",false);
    }
    @FXML
    void switchToeducator(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("curator.fxml");
        DBUtils.changeScene(event,"educator.fxml",false);
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
    @FXML
    void switchToInventory(ActionEvent event) throws IOException {
        Curatorcontroller.pushtostack();
        DBUtils.changeScene(event,"Inventory.fxml",false);
    }

    @FXML
    void switchToGallery(ActionEvent event) throws IOException {
        Curatorcontroller.pushtostack();
        DBUtils.changeScene(event,"PhotoGalleryScene.fxml",false);
    }

    @FXML
    public void switchTotickets(ActionEvent event) throws IOException
    {
        Curatorcontroller.pushtostack();
        DBUtils.changeScene(event, "Tickets.fxml", false);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paneside.setTranslateX(0);
        bar1.setVisible(false);
        bar2.setVisible(true);
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
