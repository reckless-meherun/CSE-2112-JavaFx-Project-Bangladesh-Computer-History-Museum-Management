package application.museum;

import application.museum.People.Visitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Gallerycontroller implements Initializable
{
    @FXML
    public TreeView<String> treeView;
    @FXML
    private Button GoBackButton;

    @FXML
    private Button LogoutButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button browseImageButton;

    @FXML
    private ImageView imageView;
    @FXML
    private TextField searchBar;
    @FXML
    private TextField titleField;
    @FXML
    private TextField catalogNoField;
    @FXML
    private TextField docNoField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextField rowField;
    @FXML
    private TextField roomField;
    @FXML
    private ComboBox<String> departmentField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TableView<Visitor> photoGalleryTable;
    @FXML
    private TableColumn<Visitor, Integer> docNoCol;
    @FXML
    private TableColumn<Visitor, String> dateCol;
    @FXML
    private TableColumn<Visitor, String> catalogNoCol;
    @FXML
    private TableColumn<Visitor, String> titleCol;
    @FXML
    private TableColumn<Visitor, String> descriptionCol;
    @FXML
    private TableColumn<Visitor, String> departmentCol;
    @FXML
    private TableColumn<Visitor, Integer> positionCol;
    @FXML
    private TableColumn<Visitor, Integer> roomCol;

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

    static void pushtostack()
    {
        DBUtils.prevfxml.push("PhotoGalleryScene.fxml");

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
    void switchToSceneOne(ActionEvent event) throws IOException
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
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            NavigationHandler.HandleNavigation("PhotoGalleryScene.fxml", home, treeView, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
