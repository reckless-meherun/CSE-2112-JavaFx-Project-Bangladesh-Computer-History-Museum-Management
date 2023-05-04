package application.museum;

import java.io.*;

import application.museum.People.Employee;
import application.museum.People.Gender;
import application.museum.People.Visitor;
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

import java.lang.invoke.VolatileCallSite;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import java.util.jar.JarException;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import static java.lang.String.valueOf;

public class TicketController implements Initializable
{
    @FXML
    public TreeView<String> treeView;
    private String url = "jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\InvPhotoTick.db";
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
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
    private TableColumn<Visitor, Date> dateCol;
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

    public void Combo_box()
    {
        List<String> genderList = new ArrayList<>();
        for (Gender data : Gender.values())
        {
            genderList.add(data.toString());
        }
        ObservableList data_list = FXCollections.observableArrayList(genderList);
        genderField.setItems(data_list);

        List<String> typeList = new ArrayList<>();
        for (String data : DBUtils.ticketTypes)
        {
            typeList.add(data);
        }
        ObservableList data_list_class = FXCollections.observableArrayList(typeList);
        ticketTypeField.setItems(data_list_class);

        List<String> languageList = new ArrayList<>();
        for (String data : DBUtils.language)
        {
            languageList.add(data);
        }
        ObservableList languagelist = FXCollections.observableArrayList(languageList);
        languageField.setItems(languagelist);
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

    StringBuilder getrespath()
    {
        StringBuilder resourcesPath = new StringBuilder(getClass().getResource("").getPath());
        //int n=resourcesPath.length();
        resourcesPath.deleteCharAt(0);
        for (int i = 0; i < resourcesPath.length(); i++)
        {
            if (resourcesPath.charAt(i) == '%')
            {
                resourcesPath.replace(i, i + 3, " ");
            }
            if (resourcesPath.charAt(i) == 'm')
            {
                resourcesPath.delete(i + 1, resourcesPath.length());
                break;
            }
        }
        return resourcesPath;
    }

    public ObservableList<Visitor> datalist()
    {
        ObservableList<Visitor> datalist = FXCollections.observableArrayList();
        String sql;
        sql = "SELECT * FROM Visitors_Table";

        try
        {
            connect = DBUtils.connectDB(url);
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next())
            {
                //System.out.println("oka1");
//                StringBuilder resourcesPath = getrespath();
//                resourcesPath.append(result.getString("img"));
                Gender gm;
                if (result.getString("Gender").equals("MALE"))
                {
                    gm = Gender.MALE;
                } else if (result.getString("Gender").equals("FEMALE"))
                {
                    gm = Gender.FEMALE;
                } else gm = Gender.OTHER;
                Visitor vis;
                // System.out.println("oka2");
                vis = new Visitor(result.getDate("Last_Visit_Date"), result.getString("Name"), result.getInt("Age"), gm, result.getString("Email"), result.getString("Phone"), result.getInt("Total_Visit"), result.getString("Language"));
                //  System.out.println("oka3");
                datalist.add(vis);
                // System.out.println("oka4");
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
        String sql = "INSERT INTO Visitors_Table VALUES (?,?,?,?,?,?,?,?)";

        try
        {
            connect = DBUtils.connectDB(url);
            if (nameField.getText().isEmpty() | ageField.getText().isEmpty() | emailField.getText().isEmpty() | genderField.getSelectionModel().isEmpty() | phoneField.getText().isEmpty() | dateField.getValue() == null | prevVisitField.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            } else
            {
//                StringBuilder im = copyImageToResources(photo);
//                for (int i = 0; i < im.length(); i++)
//                {
//                    if (im.charAt(i) == '\\')
//                    {
//                        im.setCharAt(i, '/');
//                    }
//                }
//                String files_path = im.toString();

                prepare = connect.prepareStatement(sql);
                prepare.setDate(1, (java.sql.Date.valueOf(dateField.getValue())));
                prepare.setString(2, nameField.getText());
                prepare.setString(4, (String) genderField.getSelectionModel().getSelectedItem());
                prepare.setString(3, ageField.getText());
                prepare.setString(6, phoneField.getText());
                prepare.setString(8, (String) languageField.getSelectionModel().getSelectedItem());
                if (!emailField.getText().isEmpty())
                {
                    prepare.setString(5, emailField.getText());
                }

                prepare.setString(7, prevVisitField.getText());

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
        ObservableList<Visitor> showlist = datalist();
        dateCol.setCellValueFactory(new PropertyValueFactory<>("last_vis_date"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("mobile_no"));
        totalVisitCol.setCellValueFactory(new PropertyValueFactory<>("TotalVisitCount"));
        languageCol.setCellValueFactory(new PropertyValueFactory<>("language"));

        visitorTable.setItems(showlist);
    }

    @FXML
    public void clear()
    {
        nameField.setText("");
        ageField.setText("");
        emailField.setText("");
        phoneField.setText("");
        occupationField.setText("");
        prevVisitField.setText("");
        genderField.getSelectionModel().clearSelection();
        languageField.getSelectionModel().clearSelection();
        dateField.setValue(null);
        ticketTypeField.getSelectionModel().clearSelection();
    }

    @FXML
    void selectData(MouseEvent event)
    {
        clear();
        Visitor visitor = visitorTable.getSelectionModel().getSelectedItem();
        int no = visitorTable.getSelectionModel().getSelectedIndex();
        if ((no - 1) < -1)
        {
            return;
        }
        System.out.println("cleared");
        dateField.setValue(LocalDate.parse(valueOf(visitor.getLast_vis_date())));
        nameField.setText(valueOf(visitor.getName()));
        genderField.setValue(valueOf(visitor.getGender()));
        ageField.setText(valueOf(visitor.getAge()));
        phoneField.setText(valueOf(visitor.getMobile_no()));
        prevVisitField.setText(valueOf(visitor.getTotalVisitCount()));
        //ticketTypeField.setValue(valueOf(visitor.));
        languageField.setValue(valueOf(visitor.getLanguage()));

        if (visitor.getEmail() != null)
        {
            emailField.setText(valueOf(visitor.getEmail()));
        }
        //System.out.println("value set");
    }

    @FXML
    void update_Crud(ActionEvent event) throws IOException
    {
        String sql;

        if (dateField.getValue() == null)
        {
            sql = "UPDATE Visitors_Table SET name = '" + nameField.getText() + "', age = '" + ageField.getText() + "', email = '" + emailField.getText() + "', phone = '" + phoneField.getText() + "', total_visit = '" + prevVisitField.getText() + "', language = '" + languageField.getValue() + "' WHERE phone = '" + phoneField.getText() + "';";
        } else
        {
            sql = "UPDATE Visitors_Table SET last_visit_date = '" + java.sql.Date.valueOf(dateField.getValue()) + "', name = '" + nameField.getText() + "', age = '" + ageField.getText() + "', email = '" + emailField.getText() + "', phone = '" + phoneField.getText() + "', total_visit = '" + prevVisitField.getText() + "', language = '" + languageField.getValue() + "' WHERE phone = '" + phoneField.getText() + "';";
        }

        try
        {
            connect = DBUtils.connectDB(url);
            if (nameField.getText().isEmpty() | ageField.getText().isEmpty() | emailField.getText().isEmpty() | genderField.getSelectionModel().isEmpty() | languageField.getSelectionModel().isEmpty() | phoneField.getText().isEmpty() | dateField.getValue() == null | prevVisitField.getText().isEmpty())
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

    @FXML
    public void printTicket()
    {
        try
        {
            String pdf = "C:\\University Stuff\\2-1\\OOP Project\\Museum 2\\Code\\Museum\\src\\main\\resources\\GeneratedPDFs\\TicketPDFs\\" + "MyTicket.pdf";
            List<Visitor> visList = new ArrayList<Visitor>();
            Visitor visitorOne = new Visitor();
            visitorOne.setName("Meherun");
            visitorOne.setAge(20);
            visitorOne.set_mobile_no("01815803974");
            visList.add(visitorOne);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(visList);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("TicketParam", dataSource);
//            InputStream input = new FileInputStream(new File("src\\main\\resources\\JRXMLs\\Ticket.jrxml"));
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/application/museum/Ticket.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint);
//            OutputStream output = new FileOutputStream(new File(pdf));
//            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            System.out.println("File generated");
        } catch (Exception e)
        {
            System.out.println(e);
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
        Combo_box();
        showData();
    }
}

