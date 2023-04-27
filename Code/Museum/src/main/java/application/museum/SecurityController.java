package application.museum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SecurityController implements Initializable {
    @FXML
    public TreeView<String> treeView;
    @FXML
    private Button GoBackButton;
    @FXML
    private Button LogOutButton;
    @FXML
    private Button aboutUs;
    @FXML
    private Button articles;
    @FXML
    private Button departments;
    @FXML
    private Button home;
    @FXML
    private Button home1;
    @FXML
    private Button photoGallery;
    @FXML
    private Button tickets;

    private static String name_here = "SecurityScene.fxml";

    static void pushToStack()
    {
        DBUtils.prevfxml.push(name_here);
    }

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            NavigationHandler.HandleNavigation(name_here, home, treeView, photoGallery, articles, aboutUs, tickets, LogOutButton, GoBackButton);
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    void setGoBackButton(ActionEvent event) throws IOException
    {
        if (DBUtils.prevfxml.empty())
        {
            return;
        }
        String fxml = DBUtils.prevfxml.pop();
        System.out.println(fxml);

        if (fxml == name_here)
        {
            DBUtils.changeScene(event, fxml, DBUtils.username);
        }
        else
        {
            DBUtils.changeScene(event, fxml, true);
        }
    }

    @FXML
    void LogOut(ActionEvent event)
    {

    }

    @FXML
    void switchToSceneOne (ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to log out.");
        alert.setContentText("Do you want to save?");

        if (alert.showAndWait().get() == ButtonType.OK)
        {
            DBUtils.prevfxml.clear();
            Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1001, 680));
            stage.show();

        }
    }
}
