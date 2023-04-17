package application.museum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class Aboutus {

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
    private Button photogallery;

    @FXML
    private Text studentName;

    @FXML
    private Button tickets;

    @FXML
    void switchToHome(ActionEvent event) {
        DBUtils.prevfxml.push("aboutus.fxml");
        DBUtils.changeScene(event,"DashboardScene.fxml",DBUtils.username);

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

}
