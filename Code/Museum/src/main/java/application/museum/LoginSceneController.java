package application.museum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginSceneController
{
    @FXML
    TextField username;

    @FXML

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent event) throws IOException
    {
        String nameTextField = username.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardScene.fxml"));
        root = loader.load();
        DashboardSceneController scene2Controller = loader.getController();
        scene2Controller.displayName(nameTextField);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
