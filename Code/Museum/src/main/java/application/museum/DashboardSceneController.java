package application.museum;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardSceneController implements Initializable
{
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
    private Text notice1;
    @FXML
    private Text notice2;
    @FXML
    private Text notice3;
    @FXML
    private Text notice4;
    @FXML
    private Text ProfileIcon;


    @FXML
    private AnchorPane SceneTwo;

    public void displayName(String username)
    {
        loggedIn.setText("Hello " + username + " !");
    }

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

    public void switchToSceneOne(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1101, 680);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        NavigationHandler.HandleNavigation(home, departments, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);
    }
}