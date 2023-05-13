package application.museum;

import application.museum.Departments.Curatorial_dept;
import application.museum.Departments.Security;
import application.museum.People.Employee;
import application.museum.People.Gender;
import application.museum.People.Visitor;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;


import static java.lang.String.valueOf;

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
    private ComboBox<String> nameField;

    @FXML
    private TextField levelField;
    @FXML
    private TextField galleryNoField;
    @FXML
    private ComboBox<String> guideField;
    @FXML
    private ComboBox<String> cleanerField;
    @FXML
    private ComboBox<String> envConField;
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

    private String url1 = "jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\Departments.db";
    private String url = "jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\employee.db";
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    static void pushToStack()
    {
        DBUtils.prevfxml.push("CuratorialDeptScene.fxml");
    }

    public void comboBox()
    {
        List<String> deptList = new ArrayList<>();
        for (String data : DBUtils.dept)
        {
            deptList.add(data);
        }
        ObservableList DEPTLIST = FXCollections.observableArrayList(deptList);
        nameField.setItems(DEPTLIST);

        /** guide combo box */
        ArrayList<Employee> guideListEm = employeeList();
        ArrayList<String> guideListStr = new ArrayList<>();
        String designation = "Guide";
        for (Employee emp : guideListEm)
        {
            if (emp.getDesignation().equals(designation.toLowerCase()) | emp.getDesignation().equals(designation.toUpperCase()))
            {
                guideListStr.add(emp.getName());
            }
        }
        ObservableList data_list = FXCollections.observableArrayList(guideListStr);
        guideField.setItems(data_list);

        /** cleaner combo box*/
        ArrayList<Employee> cleanerListEm = employeeList();
        ArrayList<String> cleanerListStr = new ArrayList<>();
        designation = "Cleaner";
        for (Employee emp : cleanerListEm)
        {
            if (emp.getDesignation().equals(designation.toLowerCase()) | emp.getDesignation().equals(designation.toUpperCase()))
            {
                cleanerListStr.add(emp.getName());
            }
        }
        ObservableList data2_list = FXCollections.observableArrayList(cleanerListStr);
        cleanerField.setItems(data2_list);

        /** env controller combo box*/
        ArrayList<Employee> envListEm = employeeList();
        ArrayList<String> envListStr = new ArrayList<>();
        designation = "Environment Controller";
        for (Employee emp : envListEm)
        {
            if (emp.getDesignation().equals(designation.toLowerCase()) | emp.getDesignation().equals(designation.toUpperCase()))
            {
                envListStr.add(emp.getName());
            }
        }
        ObservableList data3_list = FXCollections.observableArrayList(envListStr);
        guideField.setItems(data3_list);

    }

    ArrayList<Employee> employeeList()
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

    public ObservableList<Curatorial_dept> datalist()
    {
        ObservableList<Curatorial_dept> datalist = FXCollections.observableArrayList();

        String sql;
        sql = "SELECT * FROM curatorial_dept";

        try
        {
            connect = DBUtils.connectDB(url1);
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next())
            {
                Curatorial_dept curDept = new Curatorial_dept(result.getDate("Date"), result.getString("Name"), result.getString("GuideName"), result.getString("CleanerName"), result.getString("EnvironmentController"), result.getInt("room"), result.getInt("GalleryLevel"));
                datalist.add(curDept);
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

    @FXML
    void insert(ActionEvent event)
    {
        String sql = "INSERT INTO curatorial_dept VALUES (?,?,?,?,?,?,?)";

        try
        {
            connect = DBUtils.connectDB(url1);
            if (galleryNoField.getText().isEmpty() | levelField.getText().isEmpty() | nameField.getSelectionModel().isEmpty() |
                    guideField.getSelectionModel().isEmpty() | cleanerField.getSelectionModel().isEmpty() | envConField.getSelectionModel().isEmpty() |
                    dateField.getValue() == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            } else
            {

                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, Integer.parseInt(galleryNoField.getText()));
                prepare.setString(2, (String) nameField.getSelectionModel().getSelectedItem());
                prepare.setString(3, levelField.getText());
                prepare.setString(4, (String) guideField.getSelectionModel().getSelectedItem());
                prepare.setString(5, (String) cleanerField.getSelectionModel().getSelectedItem());
                prepare.setString(6, (String) envConField.getSelectionModel().getSelectedItem());
                prepare.setDate(7, (java.sql.Date.valueOf(dateField.getValue())));
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

    public void showData()
    {
        ObservableList<Curatorial_dept> showlist = datalist();
        dateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("deptName"));
        levelCol.setCellValueFactory(new PropertyValueFactory<>("deptLevel"));
        guideCol.setCellValueFactory(new PropertyValueFactory<>("guideName"));
        cleanerCol.setCellValueFactory(new PropertyValueFactory<>("cleanerName"));
        envConCol.setCellValueFactory(new PropertyValueFactory<>("envConName"));
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));

        curatorialDeptable.setItems(showlist);
    }

    @FXML
    void delete(ActionEvent event)
    {
        String sql = "DELETE from curatorial_dept WHERE `room` ='" + galleryNoField.getText() + "'";

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
    public void clear()
    {
        dateField.setValue(null);
        nameField.getSelectionModel().clearSelection();
        guideField.getSelectionModel().clearSelection();
        cleanerField.getSelectionModel().clearSelection();
        envConField.getSelectionModel().clearSelection();
        galleryNoField.setText("");
        levelField.setText("");
    }

    @FXML
    void selectData(MouseEvent event)
    {
        clear();
        Curatorial_dept curDept = curatorialDeptable.getSelectionModel().getSelectedItem();
        int no = curatorialDeptable.getSelectionModel().getSelectedIndex();
        if ((no - 1) < -1)
        {
            return;
        }
        dateField.setValue(LocalDate.parse(valueOf(curDept.getLastUpdate())));
        nameField.setValue(valueOf(curDept.getDeptName()));
        guideField.setValue(valueOf(curDept.getGuideName()));
        cleanerField.setValue(valueOf(curDept.getCleanerName()));
        envConField.setValue(valueOf(curDept.getEnvConName()));
        galleryNoField.setText("");
        levelField.setText(valueOf(curDept.getDeptLevel()));
    }

    @FXML
    void update_Crud(ActionEvent event) throws IOException
    {
        String sql = "UPDATE curatorial_dept SET `room` = '" + galleryNoField.getText() + "', `Date` = '" + java.sql.Date.valueOf(dateField.getValue()) + "', `Name` = '" + nameField.getSelectionModel().getSelectedItem() + "', `GalleryLevel` = '" + levelField.getText() + "', `GuideName` = '" + guideField.getSelectionModel().getSelectedItem() + "', `CleanerName` = '" + cleanerField.getSelectionModel().getSelectedItem() + "', `EnvironmentController` = '" + envConField.getSelectionModel().getSelectedItem() + "' WHERE room = '" + galleryNoField.getText() + "'";

        try
        {
            connect = DBUtils.connectDB(url1);
            if (galleryNoField.getText().isEmpty() | levelField.getText().isEmpty() | nameField.getSelectionModel().isEmpty() |
                    guideField.getSelectionModel().isEmpty() | cleanerField.getSelectionModel().isEmpty() | envConField.getSelectionModel().isEmpty() |
                    dateField.getValue() == null)
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
        showData();
        comboBox();
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