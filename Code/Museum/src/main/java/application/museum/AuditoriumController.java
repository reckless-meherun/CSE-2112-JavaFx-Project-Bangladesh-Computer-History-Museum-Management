package application.museum;

import application.museum.Departments.Auditorium;
import application.museum.People.course;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.ResourceBundle;

public class AuditoriumController implements Initializable {
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
    private Text ProfileIcon;
    @FXML
    private AnchorPane SceneTwo;
    //
    //
    //
    //
    @FXML
    private DatePicker inputDate;
    @FXML
    private Auditorium auditorium;
    @FXML
    private TableColumn<Auditorium, Date> auditoriumDateTableColumn;
    @FXML
    private TableColumn<Auditorium, String> auditoriumStringTableColumn;

    @FXML
    private Button home1;
    @FXML
    private Button photoGallery;
    @FXML
    private Button tickets;
    @FXML
    private TextField textField;
    private Connection connect;
    private PreparedStatement prepare;
    private Text cname;
//    private ComboBox<String>

    private static String name_here = "AuditoriumScene.fxml";
    private String url="jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\database.db";
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

    @FXML
    void insert(ActionEvent event)
    {
        String sql = "INSERT INTO ?????????/ VALUES (?,?,?,?,?,?)";

        try{
            connect = DBUtils.connectDB(url);
            if (cname.getText().isEmpty() | inputDate.getValue() == null)
//             eikhane sob kichu kora hoy nai....
//             if er vitor aaro kisu jinis add korte hbe
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            }
            else {
                prepare = connect.prepareStatement(sql);

                if(!textField.getText().isEmpty())
                {
                    prepare.setInt(1, Integer.parseInt(textField.getText()));
                }
                else {
                    prepare.setInt(1, 0);
                }
                prepare.setString(2, cname.getText());

                prepare.setDate(3, java.sql.Date.valueOf(inputDate.getValue()));


            }
//
//
//
//
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        finally {
            try {
                connect.close();
            }
            catch (Exception ee){
                System.out.println(ee);
            }
        }
    }


}
