package application.museum;

import application.museum.Departments.Photo;
import application.museum.People.Admins;
import application.museum.People.Employee;
import application.museum.People.Gender;
import application.museum.Departments.Photo;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.crypto.Cipher;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static java.lang.String.valueOf;

public class Gallerycontroller implements Initializable
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
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button browseImageButton;
    @FXML
    private AnchorPane imageAnchor;
    @FXML
    private ImageView showImageView;
    @FXML
    private ImageView tempImageView;
    private File photoFile;
    @FXML
    private TextField searchBar;
    @FXML
    private TextField titleField;
    @FXML
    private TextField catalogNoField;
    @FXML
    private TextField docNoField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextField rowField;
    @FXML
    private TextField roomField;
    @FXML
    private ComboBox<String> departmentField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TableView<Photo> photoGalleryTable;
    @FXML
    private TableColumn<Photo, Integer> docNoCol;
    @FXML
    private TableColumn<Photo, String> dateCol;
    @FXML
    private TableColumn<Photo, String> catalogNoCol;
    @FXML
    private TableColumn<Photo, String> titleCol;
    @FXML
    private TableColumn<Photo, String> descriptionCol;
    @FXML
    private TableColumn<Photo, String> departmentCol;
    @FXML
    private TableColumn<Photo, Integer> positionCol;
    @FXML
    private TableColumn<Photo, Integer> roomCol;
    @FXML
    private TableColumn<Photo, String> imagePathCol;

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
    private boolean isimageChanged = false;

    static void pushtostack()
    {
        DBUtils.prevfxml.push("PhotoGalleryScene.fxml");

    }

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
        String sql = "INSERT INTO PhotoGallery_Table VALUES (?,?,?,?,?,?,?,?,?)";

        try
        {
            connect = DBUtils.connectDB(url);
            if (dateField.getValue() == null | catalogNoField.getText().isEmpty() | showImageView.getImage() == null | docNoField.getText().isEmpty() |
                    departmentField.getSelectionModel().isEmpty() | titleField.getText().isEmpty() |
                    descriptionField.getText().isEmpty() | rowField.getText().isEmpty() | roomField.getText().isEmpty())
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
                prepare.setString(2, catalogNoField.getText());
                prepare.setString(3, docNoField.getText());
                prepare.setString(4, titleField.getText());
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

    public ObservableList<Photo> photolist()
    {
        ObservableList<Photo> photolist = FXCollections.observableArrayList();

        String sql;
        sql = "SELECT * FROM PhotoGallery_Table";

        try
        {
            connect = DBUtils.connectDB(url);
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next())
            {
                StringBuilder resourcesPath = getrespath();
                resourcesPath.append(result.getString("ImagePath"));
                Photo photo = new Photo(result.getDate("date"), result.getInt("Catalog_no"), result.getInt("doc_no"), result.getString("Title"), result.getString("Description"), result.getString("Department"), result.getInt("Position"), result.getInt("Room"), resourcesPath.toString());
                photolist.add(photo);
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
        return photolist;
    }

    public void showData()
    {
        //   System.out.println("Trying to show");
        ObservableList<Photo> showlist = photolist();
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        //  System.out.println("showing date");
        catalogNoCol.setCellValueFactory(new PropertyValueFactory<>("catalog_no"));
        docNoCol.setCellValueFactory(new PropertyValueFactory<>("doc_no"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));

        photoGalleryTable.setItems(showlist);
        // System.out.println("showing");
    }
    public void showData(ObservableList<Photo> showlist )
    {
        //   System.out.println("Trying to show");

        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        //  System.out.println("showing date");
        catalogNoCol.setCellValueFactory(new PropertyValueFactory<>("catalog_no"));
        docNoCol.setCellValueFactory(new PropertyValueFactory<>("doc_no"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));

        photoGalleryTable.setItems(showlist);
        // System.out.println("showing");
    }

    @FXML
    public void clear()
    {
        dateField.setValue(null);
        catalogNoCol.setText(null);
        docNoField.setText(null);
        titleField.setText(null);
        descriptionField.setText(null);
        departmentField.getSelectionModel().clearSelection();
        rowField.setText(null);
        roomField.setText(null);
        tempImageView.setVisible(true);
        showImageView.setVisible(false);
        isimageChanged=false;
    }

    @FXML
    void selectData(MouseEvent event)
    {
        clear();
        Photo photo = photoGalleryTable.getSelectionModel().getSelectedItem();
        int no = photoGalleryTable.getSelectionModel().getSelectedIndex();
        if ((no - 1) < -1)
        {
            return;
        }
        dateField.setValue(LocalDate.parse(valueOf(photo.getDate())));
        catalogNoField.setText(valueOf(photo.getCatalog_no()));
        docNoField.setText(valueOf(photo.getDoc_no()));
        titleField.setText(valueOf(photo.getTitle()));
        descriptionField.setText(valueOf(photo.getDescription()));
        departmentField.setValue(valueOf(photo.getDepartment()));
        rowField.setText(valueOf(photo.getPosition()));
        roomField.setText((valueOf(photo.getRoom())));
        try
        {
            showImageView.setImage(new Image("file:/" + photo.getImagePath()));
            showImageView.setVisible(true);
            tempImageView.setVisible(false);
            System.out.println(photo.getImagePath());
        } catch (Exception e)
        {
            System.out.println(photo.getImagePath());
        }
    }

    @FXML
    void delete(ActionEvent event)
    {
        String sql = "DELETE FROM PhotoGallery_Table WHERE doc_no = " + docNoField.getText() + ";";

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
                sql = "UPDATE PhotoGallery_Table SET catalog_no = '" + catalogNoField.getText() + "', doc_no = '" + docNoField.getText() + "', title = '" + titleField.getText() + "', description = '" + descriptionField.getText() + "', department = '" + departmentField.getValue() + "', position = '" + rowField.getText() + ", room = '" + roomField.getText() + "', imagepath = '" + file_path + "' WHERE doc_no = '" + docNoField.getText() + "';";
            } else
            {
                sql = "UPDATE PhotoGallery_Table SET date = '" + java.sql.Date.valueOf(dateField.getValue()) + "', catalog_no = '" + catalogNoField.getText() + "', doc_no = '" + docNoField.getText() + "', title = '" + titleField.getText() + "', description = '" + descriptionField.getText() + "', department = '" + departmentField.getValue() + "', position = '" + rowField.getText() + ", room = '" + roomField.getText() + "', imagepath = '" + file_path + "' WHERE doc_no = '" + docNoField.getText() + "';";
            }
        } else
        {
            if (dateField.getValue() == null)
            {
                sql = "UPDATE PhotoGallery_Table SET catalog_no = '" + catalogNoField.getText() + "', doc_no = '" + docNoField.getText() + "', title = '" + titleField.getText() + "', description = '" + descriptionField.getText() + "', department = '" + departmentField.getValue() + "', position = '" + rowField.getText() + ", room = '" + roomField.getText() + "' WHERE doc_no = '" + docNoField.getText() + "';";
            } else
            {
                sql = "UPDATE PhotoGallery_Table SET date = '" + java.sql.Date.valueOf(dateField.getValue()) + "', catalog_no = '" + catalogNoField.getText() + "', doc_no = '" + docNoField.getText() + "', title = '" + titleField.getText() + "', description = '" + descriptionField.getText() + "', department = '" + departmentField.getValue() + "', position = '" + rowField.getText() + ", room = '" + roomField.getText() + "' WHERE doc_no = '" + docNoField.getText() + "';";
            }
        }

        try
        {
            connect = DBUtils.connectDB(url);
            if (dateField.getValue() == null | catalogNoField.getText().isEmpty() | showImageView.getImage() == null | docNoField.getText().isEmpty() |
                    departmentField.getSelectionModel().isEmpty() | titleField.getText().isEmpty() |
                    descriptionField.getText().isEmpty() | rowField.getText().isEmpty() | roomField.getText().isEmpty())
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
        ProfileIcon.setText(String.valueOf(DBUtils.username.charAt(0)));
        try
        {
            NavigationHandler.HandleNavigation("PhotoGalleryScene.fxml", home, treeView, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        Combo_box();
        showData();
        searchBar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Search();
            }
        });
    }
    @FXML
    void Search() {
        Integer searchName = null;
        if (!searchBar.getText().isEmpty())
            searchName = Integer.parseInt(searchBar.getText()); // the name you want to search for
        else {
            showData();
            return;
        }
        ObservableList<Photo> dev=photolist();
        ObservableList<Photo> dev1=FXCollections.observableArrayList();
        for(Photo d: dev){
            if(searchName.equals(d.getCatalog_no())){
                dev1.add(d);
            }
        }
        if(dev1.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("                                     Error!!!!!");
            alert.setHeaderText("            Photo not found!  ");
            alert.setContentText("                             Please enter correct credentials");
            alert.showAndWait();
            showData();
            return;
        }
        showData(dev1);
    }
}
