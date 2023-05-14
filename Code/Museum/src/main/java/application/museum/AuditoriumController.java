package application.museum;

import application.museum.Departments.Auditorium;
import application.museum.Departments.Security;
import application.museum.People.Employee;
import application.museum.People.Gender;
import application.museum.People.course;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static java.lang.String.valueOf;

public class AuditoriumController implements Initializable {
    @FXML
    public TreeView<String> treeView;
    @FXML
    private Button GoBackButton;
    @FXML
    private Button LogOutButton;
    @FXML
    private Button aboutus;
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
    private Button home1;
    @FXML
    private Button photogallery;
    @FXML
    private Button tickets;
    @FXML
    private TextField textField;
    private Connection connect;
    private PreparedStatement prepare;
    private Text cname;
//    private ComboBox<String>

    @FXML
    private Button LogoutButton;


    @FXML
    private ComboBox<String> attend;

    @FXML
    private ComboBox<String> cleaner;

    @FXML
    private Button clear;

    @FXML
    private DatePicker date;

    @FXML
    private TextField galary;


    @FXML
    private Button home11;

    @FXML
    private Button home12;

    @FXML
    private ComboBox<String> spk;

    @FXML
    private TextField stext;

    @FXML
    private Text studentName;

    @FXML
    private TableView<Auditorium> table_view;

    @FXML
    private TableColumn<?, ?> tattendent;

    @FXML
    private TableColumn<?, ?> tcleaner;

    @FXML
    private ComboBox<String> tech;

    @FXML
    private TableColumn<?, ?> tgalary;


    @FXML
    private TableColumn<?, ?> tlastup;


    @FXML
    private TableColumn<?, ?> tspeaker;

    @FXML
    private TableColumn<?, ?> ttech;
    private Statement statement;
    private ResultSet result;


    private static String name_here = "AuditoriumScene.fxml";
    private String url="jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\Departments.db";
    private String url1 = "jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\employee.db";
    static void pushToStack()
    {
        DBUtils.prevfxml.push(name_here);
    }
    public int k=0;

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            NavigationHandler.HandleNavigation(name_here, home, treeView, photogallery, articles, aboutus, tickets, LogOutButton, GoBackButton);
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
        comboBox();
        showData();
        stext.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Search();
            }
        });
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
        String sql = "INSERT INTO Auditorium VALUES (?,?,?,?,?,?,?)";

        try{
            connect = DBUtils.connectDB(url);
            if (galary.getText().isEmpty() | inputDate.getValue() == null|spk.getSelectionModel().isEmpty()|cleaner.getSelectionModel().isEmpty()
            |tech.getSelectionModel().isEmpty()|attend.getSelectionModel().isEmpty())

            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            }
            else {
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, k+1);
                prepare.setString(2, galary.getText());
                prepare.setString(3, attend.getSelectionModel().getSelectedItem());
                prepare.setString(4, spk.getSelectionModel().getSelectedItem());
                prepare.setString(5, tech.getSelectionModel().getSelectedItem());
                prepare.setString(6, cleaner.getSelectionModel().getSelectedItem());
                prepare.setDate(7, (java.sql.Date.valueOf(inputDate.getValue())));
                prepare.execute();
                showData();
                //System.out.println("ok12");
                clear();


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
    public ObservableList<Auditorium> datalist()
    {
        ObservableList<Auditorium> datalist = FXCollections.observableArrayList();

        String sql;
        sql = "SELECT * FROM Auditorium";

        try
        {
            connect = DBUtils.connectDB(url);
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next())
            {

                Auditorium aud = new Auditorium(result.getString("AttendantName"),result.getString("SpeakerName"),result.getString("TechnicianName"),result.getString("GalleryName"),result.getString("CleanerName"),result.getDate("Date"));
                datalist.add(aud);
                k=Math.max(k,result.getInt("ID"));
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
        ObservableList<Auditorium> showlist = datalist();
        tlastup.setCellValueFactory(new PropertyValueFactory<>("date"));
        tgalary.setCellValueFactory(new PropertyValueFactory<>("DeptName"));
        tattendent.setCellValueFactory(new PropertyValueFactory<>("AttendantName"));
        tspeaker.setCellValueFactory(new PropertyValueFactory<>("SpeakerName"));
        tcleaner.setCellValueFactory(new PropertyValueFactory<>("cleanerName"));
        ttech.setCellValueFactory(new PropertyValueFactory<>("TechnicianName"));



        table_view.setItems(showlist);
    }
    public void showData(ObservableList<Auditorium> showlist)
    {
        tlastup.setCellValueFactory(new PropertyValueFactory<>("date"));
        tgalary.setCellValueFactory(new PropertyValueFactory<>("DeptName"));
        tattendent.setCellValueFactory(new PropertyValueFactory<>("AttendantName"));
        tspeaker.setCellValueFactory(new PropertyValueFactory<>("SpeakerName"));
        tcleaner.setCellValueFactory(new PropertyValueFactory<>("cleanerName"));
        ttech.setCellValueFactory(new PropertyValueFactory<>("TechnicianName"));



        table_view.setItems(showlist);
    }
    @FXML
    void selectData(MouseEvent event)
    {
        clear();
        Auditorium auditorium = table_view.getSelectionModel().getSelectedItem();
        int no = table_view.getSelectionModel().getSelectedIndex();
        if ((no - 1) < -1)
        {
            return;
        }

        galary.setText(valueOf(auditorium.getDeptName()));
        attend.setValue(valueOf(auditorium.getAttendantName()));
        tech.setValue(auditorium.getTechnicianName());
        spk.setValue(auditorium.getSpeakerName());
        cleaner.setValue(auditorium.getCleaner());
        inputDate.setValue(LocalDate.parse(valueOf(auditorium.getDate())));

    }
    @FXML
    void delete(ActionEvent event)
    {
        String sql = "DELETE from Auditorium WHERE `GalleryName` ='" + galary.getText() + "'";

        try
        {
            connect = DBUtils.connectDB(url);
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
        galary.setText("");
        attend.getSelectionModel().clearSelection();
        cleaner.getSelectionModel().clearSelection();
        spk.getSelectionModel().clearSelection();
        tech.getSelectionModel().clearSelection();
        inputDate.setValue(null);

    }
    ArrayList<Employee> employeeList()
    {
        ArrayList<Employee> list = new ArrayList<>();
        String sql;
        sql = "SELECT * FROM Employee";

        try
        {
            connect = DBUtils.connectDB(url1);
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next())
            {
                StringBuilder resourcesPath = getrespath();
                //System.out.println(resourcesPath);
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
        //System.out.println(resourcesPath);
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
    public void comboBox()
    {


        /** guide combo box */

        ArrayList<String> AttendentListStr = new ArrayList<>();
        ArrayList<Employee> Employeelist = employeeList();
        ArrayList<String> spkListStr = new ArrayList<>();
        ArrayList<String> cleanerListStr=  new ArrayList<>();
        ArrayList<String> TechList= new ArrayList<>();

        for (Employee emp : Employeelist )
        {
            //System.out.println(emp.getDesignation().toLowerCase()+" "+designation.toLowerCase());
            if (emp.getDesignation().toLowerCase().equals("attendant"))
            {
                AttendentListStr.add(emp.getName());
                //System.out.println(emp.getName());
            }
            if(emp.getDesignation().toLowerCase().equals("cleaner")){
                cleanerListStr.add(emp.getName());
            }
            if(emp.getDesignation().toLowerCase().equals("speaker")){
                spkListStr.add(emp.getName());
            }
            if(emp.getDesignation().toLowerCase().equals("technician")){
                TechList.add(emp.getName());
            }
        }

        ObservableList data1_list = FXCollections.observableArrayList(AttendentListStr);
        attend.setItems(data1_list);
        ObservableList data2_list = FXCollections.observableArrayList(cleanerListStr);
        cleaner.setItems(data2_list);
        ObservableList data3_list = FXCollections.observableArrayList(TechList);
        tech.setItems(data3_list);
        ObservableList data4_list = FXCollections.observableArrayList(spkListStr);
        spk.setItems(data4_list);

    }
    @FXML
    void Search() {
        String searchName = null;
        if (!stext.getText().isEmpty())
            searchName = stext.getText(); // the name you want to search for
        else {
            showData();
            return;
        }
        ObservableList<Auditorium> dev=datalist();
        ObservableList<Auditorium> dev1=FXCollections.observableArrayList();
        for(Auditorium d: dev){
            //System.out.println(d.getDeptName());
            if(searchName.equals(d.getDeptName())){
                dev1.add(d);
            }
        }
        if(dev1.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("                                     Error!!!!!");
            alert.setHeaderText("            Security not found!  ");
            alert.setContentText("                             Please enter correct credentials");
            alert.showAndWait();
            showData();
            return;
        }
        showData(dev1);
    }
    @FXML
    void update_Crud(ActionEvent event) throws IOException
    {
        String sql = "UPDATE Auditorium SET `AttendantName` = '" + attend.getSelectionModel().getSelectedItem() + "', `Date` = '" + java.sql.Date.valueOf(inputDate.getValue()) + "', `TechnicianName` = '" + tech.getSelectionModel().getSelectedItem()+ "', `CleanerName` = '" + cleaner.getSelectionModel().getSelectedItem() + "', `SpeakerName` = '" + spk.getSelectionModel().getSelectedItem() + "' WHERE  GalleryName ='" + galary.getText() + "'";


        try
        {
            connect = DBUtils.connectDB(url);
            if (galary.getText().isEmpty() | inputDate.getValue() == null|spk.getSelectionModel().isEmpty()|cleaner.getSelectionModel().isEmpty()
                    |tech.getSelectionModel().isEmpty()|attend.getSelectionModel().isEmpty())
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
