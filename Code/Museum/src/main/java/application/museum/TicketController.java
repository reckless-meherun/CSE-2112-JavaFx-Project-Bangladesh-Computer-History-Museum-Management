package application.museum;

import application.museum.People.Employee;
import application.museum.People.Visitor;
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

public class TicketController implements Initializable
{
    @FXML
    public TreeView<String> treeView;
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
    @FXML
    private Button bookButton;
    @FXML
    private Button printTicketButton;
    @FXML
    private Button printMapButton;
    @FXML
    private Button resetButton;

    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField occupationField;
    @FXML
    private TextField prevVisitField;
    @FXML
    private Text priceWithoutDiscount;
    @FXML
    private Text discount;
    @FXML
    private Text finalPrice;
    @FXML
    private TextField searchBar;
    @FXML
    private ComboBox<String> genderField;
    @FXML
    private ComboBox<String> ticketTypeField;
    @FXML
    private ComboBox<String> languageField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TableView<Visitor> visitorTable;
    @FXML
    private TableColumn<Visitor, String> dateCol;
    @FXML
    private TableColumn<Visitor, String> nameCol;
    @FXML
    private TableColumn<Visitor, Integer> ageCol;
    @FXML
    private TableColumn<Visitor, String> genderCol;
    @FXML
    private TableColumn<Visitor, String> emailCol;
    @FXML
    private TableColumn<Visitor, Integer> phoneCol;
    @FXML
    private TableColumn<Visitor, Integer> totalVisitCol;
    @FXML
    private TableColumn<Visitor, String> languageCol;


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
    void logout(ActionEvent event)
    {

    }

    @FXML
    public void switchTODepartments(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("Tickets.fxml");
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
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            NavigationHandler.HandleNavigation("Tickets.fxml", home, treeView, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}

