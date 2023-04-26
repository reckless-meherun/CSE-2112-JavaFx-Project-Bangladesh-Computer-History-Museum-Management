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

public class InventoryController {

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
    private Button departments;

    @FXML
    private Button home;

    @FXML
    private Button home1;

    @FXML
    private Button photogallery;

    @FXML
    private Text studentName;


    @FXML
    private Button tickets;

    static void pushTostack(){
        DBUtils.prevfxml.push("Inventory.fxml");
    }
    @FXML
    void SwitchToHome(ActionEvent event) {
        InventoryController.pushTostack();
        DBUtils.changeScene(event,"DashboardScene.fxml",DBUtils.username);
    }
    @FXML
    void switchToGallery(ActionEvent event) throws IOException {
        InventoryController.pushTostack();
        DBUtils.changeScene(event,"PhotoGalleryScene.fxml",false);
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
    void logout(ActionEvent event) {

    }

    @FXML
    void switchTOaboutUs(ActionEvent event) throws IOException {
        InventoryController.pushTostack();
        DBUtils.changeScene(event,"aboutus.fxml",false);
    }
    @FXML
    public void switchTODepartments(ActionEvent event) throws IOException
    {
        InventoryController.pushTostack();
        DBUtils.changeScene(event, "DepartmentsScene.fxml", false);
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
    void switchTotickets(ActionEvent event) throws IOException {
        InventoryController.pushTostack();
        DBUtils.changeScene(event,"Tickets.fxml",false);

    }

}
