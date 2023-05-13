package application.museum;

import application.museum.Departments.Curatorial_dept;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class Curatorial_deptController implements Initializable
{
    @FXML
    public TreeView<String> treeView;
    @FXML
    private Button GoBackButton;
    @FXML
    private Button LogoutButton;
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
    private Button tickets;
    @FXML
    private TextField searchBar;
    @FXML
    private Button uploadMapButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private ComboBox nameField;
    @FXML
    private TextField levelField;
    @FXML
    private TextField galleryNoField;
    @FXML
    private ComboBox guideField;
    @FXML
    private ComboBox cleanerField;
    @FXML
    private ComboBox envConField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TableView<Curatorial_dept> curatorialDeptable;
    @FXML
    private TableColumn<Curatorial_dept, Date> dateCol;
    @FXML
    private TableColumn<Curatorial_dept, String> departmentCol;
    @FXML
    private TableColumn<Curatorial_dept, Integer> roomCol;
    @FXML
    private TableColumn<Curatorial_dept, Integer> levelCol;
    @FXML
    private TableColumn<Curatorial_dept, String> guideCol;
    @FXML
    private TableColumn<Curatorial_dept, String> cleanerCol;
    @FXML
    private TableColumn<Curatorial_dept, String> envConCol;

   // private String url = "jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\Departments.db";

    static void pushToStack()
    {
        DBUtils.prevfxml.push("CuratorialDeptScene.fxml");
    }

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            NavigationHandler.HandleNavigation("CuratorialDeptScene.fxml", home, treeView, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
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
        System.out.println(fxml);
        if (fxml == "DashboardScene.fxml")
        {
            DBUtils.changeScene(event, fxml, DBUtils.username);
        }
        else
        {
            DBUtils.changeScene(event, fxml, true);
        }
    }

    @FXML
    void logout(ActionEvent event)
    {

    }

    @FXML
    void switchToSceneOne(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout.");
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
}