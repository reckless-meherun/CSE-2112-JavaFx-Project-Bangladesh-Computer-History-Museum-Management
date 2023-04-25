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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Aboutus implements Initializable
{
    @FXML
    TreeView<String> treeView;
    @FXML
    private Button BOD;

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
    private Text heading;

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
    private ComboBox<?> post;

    @FXML
    private AnchorPane scene2;

    @FXML
    private ImageView showimage;

    @FXML
    private Button student;

    @FXML
    private Text studentName;

    @FXML
    private Button tickets;

    @FXML
    private Button update;

    public static void pushtostack()
    {
        DBUtils.prevfxml.push("aboutus.fxml");
    }

    @FXML
    void switchToemployee(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("aboutus.fxml");
        DBUtils.changeScene(event, "employee.fxml", false);
    }

    @FXML
    void switchTocurator(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("aboutus.fxml");
        DBUtils.changeScene(event, "curator.fxml", false);
    }

    @FXML
    void switchTostudents(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("aboutus.fxml");
        DBUtils.changeScene(event, "students.fxml", false);
    }

    @FXML
    void switchTodevloper(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("aboutus.fxml");
        DBUtils.changeScene(event, "developer.fxml", false);
    }

    @FXML
    void switchToadmins(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("aboutus.fxml");
        DBUtils.changeScene(event, "admins.fxml", false);
    }

    @FXML
    void switchToeducator(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("aboutus.fxml");
        DBUtils.changeScene(event, "educator.fxml", false);
    }

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
    void goBack(ActionEvent event) throws IOException
    {
        if (DBUtils.prevfxml.empty())
        {
            return;
        }
        String fxml = DBUtils.prevfxml.pop();
        //DBUtils.prevfxml.pop();
        System.out.println(fxml);
        if (fxml == "DashboardScene.fxml")
        {
            DBUtils.changeScene(event, fxml, DBUtils.username);
        } else
        {
            DBUtils.changeScene(event, fxml, true);
        }
    }

    @FXML
    void run1(ActionEvent event)
    {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneside);
        slide.setToX(0);
        slide.play();

        paneside.setTranslateX(-160);

        slide.setOnFinished((ActionEvent e) ->
        {
            bar1.setVisible(false);
            bar2.setVisible(true);
        });

    }


    @FXML
    void run2(ActionEvent event)
    {

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneside);
        slide.setToX(-160);
        slide.play();

        paneside.setTranslateX(0);

        slide.setOnFinished((ActionEvent e) ->
        {
            bar1.setVisible(true);
            bar2.setVisible(false);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            NavigationHandler.HandleNavigation("aboutus.fxml", home, treeView, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        paneside.setTranslateX(0);
        bar2.setVisible(true);
        bar1.setVisible(false);
        bar3.setVisible(true);
        bar4.setVisible(false);
        scene2.setTranslateX(378);

    }


    @FXML
    void run3(ActionEvent event)
    {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.6));
        slide.setNode(scene2);
        slide.setToX(0);
        slide.play();

        scene2.setTranslateX(378);

        slide.setOnFinished((ActionEvent e) ->
        {
            bar3.setVisible(false);
            bar4.setVisible(true);
        });
    }

    @FXML
    void run4(ActionEvent event)
    {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.6));
        slide.setNode(scene2);
        slide.setToX(378);
        slide.play();

        scene2.setTranslateX(0);

        slide.setOnFinished((ActionEvent e) ->
        {
            bar4.setVisible(false);
            bar3.setVisible(true);
        });
    }
}
