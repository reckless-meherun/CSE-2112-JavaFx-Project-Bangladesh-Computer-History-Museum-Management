package application.museum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Curatorcontroller {

    @FXML
    private Button BOD;

    @FXML
    private Button admins;

    @FXML
    private Button GoBackButton;

    @FXML
    private Button LogoutButton;

    @FXML
    private Text ProfileIcon;

    @FXML
    private AnchorPane SceneTwo;

    @FXML
    private Button aboutus;

    @FXML
    private Button articles;

    @FXML
    private Button curator;

    @FXML
    private Button departments;

    @FXML
    private Button developer;

    @FXML
    private Button educator;

    @FXML
    private Button employee;

    @FXML
    private Button home;

    @FXML
    private Button photogallery;

    @FXML
    private Button student;

    @FXML
    private Text studentName;

    @FXML
    private Button tickets;

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

}
