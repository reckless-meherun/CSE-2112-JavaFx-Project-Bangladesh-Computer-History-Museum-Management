package application.museum;

import application.museum.Departments.Security;
import application.museum.People.Employee;
import application.museum.People.Gender;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class SecurityController implements Initializable
{
    private static String name_here = "SecurityScene.fxml";
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
    private Button add;
    @FXML
    private Button photoGallery;
    @FXML
    private Button tickets;
    @FXML
    private TableColumn<Security, Date> tlastup;
    @FXML
    private TableColumn<Security, Integer> tlevel;
    @FXML
    private TableColumn<Security, String> troom;
    @FXML
    private TableColumn<Security, Integer> trow;
    @FXML
    private TableColumn<Security, String> tsecroom;
    @FXML
    private TableColumn<Security, Integer> ttcam;
    @FXML
    private TableColumn<Security, String> twacher;
    @FXML
    private TableColumn<Security, String> tcamid;
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
    @FXML
    private Button clear;
    @FXML
    private Button delete;
    @FXML
    private TextField scamid;
    @FXML
    private DatePicker sdate;
    @FXML
    private TextField slevel;
    @FXML
    private TextField sroom;
    @FXML
    private TextField srow;
    @FXML
    private TextField ssroom;
    @FXML
    private Button update;
    @FXML
    private TableView<Security> table_view;
    //employee.db
    private String url = "jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\employee.db";
    //departments.db
    private String url1 = "jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\Security.db";
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
        } catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
        comboBox();
        showData();
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
        } else
        {
            DBUtils.changeScene(event, fxml, true);
        }
    }

    @FXML
    void LogOut(ActionEvent event)
    {

    }

    @FXML
    void switchToSceneOne(ActionEvent event) throws IOException
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

    ArrayList<Employee> wacherlist()
    {
        ArrayList<Employee> list = new ArrayList<>();
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

    public void comboBox()
    {
        ArrayList<Employee> e = wacherlist();
        ArrayList<String> list = new ArrayList<>();
        for (Employee emp : e)
        {
            if (emp.getDepartment().equals("Security"))
            {
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
            if (resourcesPath.charAt(i) == 't' && resourcesPath.charAt(i + 1) == 'a' && resourcesPath.charAt(i + 2) == 'r' && resourcesPath.charAt(i + 3) == 'g' && resourcesPath.charAt(i + 4) == 'e' && resourcesPath.charAt(i + 5) == 't' && resourcesPath.charAt(i + 6) == '/')
            {
                resourcesPath.delete(i - 1, resourcesPath.length());
                break;
            }
        }
        return resourcesPath;
    }

    @FXML
    public void clear()
    {
        scamid.setText("");
        ssroom.setText("");
        watcher.getSelectionModel().clearSelection();
        sroom.setText("");
        slevel.setText("");
        srow.setText("");
        sdate.setValue(null);

    }

    @FXML
    void insert(ActionEvent event)
    {
        String sql = "INSERT INTO security VALUES (?,?,?,?,?,?,?,?)";


        try
        {
            connect = DBUtils.connectDB(url1);
            if (scamid.getText().isEmpty() | ssroom.getText().isEmpty() | srow.getText().isEmpty() |
                    watcher.getSelectionModel().isEmpty() | sroom.getText().isEmpty() |
                    sdate.getValue() == null | slevel.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            } else
            {


                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, Integer.parseInt(srow.getText()));
                prepare.setString(2, ssroom.getText());
                prepare.setString(7, watcher.getSelectionModel().getSelectedItem());
                prepare.setString(3, scamid.getText());
                prepare.setString(4, sroom.getText());
                prepare.setInt(5, Integer.parseInt(slevel.getText()));
                prepare.setDate(8, (java.sql.Date.valueOf(sdate.getValue())));
                prepare.execute();
                showData();
                System.out.println("ok12");
                clear();
            }

        } catch (Exception e)
        {
            System.out.println(e);
        } finally
        {
            try
            {
                connect.close();
                result.close();
                prepare.close();
                statement.close();

            } catch (Exception e)
            {

            }
        }
    }

    public ObservableList<Security> datalist()
    {
        ObservableList<Security> datalist = FXCollections.observableArrayList();

        String sql;
        sql = "SELECT * FROM security";

        try
        {
            connect = DBUtils.connectDB(url1);
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next())
            {
                String projects = result.getString("camid");
                String s1[] = projects.split("\\s+");
                Security sec = new Security(s1, result.getString("wacher"), result.getInt("row"), result.getString("sroom"), result.getInt("level"), result.getString("room"), result.getDate("lup"));
                datalist.add(sec);
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
        return datalist;
    }

    public void showData()
    {
        ObservableList<Security> showlist = datalist();
        tlastup.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcamid.setCellValueFactory(cellData ->
        {
            ObservableList<String> specializationList = FXCollections.observableArrayList(cellData.getValue().getCamera());
            return Bindings.createStringBinding(() -> String.join(", ", specializationList));
        });
        tsecroom.setCellValueFactory(new PropertyValueFactory<>("DeptName"));
        trow.setCellValueFactory(new PropertyValueFactory<>("row"));
        troom.setCellValueFactory(new PropertyValueFactory<>("DeptRoom"));
        tlevel.setCellValueFactory(new PropertyValueFactory<>("DeptLevel"));
        ttcam.setCellValueFactory(new PropertyValueFactory<>("total_camera"));
        twacher.setCellValueFactory(new PropertyValueFactory<>("watcher"));


        table_view.setItems(showlist);
    }

    @FXML
    void selectData(MouseEvent event)
    {
        clear();
        Security security = table_view.getSelectionModel().getSelectedItem();
        int no = table_view.getSelectionModel().getSelectedIndex();
        if ((no - 1) < -1)
        {
            return;
        }
        String st = "";
        for (String s : security.getCamera())
        {
            st += s + " ";
        }
        scamid.setText(st);
        ssroom.setText(valueOf(security.getDeptName()));
        watcher.setValue(valueOf(security.getWatcher()));
        sroom.setText(valueOf(security.getDeptRoom()));
        srow.setText(valueOf(security.getRow()));
        slevel.setText(valueOf(security.getDeptLevel()));

        sdate.setValue(LocalDate.parse(valueOf(security.getDate())));

    }

    @FXML
    void delete(ActionEvent event)
    {
        String sql = "DELETE from security WHERE `row` ='" + srow.getText() + "'";

        try
        {
            connect = DBUtils.connectDB(url1);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("                                     Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("                     Are you sure you want to delete? ");

            Optional<ButtonType> buttontype = alert.showAndWait();
            if (buttontype.get() == ButtonType.OK)
            {
                statement = connect.createStatement();
                statement.executeUpdate(sql);
            }
            showData();
            clear();

        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                connect.close();
                result.close();
                prepare.close();
                statement.close();

            } catch (Exception e)
            {

            }
        }
    }

    @FXML
    void update_Crud(ActionEvent event) throws IOException
    {
        String sql = "UPDATE security SET `camid` = '" + scamid.getText() + "', `lup` = '" + java.sql.Date.valueOf(sdate.getValue()) + "', `wacher` = '" + watcher.getSelectionModel().getSelectedItem() + "' WHERE row = '" + srow.getText() + "'";

        try
        {
            connect = DBUtils.connectDB(url1);
            if (scamid.getText().isEmpty() | ssroom.getText().isEmpty() | srow.getText().isEmpty() |
                    watcher.getSelectionModel().isEmpty() | sroom.getText().isEmpty() |
                    sdate.getValue() == null | slevel.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            } else
            {
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("                                      Update Successfull!!!");
                alert.setHeaderText("       ");
                alert.setContentText("                             Successfully updated the data. ");
                alert.showAndWait();
                showData();
                clear();
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                connect.close();
                result.close();
                prepare.close();
                statement.close();

            } catch (Exception e)
            {

            }
        }
    }

}
