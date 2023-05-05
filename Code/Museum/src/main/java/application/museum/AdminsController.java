package application.museum;

import application.museum.People.Admins;
import application.museum.People.developer;
import javafx.animation.TranslateTransition;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminsController implements Initializable
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
    private PasswordField confirmpass;

    @FXML
    private Button curator;

    @FXML
    private Button delete;

    @FXML
    private Button departments;

    @FXML
    private Button developer;

    @FXML
    private Button educator;

    @FXML
    private Button employee;

    @FXML
    private Button home;

    @FXML
    private TextField id;

    @FXML
    private AnchorPane paneside;

    @FXML
    private PasswordField password;

    @FXML
    private Button photogallery;

    @FXML
    private AnchorPane scene2;


    @FXML
    private TextField stext;

    @FXML
    private Button sbut;

    @FXML
    private Button student;

    @FXML
    private Text studentName;

    @FXML
    private Button tickets;

    @FXML
    private TableView<Admins> table_view;

    @FXML
    private Button update;

    @FXML
    private TextField username;
    @FXML
    private TableColumn<Admins, String> username_table;

    @FXML
    private TableColumn<Admins, String> password_table;

    @FXML
    private TableColumn<Admins, Integer> id_table;

    private String url = "jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\userpassword.db";

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    static void pushtostack()
    {
        DBUtils.prevfxml.push("admins.fxml");
    }

    @FXML
    void run3()
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
    void switchToemployee(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("admins.fxml");
        DBUtils.changeScene(event, "employee.fxml", false);
    }

    @FXML
    void switchTobod(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("admins.fxml");
        DBUtils.changeScene(event, "aboutus.fxml", false);
    }

    @FXML
    void switchTocurato(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("admins.fxml");
        DBUtils.changeScene(event, "curator.fxml", false);
    }

    @FXML
    void switchTodevloper(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("admins.fxml");
        DBUtils.changeScene(event, "developer.fxml", false);
    }

    @FXML
    void switchToeducator(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("admins.fxml");
        DBUtils.changeScene(event, "educator.fxml", false);
    }

//    @FXML
//    public void switchTotickets(ActionEvent event) throws IOException
//    {
//        AdminsController.pushtostack();
//        DBUtils.changeScene(event, "Tickets.fxml", false);
//    }
//
//    @FXML
//    void switchToInventory(ActionEvent event) throws IOException
//    {
//        AdminsController.pushtostack();
//        DBUtils.changeScene(event, "Inventory.fxml", false);
//    }
//
//    @FXML
//    void switchToGallery(ActionEvent event) throws IOException
//    {
//        AdminsController.pushtostack();
//        DBUtils.changeScene(event, "PhotoGalleryScene.fxml", false);
//    }

    @FXML
    void switchTostudents(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("admins.fxml");
        DBUtils.changeScene(event, "students.fxml", false);
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
    void run2()
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
            NavigationHandler.HandleNavigation("admins.fxml", home, treeView, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        showData();
        paneside.setTranslateX(0);
        bar2.setVisible(true);
        bar1.setVisible(false);
        bar3.setVisible(true);
        bar4.setVisible(false);
        scene2.setTranslateX(378);
        stext.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Search();
            }
        });
    }

    void clear()
    {
        id.setText("");
        username.setText("");
        password.setText("");
        confirmpass.setText("");
    }

    public ObservableList<Admins> datalist()
    {
        ObservableList<Admins> datalist = FXCollections.observableArrayList();

        String sql;
        sql = "SELECT * FROM users";

        try
        {
            connect = DBUtils.connectDB(url);
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();


            while (result.next())
            {
                Admins user = new Admins(result.getInt("id"), result.getString("username"), "*********");

                datalist.add(user);
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
        ObservableList<Admins> showlist = datalist();
        id_table.setCellValueFactory(new PropertyValueFactory<>("id"));
        username_table.setCellValueFactory(new PropertyValueFactory<>("username"));
        password_table.setCellValueFactory(new PropertyValueFactory<>("password"));
        table_view.setItems(showlist);
    }
    public void showData(ObservableList<Admins> admins1)
    {
        //ObservableList<Admins> showlist = datalist();
        id_table.setCellValueFactory(new PropertyValueFactory<>("id"));
        username_table.setCellValueFactory(new PropertyValueFactory<>("username"));
        password_table.setCellValueFactory(new PropertyValueFactory<>("password"));
        table_view.setItems(admins1);
    }

    @FXML
    void insert(ActionEvent event)
    {
        String sql = "INSERT INTO users VALUES (?,?,?)";
        PreparedStatement PsIsUserExist = null;

        try
        {
            connect = DBUtils.connectDB(url);
            PsIsUserExist = connect.prepareStatement("SELECT * FROM users WHERE username=?");
            PsIsUserExist.setString(1, username.getText());
            result = PsIsUserExist.executeQuery();
            if (id.getText().isEmpty() | username.getText().isEmpty() | password.getText().isEmpty() |
                    confirmpass.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            } else if (password.getText().compareTo(confirmpass.getText()) != 0)
            {
                System.out.println(password.getText());
                System.out.println(confirmpass.getText());
                System.out.println(password.getText().compareTo(confirmpass.getText()));
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            password doesn't match.  ");
                alert.setContentText("                             please check the password again.");
                alert.showAndWait();
            } else if (result.isBeforeFirst())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Username Not Available  ");
                alert.setContentText("                             Please choose a new username");
                alert.showAndWait();
            } else
            {

                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, Integer.parseInt(id.getText()));
                prepare.setString(2, username.getText());
                prepare.setString(3, password.getText());
                prepare.execute();
                System.out.println("ok12");
                showData();
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
                if (statement != null)
                {
                    statement.close();
                }

            } catch (Exception e)
            {

            }
        }
    }

    @FXML
    void selectData(MouseEvent event)
    {
        Admins user = table_view.getSelectionModel().getSelectedItem();
        int no = table_view.getSelectionModel().getSelectedIndex();
        if ((no - 1) < -1)
        {
            return;
        }
        id.setText(String.valueOf(user.getId()));
        username.setText(String.valueOf(user.getUsername()));
        run3();
        run2();

    }

    @FXML
    void update_Crud(ActionEvent event)
    {

        String sql = "UPDATE users SET `username`= '" + username.getText() + "', `password` = '" + password.getText() + "' WHERE id = '" + id.getText() + "'";
        try
        {
            connect = DBUtils.connectDB(url);
            if (id.getText().isEmpty() | username.getText().isEmpty() | password.getText().isEmpty() |
                    confirmpass.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            } else if (password.getText().compareTo(confirmpass.getText()) != 0)
            {
                System.out.println(password.getText());
                System.out.println(confirmpass.getText());
                System.out.println(password.getText().compareTo(confirmpass.getText()));
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            password doesn't match.  ");
                alert.setContentText("                             please check the password again.");
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
    void delete(ActionEvent event)
    {
        String sql = "DELETE from users WHERE `id` ='" + id.getText() + "'";

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
    void Search() {
        String searchName = null;
        if (!stext.getText().isEmpty())
            searchName = stext.getText(); // the name you want to search for
        else {
            showData();
            return;
        }
        ObservableList<Admins> dev=datalist();
        ObservableList<Admins> dev1=FXCollections.observableArrayList();
        for(Admins d: dev){
            if(searchName.equals(d.getUsername())){
                dev1.add(d);
            }
        }
        if(dev1.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("                                     Error!!!!!");
            alert.setHeaderText("            Admin not found!  ");
            alert.setContentText("                             Please enter correct credentials");
            alert.showAndWait();
            showData();
            return;
        }
        showData(dev1);
    }
}