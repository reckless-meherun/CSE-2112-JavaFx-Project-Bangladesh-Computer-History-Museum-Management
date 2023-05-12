package application.museum;

import application.museum.People.*;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
import javafx.util.Duration;

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

public class Studentscontroller implements Initializable
{
    @FXML
    TreeView<String> treeView;

    private static File photo = null;
    @FXML
    private Button BOD;
    @FXML
    private Button GoBackButton;
    @FXML
    private TextField Id;
    @FXML
    private TextField Institutename;
    @FXML
    private Button LogoutButton;
    @FXML
    private Text ProfileIcon;
    @FXML
    private Button Clear;
    @FXML
    private AnchorPane SceneTwo;
    @FXML
    private AnchorPane SceneTwo1;
    @FXML
    private Button aboutus;
    @FXML
    private Button add;
    @FXML
    private Button admins;
    @FXML
    private TextField adress;
    @FXML
    private ImageView temp;
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
    private TextField course;
    @FXML
    private Button curator;
    @FXML
    private Button delete;
    @FXML
    private Button departments;
    @FXML
    private Button developer;
    @FXML
    private DatePicker dob;
    @FXML
    private Button educator;
    @FXML
    private TextField email;
    @FXML
    private Button employee;
    @FXML
    private DatePicker finishingdate;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private Button home;
    @FXML
    private Button image;
    @FXML
    private TextField name;
    @FXML
    private AnchorPane paneside;
    @FXML
    private TextField phonenumber;
    @FXML
    private Button photogallery;
    @FXML
    private AnchorPane scene2;
    @FXML
    private ImageView show;
    @FXML
    private DatePicker staringdate;
    @FXML
    private Button student;
    @FXML
    private Text studentName;
    @FXML
    private Button tickets;
    @FXML
    private Button update;


    @FXML
    private ComboBox<String> Course;


    @FXML
    private TableColumn<Students, String> t_adress;

    @FXML
    private TableColumn<Students, String> t_course;
    @FXML
    private TableColumn<Students, course> t_institute;

    @FXML
    private TableColumn<Students, java.util.Date> t_dob;

    @FXML
    private TableColumn<Students, String> t_email;

    @FXML
    private TableColumn<Students, java.util.Date> t_fdate;

    @FXML
    private TableColumn<Students, String> t_gender;

    @FXML
    private TableColumn<Students, Integer> t_id;

    @FXML
    private TableColumn<Students, java.util.Date> t_jdate;

    @FXML
    private TableColumn<Students, String> t_name;

    @FXML
    private TableColumn<Students, String> t_phoneno;

    @FXML
    private TableView<Students> table_view;

    @FXML
    private Button sbut;

    @FXML
    private TextField stext;

    private String st=null;


    private String url = "jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\database.db";

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private boolean isimageChanged = false;

    static void pushtostack()
    {
        DBUtils.prevfxml.push("students.fxml");
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
        DBUtils.prevfxml.push("students.fxml");
        DBUtils.changeScene(event, "employee.fxml", false);
    }

    @FXML
    void switchTobod(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("students.fxml");
        DBUtils.changeScene(event, "aboutus.fxml", false);
    }

    @FXML
    void switchTocurator(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("students.fxml");
        DBUtils.changeScene(event, "curator.fxml", false);
    }

    @FXML
    void switchTodevloper(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("students.fxml");
        DBUtils.changeScene(event, "developer.fxml", false);
    }

    @FXML
    void switchToeducator(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("students.fxml");
        DBUtils.changeScene(event, "educator.fxml", false);
    }

    @FXML
    void switchToadmins(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("students.fxml");
        DBUtils.changeScene(event, "admins.fxml", false);
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
            NavigationHandler.HandleNavigation("students.fxml", home, treeView, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        getData();
        Combo_box();
        showData();

//        DBUtils.courseArrayList.clear();
//        DBUtils.courseArrayList=detalist();
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
    @FXML
    void Search() {
        String searchName = null;
        if (!stext.getText().isEmpty())
            searchName = stext.getText(); // the name you want to search for
        else {
            showData();
            return;
        }
        ObservableList<Students> dev=datalist();
        ObservableList<Students> dev1=FXCollections.observableArrayList();
        for(Students d: dev){
            if(searchName.equals(d.getName())){
                dev1.add(d);
            }
        }
        if(dev1.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("                                     Error!!!!!");
            alert.setHeaderText("            developer not found!  ");
            alert.setContentText("                             Please enter correct credentials");
            alert.showAndWait();
            showData();
            return;
        }
        showData(dev1);
    }
    void getData(){
        DBUtils.courseArrayList.clear();
        DBUtils.courseArrayList=detalist();
    }
    public ArrayList<application.museum.People.course> detalist()
    {
        ArrayList<course> datalist = new ArrayList<>();

        String sql;
        sql ="SELECT * FROM course";

        try {
            connect= DBUtils.connectDB(url);
            prepare = connect.prepareStatement(sql);
            result=prepare.executeQuery();


            while(result.next())
            {


                course emp;
                boolean fin;
                if(result.getString("courses")=="YES"){
                    fin=true;
                }
                else {
                    fin=false;
                }

                if(result.getDate("resign")!=null && result.getString("teacher")==null) {


                    emp = new course(result.getString("Name"),result.getDate("jdate"),fin,Integer.valueOf(result.getString("total")));
                }
                else{
                    emp = new course(result.getString("Name"),result.getDate("jdate"),fin,Integer.valueOf(result.getString("total")));
                }
                datalist.add(emp);
            }

        }catch (Exception e) {
            System.out.println("course database error");
        }
        finally
        {
            try
            {
                connect.close();
                result.close();
                prepare.close();
                if(statement!=null) {
                    statement.close();
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return datalist;
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
    void insertImage(ActionEvent event) throws IOException
    {
        FileChooser open = new FileChooser();
        Stage stage = (Stage) scene2.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        isimageChanged = true;
        if (file != null)
        {
            StringBuilder im = new StringBuilder(file.toPath().toString());
            photo = file;
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
            show.setImage(img);
            temp.setVisible(false);
        } else
        {
            System.out.println("Student pic is missing");
        }
    }

    public void Combo_box()
    {
        List<String> list = new ArrayList<>();
        for (Gender data : Gender.values())
        {
            list.add(data.toString());
        }
        ObservableList data_list = FXCollections.observableArrayList(list);
        gender.setItems(data_list);
        ArrayList<String> cors=new ArrayList<>();
        for(course c:DBUtils.courseArrayList){
            cors.add(c.getCourseName());
            System.out.println(c.getCourseName());
        }
        ObservableList cor = FXCollections.observableArrayList(cors);
        Course.setItems(cor);

//        List<String> class_list=new ArrayList<>();
//        for(String data:Combo_subject)
//        {
//            class_list.add(data);
//        }
//        ObservableList data_list_class= FXCollections.observableArrayList(class_list);
//        crud_subject.setItems(data_list_class);
    }
    @FXML
    public void clear()
    {
        Id.setText("");
        name.setText("");
        gender.getSelectionModel().clearSelection();
        show.setImage(null);
        temp.setVisible(true);
        Institutename.setText("");
        email.setText("");
        phonenumber.setText("");
        adress.setText("");
        Course.getSelectionModel().clearSelection();
        staringdate.setValue(null);
        finishingdate.setValue(null);
        dob.setValue(null);
        photo = null;

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
    @FXML
    void insert(ActionEvent event)
    {
        String sql = "INSERT INTO students VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        try
        {
            connect = DBUtils.connectDB(url);
            if (Id.getText().isEmpty() | name.getText().isEmpty() | show.getImage() == null | Course.getSelectionModel().isEmpty() |
                    Institutename.getText().isEmpty() | dob.getValue() == null | staringdate.getValue() == null|gender.getSelectionModel().isEmpty()|
                    phonenumber.getText().isEmpty()|adress.getText().isEmpty()
            )
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            } else
            {
                StringBuilder im = copyImageToResources(photo);
                for (int i = 0; i < im.length(); i++)
                {
                    if (im.charAt(i) == '\\')
                    {
                        im.setCharAt(i, '/');
                    }
                }
                String file_path = im.toString();


                prepare = connect.prepareStatement(sql);
                prepare.setString(1, Id.getText());
                prepare.setString(2, name.getText());
                prepare.setString(3, (String)gender.getSelectionModel().getSelectedItem() );
                prepare.setString(5,Institutename.getText());
                prepare.setString(6, email.getText());
                prepare.setString(7, phonenumber.getText());
                prepare.setString(8, adress.getText());
                prepare.setDate(9, (Date.valueOf(dob.getValue())));
                prepare.setDate(10, (Date.valueOf(staringdate.getValue())));
                if(finishingdate.getValue()!=null) {
                    prepare.setDate(11, (Date.valueOf(finishingdate.getValue())));
                }
                prepare.setString(13,(String) Course.getSelectionModel().getSelectedItem());
                course k=null;
                for (course c : DBUtils.courseArrayList) {
                    if (Course.getSelectionModel().getSelectedItem().equals(c.getCourseName())) {
                        k = c;
                        break;

                    }
                }

                upcourse(k);

                prepare.setString(12, file_path);

                prepare.execute();
                System.out.println("ok12");
                clear();
                showData();
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
    StringBuilder getrespath(){
        StringBuilder resourcesPath = new StringBuilder(getClass().getResource("").getPath());
        //int n=resourcesPath.length();
        resourcesPath.deleteCharAt(0);
        for(int i=0;i<resourcesPath.length();i++){
            if(resourcesPath.charAt(i)=='%'){
                resourcesPath.replace(i,i+3," ");
            }
            if(resourcesPath.charAt(i)=='t'&&resourcesPath.charAt(i+1)=='a'&&resourcesPath.charAt(i+2)=='r'&& resourcesPath.charAt(i+3)=='g'&& resourcesPath.charAt(i+4)=='e'&& resourcesPath.charAt(i+5)=='t'&& resourcesPath.charAt(i+6)=='/'){
                resourcesPath.delete(i-1,resourcesPath.length());
                break;
            }
        }
        return resourcesPath;
    }
    public ObservableList<Students> datalist()
    {
        ObservableList<Students> datalist = FXCollections.observableArrayList();

        String sql;
        sql ="SELECT * FROM students";

        try {
            connect= DBUtils.connectDB(url);
            prepare = connect.prepareStatement(sql);
            result=prepare.executeQuery();


            while(result.next())
            {
                StringBuilder resourcesPath=getrespath();
                resourcesPath.append(result.getString("img"));
                Gender gm;
                if(result.getString("Gender").equals("MALE")){
                    gm= Gender.MALE;
                } else if (result.getString("Gender").equals("FEMALE")) {
                    gm=Gender.FEMALE;
                }
                else gm= Gender.OTHER;
                Students emp;


                course k=null;
                if(result.getString("courses")!=null) {
                    System.out.println(result.getString("courses"));
                    for (course c : DBUtils.courseArrayList) {
                        if (result.getString("courses").equals(c.getCourseName())) {
                            k = c;
                        }
                    }
                    //System.out.println(k.getCourseName()+ "1");

                }



                if(result.getDate("resign")!=null) {

                    emp = new Students(result.getString("Name"), gm,result.getString("phoneNo"),resourcesPath.toString(),
                            result.getString("email"),result.getDate("dob"),result.getString("adress"),
                            result.getInt("ID"),result.getString("institute"),k,result.getDate("jdate"),result.getDate("resign"));
                }
                else{
                    emp = new Students(result.getString("Name"), gm,result.getString("phoneNo"),resourcesPath.toString(),
                            result.getString("email"),result.getDate("dob"),result.getString("adress"),
                            result.getInt("ID"),result.getString("institute"),k,result.getDate("jdate"));
                }
                //System.out.println(emp.getCur_course().getCourseName());
                datalist.add(emp);
            }

        }catch (Exception e) {
            System.out.println("Students database error");
            e.printStackTrace();
        }
        finally
        {
            try
            {
                connect.close();
                result.close();
                prepare.close();
                if(statement!=null) {
                    statement.close();
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return datalist;
    }
    public void showData()
    {
        ObservableList<Students> showlist = datalist();
        t_id.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        t_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        t_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        t_institute.setCellValueFactory(new PropertyValueFactory<>("institute_name"));
        //curcourse_t.setCellValueFactory(new PropertyValueFactory<>("cur_course"));
        t_jdate.setCellValueFactory(new PropertyValueFactory<>("Starting_date"));
        t_fdate.setCellValueFactory(new PropertyValueFactory<>("Finishing_date"));
        t_phoneno.setCellValueFactory(new PropertyValueFactory<>("mobile_no"));
        t_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        t_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        t_adress.setCellValueFactory(new PropertyValueFactory<>("Adress"));
//        curcourse_t.setCellFactory(column -> new TableCell<educator, course>() {
//            @Override
//            protected void updateItem(course item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty || item == null) {
//                    setText(null);
//                } else {
//                    setText(item.getCourseName());
//                }
//            }
//        });
        //curcourse_t.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        t_course.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Students, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Students, String> param) {
                return new SimpleStringProperty(param.getValue().getCourse().getCourseName());
            }
        });
//        course_t.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<educator, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<educator, String> cellData) {
//                educator educator = cellData.getValue();
//                List<course> courses = educator.getCourses();
//                String courseNames = "";
//
//                // Concatenate the names of all courses for this educator
//                for (course course : courses) {
//                    if (!courseNames.isEmpty()) {
//                        courseNames += ", ";
//                    }
//                    courseNames += course.getCourseName();
//                }
//
//                return new SimpleStringProperty(courseNames);
//            }
//        });
//
//        specialization_t.setCellValueFactory(cellData -> {
//            ObservableList<String> specializationList = FXCollections.observableArrayList(cellData.getValue().getSpecializations());
//            return Bindings.createStringBinding(() -> String.join(", ", specializationList));
//        });




        table_view.setItems(showlist);

    }
    public void showData(ObservableList<Students> showlist)
    {

        t_id.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        t_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        t_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        t_institute.setCellValueFactory(new PropertyValueFactory<>("institute_name"));
        //curcourse_t.setCellValueFactory(new PropertyValueFactory<>("cur_course"));
        t_jdate.setCellValueFactory(new PropertyValueFactory<>("Starting_date"));
        t_fdate.setCellValueFactory(new PropertyValueFactory<>("Finishing_date"));
        t_phoneno.setCellValueFactory(new PropertyValueFactory<>("mobile_no"));
        t_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        t_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        t_adress.setCellValueFactory(new PropertyValueFactory<>("Adress"));
//        curcourse_t.setCellFactory(column -> new TableCell<educator, course>() {
//            @Override
//            protected void updateItem(course item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty || item == null) {
//                    setText(null);
//                } else {
//                    setText(item.getCourseName());
//                }
//            }
//        });
        //curcourse_t.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        t_course.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Students, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Students, String> param) {
                return new SimpleStringProperty(param.getValue().getCourse().getCourseName());
            }
        });
//        course_t.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<educator, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<educator, String> cellData) {
//                educator educator = cellData.getValue();
//                List<course> courses = educator.getCourses();
//                String courseNames = "";
//
//                // Concatenate the names of all courses for this educator
//                for (course course : courses) {
//                    if (!courseNames.isEmpty()) {
//                        courseNames += ", ";
//                    }
//                    courseNames += course.getCourseName();
//                }
//
//                return new SimpleStringProperty(courseNames);
//            }
//        });
//
//        specialization_t.setCellValueFactory(cellData -> {
//            ObservableList<String> specializationList = FXCollections.observableArrayList(cellData.getValue().getSpecializations());
//            return Bindings.createStringBinding(() -> String.join(", ", specializationList));
//        });




        table_view.setItems(showlist);

    }
    @FXML
    void delete(ActionEvent event) {
        String sql = "DELETE from students WHERE `ID` ='" + Id.getText() + "'";

        try
        {
            connect=DBUtils.connectDB(url);
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("                                     Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("                     Are you sure you want to delete? ");

            Optional<ButtonType> buttontype= alert.showAndWait();
            if(buttontype.get()==ButtonType.OK)
            {
                statement=connect.createStatement();
                statement.executeUpdate(sql);
                run4(event);
            }
            showData();
            clear();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                connect.close();
                result.close();
                prepare.close();
                statement.close();

            }catch (Exception e)
            {

            }
        }

    }
    @FXML
    void selectData(MouseEvent event) {
        clear();
        Students employee = table_view.getSelectionModel().getSelectedItem();
        int no=table_view.getSelectionModel().getSelectedIndex();
        if((no-1)<-1)
        {
            return;
        }
        Id.setText(valueOf(employee.getStudentID()));
        name.setText(valueOf(employee.getName()));
        gender.setValue(valueOf(employee.getGender()));
        Course.setValue(valueOf(employee.getCourse().getCourseName()));
        adress.setText(valueOf(employee.getAdress()));
        if(employee.getEmail()!=null){
            email.setText(valueOf(employee.getEmail()));
        }

        temp.setVisible(false);
        phonenumber.setText(valueOf(employee.getMobile_no()));
        dob.setValue(LocalDate.parse(valueOf(employee.getDob())));
        staringdate.setValue(LocalDate.parse(valueOf(employee.getStarting_date())));
        if(employee.getFinishing_date()!=null){
            finishingdate.setValue(LocalDate.parse(valueOf(employee.getFinishing_date())));
        }
        else finishingdate.setValue(null);
        Institutename.setText(employee.getInstitute_name());
        st=employee.getCourse().getCourseName();



        System.out.println(employee.getPhoto());
        //file_path=employee.getPhoto();


        run3();
        run2();
        try {
            show.setImage(new Image("file:/"+employee.getPhoto()));
        }
        catch (Exception e){
            System.out.println(employee.getPhoto());
        }


    }

    private void upcourse(application.museum.People.course cor) {
        Integer cnt=cor.getStudencnt()+1;
        cor.setStudencnt(cnt);
        String sql = "UPDATE course SET `total`= '" + cnt  + "' WHERE Name = '" + cor.getCourseName() + "'";
        Connection con=null;
        PreparedStatement prep=null;
        Statement stat=null;
        ResultSet res=null;
        try
        {

            con = DBUtils.connectDB(url);
            stat = con.createStatement();
            stat.executeUpdate(sql);

        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("couse e vul");
        } finally
        {
            try
            {
                if(con!=null)
                    con.close();
                if(res!=null)
                    res.close();
                if(prep!=null)
                    prep.close();
                if(stat!=null)
                    stat.close();

            } catch (Exception e)

            {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void update_Crud(ActionEvent event) throws IOException {
        String sql;
        if(isimageChanged) {
            StringBuilder im = copyImageToResources(photo);
            for (int i = 0; i < im.length(); i++) {
                if (im.charAt(i) == '\\') {
                    im.setCharAt(i, '/');
                }
            }
            String file_path = im.toString();


            if (finishingdate.getValue() == null && email.getText().isEmpty()) {
                sql = "UPDATE students SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `institute` = '" + Institutename.getText()  + "', `courses` = '" + Course.getSelectionModel().getSelectedItem()+ "', `img` = '" + file_path+ "' WHERE Id = '" + Id.getText() + "'";
            } else if (finishingdate.getValue() == null) {
                sql = "UPDATE students SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `Email` = '" + email.getText() + "', `institute` = '" + Institutename.getText() + "', `courses` = '" + Course.getSelectionModel().getSelectedItem()+ "', `img` = '" + file_path+ "' WHERE Id = '" + Id.getText() + "'";
            } else if (email.getText().isEmpty()) {
                sql = "UPDATE students SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + java.sql.Date.valueOf(finishingdate.getValue()) + "', `institute` = '" + Institutename.getText()+ "', `courses` = '" + Course.getSelectionModel().getSelectedItem()+ "', `img` = '" + file_path+ "', `courses` = '" + "' WHERE Id = '" + Id.getText() + "'";
            } else {
                sql = "UPDATE students SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + java.sql.Date.valueOf(finishingdate.getValue()) + "', `Email` = '" + email.getText() + "', `institute` = '" + Institutename.getText() + "', `courses` = '" + Course.getSelectionModel().getSelectedItem()+ "', `img` = '" + file_path+ "' WHERE Id = '" + Id.getText() + "'";
            }
        }
        else {
            if (finishingdate.getValue() == null && email.getText().isEmpty()) {
                sql = "UPDATE students SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + "', `institute` = '" + Institutename.getText()  + "', `courses` = '" + Course.getSelectionModel().getSelectedItem()+ "' WHERE Id = '" + Id.getText() + "'";
            } else if (finishingdate.getValue() == null) {
                sql = "UPDATE students SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `Email` = '" + email.getText() + "', `institute` = '" + Institutename.getText() + "', `courses` = '" + Course.getSelectionModel().getSelectedItem()+ "' WHERE Id = '" + Id.getText() + "'";
            } else if (email.getText().isEmpty()) {
                sql = "UPDATE students SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + java.sql.Date.valueOf(finishingdate.getValue()) + "', `institute` = '" + Institutename.getText()+ "', `courses` = '" + Course.getSelectionModel().getSelectedItem()+ "', `courses` = '" + "' WHERE Id = '" + Id.getText() + "'";
            } else {
                sql = "UPDATE students SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + java.sql.Date.valueOf(finishingdate.getValue()) + "', `Email` = '" + email.getText() + "', `institute` = '" + Institutename.getText() + "', `courses` = '" + Course.getSelectionModel().getSelectedItem()+ "' WHERE Id = '" + Id.getText() + "'";
            }
        }


        try {
            connect= DBUtils.connectDB(url);
            if (Id.getText().isEmpty() | name.getText().isEmpty() | show.getImage() == null | Course.getSelectionModel().isEmpty() |
                    Institutename.getText().isEmpty() | dob.getValue() == null | staringdate.getValue() == null|gender.getSelectionModel().isEmpty()|
                    phonenumber.getText().isEmpty()|adress.getText().isEmpty()
            )
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            }

            else
            {
                statement=connect.createStatement();
                statement.executeUpdate(sql);
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("                                      Update Successfull!!!");
                alert.setHeaderText("       ");
                alert.setContentText("                             Successfully updated the data. ");
                alert.showAndWait();
                if(!st.equals(Course.getSelectionModel().getSelectedItem())){
                    course k=null;
                    for (course c : DBUtils.courseArrayList) {
                        if (Course.getSelectionModel().getSelectedItem().equals(c.getCourseName())) {
                            k = c;
                            break;

                        }
                    }

                    upcourse(k);
                }
                showData();
                clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                connect.close();
                result.close();
                prepare.close();
                statement.close();

            }catch (Exception e)
            {

            }
        }

    }

}
