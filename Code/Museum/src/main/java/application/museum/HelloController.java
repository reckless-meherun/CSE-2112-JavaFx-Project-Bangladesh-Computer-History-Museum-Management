package application.museum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController
{
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button GetStartedButton;
    public void switchToLoginScene(ActionEvent event) throws IOException
    {
        //Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
        root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,1101,680);
        stage.setScene(scene);
        stage.show();
    }
}