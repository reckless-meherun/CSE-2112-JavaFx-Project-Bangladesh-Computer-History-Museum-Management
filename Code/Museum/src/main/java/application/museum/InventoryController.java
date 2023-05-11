package application.museum;

import application.museum.Departments.Artifacts;
import application.museum.Departments.Photo;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class InventoryController implements Initializable
{
    private String url = "jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\InvPhotoTick.db";
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private File photoFile;
    @FXML
    public TreeView<String> treeView;
    @FXML
    public AnchorPane imageAnchor;
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
    private Button browseImageButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button clearButton;
    @FXML
    private ImageView tempImageView;
    @FXML
    private ImageView showImageView;
    @FXML
    private TextField searchBar;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField docNoField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextField rowField;
    @FXML
    private TextField roomField;
    @FXML
    private TextField levelField;
    @FXML
    private ComboBox<String> departmentField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TableView<Artifacts> inventoryTable;
    @FXML
    private TableColumn<Artifacts, Integer> docNoCol;
    @FXML
    private TableColumn<Artifacts, String> dateCol;
    @FXML
    private TableColumn<Artifacts, String> categoryCol;
    @FXML
    private TableColumn<Artifacts, String> descriptionCol;
    @FXML
    private TableColumn<Artifacts, String> departmentCol;
    @FXML
    private TableColumn<Artifacts, Integer> positionCol;
    @FXML
    private TableColumn<Artifacts, Integer> roomCol;
    @FXML
    private TableColumn<Artifacts, Integer> levelCol;
    private boolean isimageChanged = false;
    public void Combo_box()
    {
        List<String> deptList = new ArrayList<>();
        for (String data : DBUtils.dept)
        {
            deptList.add(data);
        }
        ObservableList DEPTLIST = FXCollections.observableArrayList(deptList);
        departmentField.setItems(DEPTLIST);
    }
    static void pushTostack()
    {
        DBUtils.prevfxml.push("Inventory.fxml");
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
    public ObservableList<Artifacts> artifactList()
    {
        ObservableList<Artifacts> artifactList = FXCollections.observableArrayList();

        String sql;
        sql = "SELECT * FROM Inventory_Table";

        try
        {
            System.out.println("oka_1");
            connect = DBUtils.connectDB(url);
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            System.out.println("oka_2");
            while (result.next())
            {
                StringBuilder resourcesPath = getrespath();
                System.out.println("oka_3");
                resourcesPath.append(result.getString("ImagePath"));
                System.out.println("oka_4");
                Artifacts artifact = new Artifacts(result.getInt("doc_no"), result.getDate("Last_Update"), result.getString("Category"), result.getString("Description"), result.getString("Department"), result.getInt("Position"), result.getInt("Room"), result.getInt("Level"), resourcesPath.toString());
                System.out.println("oka_5");
                artifactList.add(artifact);
                System.out.println("oka_6");
            }
            System.out.println("oka_7");
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
        return artifactList;
    }


    public StringBuilder copyImageToResources(File imageFile) throws IOException
    {
        // Determine the path to the resources folder
        StringBuilder resourcesPath = new StringBuilder(getClass().getResource("").getPath());
        //int n=resourcesPath.length();
        resourcesPath.deleteCharAt(0);
        for (int i = 0; i < resourcesPath.length(); i++)
        {
            if (resourcesPath.charAt(i) == '/')
            {

                resourcesPath.replace(i, i + 1, "\\\\");
            }
            if (resourcesPath.charAt(i) == '%')
            {
                resourcesPath.replace(i, i + 3, " ");
            }
            if(resourcesPath.charAt(i)=='t'&&resourcesPath.charAt(i+1)=='a'&&resourcesPath.charAt(i+2)=='r'&& resourcesPath.charAt(i+3)=='g'&& resourcesPath.charAt(i+4)=='e'&& resourcesPath.charAt(i+5)=='t'&& resourcesPath.charAt(i+6)=='/'){
                resourcesPath.delete(i-1,resourcesPath.length());
                break;
            }
        }
        System.out.println(resourcesPath);

        String name = imageFile.getName();
        String extension = "";

        int dotIndex = name.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < name.length() - 1)
        {
            extension = name.substring(dotIndex + 1);
        }

        String fileName = "image_" + System.currentTimeMillis() + "." + extension;
        StringBuilder destination = new StringBuilder("\\src\\main\\resources\\application\\museum\\StudentsPhotos\\" + fileName);

        // Copy the image file to the resources folder with the unique file name
        Path sourcePath = imageFile.toPath();
        Path destinationPath = Paths.get(resourcesPath + "\\src\\main\\resources\\application\\museum\\StudentsPhotos\\" + fileName);
        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
//        direction=destinationPath.toString();
        return destination;
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
            if(resourcesPath.charAt(i)=='t'&&resourcesPath.charAt(i+1)=='a'&&resourcesPath.charAt(i+2)=='r'&& resourcesPath.charAt(i+3)=='g'&& resourcesPath.charAt(i+4)=='e'&& resourcesPath.charAt(i+5)=='t'&& resourcesPath.charAt(i+6)=='/'){
                resourcesPath.delete(i-1,resourcesPath.length());
                break;
            }
        }
        return resourcesPath;
    }

    @FXML
    void insert(ActionEvent event)
    {
        String sql = "INSERT INTO Inventory_Table VALUES (?,?,?,?,?,?,?,?,?)";

        try
        {
            connect = DBUtils.connectDB(url);
            if (docNoField.getText().isEmpty() | dateField.getValue() == null | categoryField.getText().isEmpty() | showImageView.getImage() == null |
                    departmentField.getSelectionModel().isEmpty() |  descriptionField.getText().isEmpty() | rowField.getText().isEmpty() | roomField.getText().isEmpty() | levelField.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            } else
            {
                StringBuilder im = copyImageToResources(photoFile);
                for (int i = 0; i < im.length(); i++)
                {
                    if (im.charAt(i) == '\\')
                    {
                        im.setCharAt(i, '/');
                    }
                }
                String files_path = im.toString();

                prepare = connect.prepareStatement(sql);
                prepare.setDate(1, (java.sql.Date.valueOf(dateField.getValue())));
                prepare.setString(2, categoryField.getText());
                prepare.setString(3, docNoField.getText());
                prepare.setString(4, descriptionField.getText());
                prepare.setString(5, descriptionField.getText());
                prepare.setString(6, (String) departmentField.getSelectionModel().getSelectedItem());
                prepare.setString(7, rowField.getText());
                prepare.setString(8, roomField.getText());
                //prepare.setString(13, (String) gender.getSelectionModel().getSelectedItem());
                prepare.setString(9, files_path);

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

    @FXML
    void insertImage(ActionEvent event) throws IOException
    {
        FileChooser open = new FileChooser();
        Stage stage = (Stage) imageAnchor.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        isimageChanged = true;
        if (file != null)
        {
            StringBuilder im = new StringBuilder(file.toPath().toString());
            photoFile = file;
            for (int i = 0; i < im.length(); i++)
            {
                if (im.charAt(i) == '\\')
                {
                    im.setCharAt(i, '/');
                }
            }
            String destination = im.toString();
            System.out.println(destination);
            Image img = new Image(destination);
            showImageView.setImage(img);
            tempImageView.setVisible(false);
        } else
        {
            System.out.println("Image is missing");
        }
    }

    public void showData()
    {
        ObservableList<Artifacts> showlist = artifactList();
        docNoCol.setCellValueFactory(new PropertyValueFactory<>("doc_no"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("title_description"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("dept"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("row"));
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
        levelCol.setCellValueFactory(new PropertyValueFactory<>("level"));
        inventoryTable.setItems(showlist);
        // System.out.println("showing");
    }

    @FXML
    public void clear()
    {
        docNoField.setText(null);
        dateField.setValue(null);
        categoryField.setText(null);
        descriptionField.setText(null);
        departmentField.getSelectionModel().clearSelection();
        rowField.setText(null);
        roomField.setText(null);
        levelField.setText(null);
        tempImageView.setVisible(true);
        showImageView.setVisible(false);
        isimageChanged=false;
    }

    @FXML
    void selectData(MouseEvent event)
    {
        clear();
        Artifacts artifact = inventoryTable.getSelectionModel().getSelectedItem();
        int no = inventoryTable.getSelectionModel().getSelectedIndex();
        if ((no - 1) < -1)
        {
            return;
        }
        docNoField.setText(valueOf(artifact.getDoc_no()));
        dateField.setValue(LocalDate.parse(valueOf(artifact.getLastUpdate())));
        categoryField.setText(valueOf(artifact.getCategory()));
        descriptionField.setText(valueOf(artifact.getTitle_description()));
        departmentField.setValue(valueOf(artifact.getDept()));
        rowField.setText(valueOf(artifact.getRow()));
        roomField.setText((valueOf(artifact.getRoom())));
        levelField.setText((valueOf(artifact.getLevel())));

        try
        {
            showImageView.setImage(new Image("file:/" + artifact.getImagePath()));
            showImageView.setVisible(true);
            tempImageView.setVisible(false);
            System.out.println(artifact.getImagePath());
        } catch (Exception e)
        {
            System.out.println(artifact.getImagePath());
        }
    }

    @FXML
    void delete(ActionEvent event)
    {
        String sql = "DELETE FROM Inventory_Table WHERE doc_no = " + docNoField.getText() + ";";

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
    void update_Crud(ActionEvent event) throws IOException
    {
        String sql;
        if (isimageChanged)
        {
            StringBuilder im = copyImageToResources(photoFile);
            for (int i = 0; i < im.length(); i++)
            {
                if (im.charAt(i) == '\\')
                {
                    im.setCharAt(i, '/');
                }
            }
            String file_path = im.toString();

            if (dateField.getValue() == null)
            {
                sql = "UPDATE Inventory_Table SET doc_no = '" + docNoField.getText() + "', category = '" + categoryField.getText() + "', description = '" + descriptionField.getText() + "', department = '" + departmentField.getValue() + "', position = '" + rowField.getText() + ", room = '" + roomField.getText() + ", level = '" + levelField.getText() + "', imagepath = '" + file_path + "' WHERE doc_no = '" + docNoField.getText() + "';";
            } else
            {
                sql = "UPDATE Inventory_Table SET doc_no = '" + docNoField.getText() + "', date = '" + java.sql.Date.valueOf(dateField.getValue()) + "', category = '" + categoryField.getText() + "', description = '" + descriptionField.getText() + "', department = '" + departmentField.getValue() + "', position = '" + rowField.getText() + ", room = '" + roomField.getText() + ", level = '" + levelField.getText() + "', imagepath = '" + file_path + "' WHERE doc_no = '" + docNoField.getText() + "';";
            }
        } else
        {
            if (dateField.getValue() == null)
            {
                sql = "UPDATE Inventory_Table SET doc_no = '" + docNoField.getText() + "', category = '" + categoryField.getText() + "', description = '" + descriptionField.getText() + "', department = '" + departmentField.getValue() + "', position = '" + rowField.getText() + ", room = '" + roomField.getText() + ", level = '" + levelField.getText() + "' WHERE doc_no = '" + docNoField.getText() + "';";
            } else
            {
                sql = "UPDATE Inventory_Table SET doc_no = '" + docNoField.getText() + "', date = '" + java.sql.Date.valueOf(dateField.getValue()) + "', category = '" + categoryField.getText() + "', description = '" + descriptionField.getText() + "', department = '" + departmentField.getValue() + "', position = '" + rowField.getText() + ", room = '" + roomField.getText() + ", level = '" + levelField.getText() + "' WHERE doc_no = '" + docNoField.getText() + "';";
            }
        }

        try
        {
            connect = DBUtils.connectDB(url);
            if (docNoField.getText().isEmpty() | dateField.getValue() == null | categoryField.getText().isEmpty() | showImageView.getImage() == null |
                    departmentField.getSelectionModel().isEmpty() |  descriptionField.getText().isEmpty() | rowField.getText().isEmpty() | roomField.getText().isEmpty() | levelField.getText().isEmpty())
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
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            NavigationHandler.HandleNavigation("Inventory.fxml", home, treeView, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        Combo_box();
        showData();
    }
}