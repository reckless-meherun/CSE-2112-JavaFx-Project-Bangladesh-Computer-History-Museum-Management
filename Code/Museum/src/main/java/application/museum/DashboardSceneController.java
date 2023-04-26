package application.museum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
        //DBUtils.prevfxml.pop();
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
    }
}