package application.museum;

import application.museum.People.Employee;
import application.museum.People.Gender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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



    @FXML
    private Button LogoutButton;

    @FXML
    private Text ProfileIcon;

    @FXML
    private AnchorPane SceneTwo;

    @FXML
    private Button aboutus;


    @FXML
    private Button home11;

    @FXML
    private Button home12;

    @FXML
    private Button photogallery;

    @FXML
    private Text studentName;

    @FXML
    private ComboBox<String> watcher;

    private String url = "jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\employee.db";


    private static String name_here = "SecurityScene.fxml";

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    static void pushToStack()
    {
        DBUtils.prevfxml.push(name_here);
    }

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            NavigationHandler.HandleNavigation(name_here, home, treeView, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
        comboBox();
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
    ArrayList<Employee> wacherlist(){
        ArrayList<Employee> list=new ArrayList<>();
        String sql;
        sql = "SELECT * FROM Employee";

        try
        {
            connect = DBUtils.connectDB(url);
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next())
            {
                StringBuilder resourcesPath = getrespath();
                System.out.println(resourcesPath);
                resourcesPath.append(result.getString("img"));

                Gender gm;
                if (result.getString("Gender").equals("MALE"))
                {
                    gm = Gender.MALE;
                } else if (result.getString("Gender").equals("FEMALE"))
                {
                    gm = Gender.FEMALE;
                } else gm = Gender.OTHER;
                Employee emp;

                if (result.getDate("resign") != null)
                {

                    emp = new Employee(result.getString("Name"), gm, result.getString("phoneNo"), resourcesPath.toString(),
                            result.getString("email"), result.getDate("dob"), result.getString("adress"),
                            result.getInt("ID"), result.getString("Department"), result.getString("designation"),
                            result.getString("worktime"), result.getDate("jdate"), result.getDate("resign"));
                } else
                {
                    emp = new Employee(result.getString("Name"), gm, result.getString("phoneNo"), resourcesPath.toString(),
                            result.getString("email"), result.getDate("dob"), result.getString("adress"),
                            result.getInt("ID"), result.getString("Department"), result.getString("designation"),
                            result.getString("worktime"), result.getDate("jdate"));
                }

                list.add(emp);
            }

        } catch (Exception e)
        {
            System.out.println("username database error");
        } finally
        {
            try
            {
                connect.close();
                result.close();
                prepare.close();
                if (statement != null)
                {
                    statement.close();
                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return list;
    }
    public void comboBox(){
        ArrayList<Employee> e=wacherlist();
        ArrayList<String> list=new ArrayList<>();
        for(Employee emp:e){
            if(emp.getDepartment().equals("Security")){
                list.add(emp.getName());
            }
        }
        ObservableList data_list = FXCollections.observableArrayList(list);
        watcher.setItems(data_list);
    }

    StringBuilder getrespath()
    {
        StringBuilder resourcesPath = new StringBuilder(getClass().getResource("").getPath());
        //int n=resourcesPath.length();
        resourcesPath.deleteCharAt(0);
        System.out.println(resourcesPath);
        for (int i = 0; i < resourcesPath.length(); i++)
        {
            if (resourcesPath.charAt(i) == '%')
            {
                resourcesPath.replace(i, i + 3, " ");
            }
            if(resourcesPath.charAt(i)=='t'&&resourcesPath.charAt(i+1)=='a'&&resourcesPath.charAt(i+2)=='r'&& resourcesPath.charAt(i+3)=='g'&& resourcesPath.charAt(i+4)=='e'&& resourcesPath.charAt(i+5)=='t'&& resourcesPath.charAt(i+6)=='/'){
                resourcesPath.delete(i-1,resourcesPath.length());
                break;
            }
        }
        return resourcesPath;
    }

}
