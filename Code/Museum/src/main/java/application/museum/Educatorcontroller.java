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
import java.util.*;
import java.util.Date;

import static java.lang.String.valueOf;

public class Educatorcontroller implements Initializable
{
    @FXML
    private Button BOD;

    @FXML
    private ComboBox<String> Department;

    @FXML
    private Button GoBackButton;

    @FXML
    private TextField Id;

    @FXML
    private Button LogoutButton;

    @FXML
    private Text ProfileIcon;

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
    private TableColumn<educator, String> adress_t;

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
    private TableColumn<educator, String> course_t;

    @FXML
    private TextField courses;

    @FXML
    private Button curator;

    @FXML
    private ComboBox<String> curcourse;

    @FXML
    private TableColumn<application.museum.People.educator, String> curcourse_t;

    @FXML
    private Button delete;

    @FXML
    private TableColumn<educator, String> department_t;

    @FXML
    private TextField designation;

    @FXML
    private TableColumn<educator, String> designation_t;

    @FXML
    private Button developer;

    @FXML
    private DatePicker dob;

    @FXML
    private TableColumn<educator, Date> dob_t;

    @FXML
    private Button educator;

    @FXML
    private TextField email;

    @FXML
    private TableColumn<educator, String> email_t;

    @FXML
    private Button employee;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TableColumn<educator, String> gender_t;

    @FXML
    private Button home;

    @FXML
    private TableColumn<educator,Integer> id_t;

    @FXML
    private Button image;

    @FXML
    private TableColumn<educator, Date> jdate_t;

    @FXML
    private DatePicker joingdate;

    @FXML
    private DatePicker joingdate1;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<educator, String> name_t;

    @FXML
    private AnchorPane paneside;

    @FXML
    private TableColumn<educator, String> phoneno_t;

    @FXML
    private TextField phonenumber;

    private File photo;

    @FXML
    private Button photogallery;

    @FXML
    private TableColumn<educator, Date> resign_t;

    @FXML
    private AnchorPane scene2;

    @FXML
    private ImageView show;

    @FXML
    private TableColumn<educator, String> specialization_t;

    @FXML
    private TextField specializations;

    @FXML
    private Button student;

    @FXML
    private Text studentName;

    @FXML
    private TableView<educator> table_view;

    @FXML
    private ImageView temp;

    @FXML
    private Button tickets;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private Button update;
    @FXML
    private Button clear;


    private String url="jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\database.db";

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private boolean isimageChanged = false;

    static void pushtostack()
    {
        DBUtils.prevfxml.push("educator.fxml");
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

//    @FXML
//    void switchToHome(ActionEvent event)
//    {
//        DBUtils.prevfxml.push("educator.fxml");
//        DBUtils.changeScene(event, "DashboardScene.fxml", DBUtils.username);
//    }
//
//    @FXML
//    public void switchTODepartments(ActionEvent event) throws IOException
//    {
//        DBUtils.prevfxml.push("educator.fxml");
//        DBUtils.changeScene(event, "DepartmentsScene.fxml", false);
//    }

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
        DBUtils.prevfxml.push("educator.fxml");
        DBUtils.changeScene(event, "employee.fxml", false);
    }

    @FXML
    void switchTobod(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("educator.fxml");
        DBUtils.changeScene(event, "aboutus.fxml", false);
    }

    @FXML
    void switchTocurator(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("educator.fxml");
        DBUtils.changeScene(event, "curator.fxml", false);
    }

    @FXML
    void switchTodevloper(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("educator.fxml");
        DBUtils.changeScene(event, "developer.fxml", false);
    }

    @FXML
    void switchTostudents(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("educator.fxml");
        DBUtils.changeScene(event, "students.fxml", false);
    }

    @FXML
    void switchToadmins(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("educator.fxml");
        DBUtils.changeScene(event, "admins.fxml", false);
    }

//    @FXML
//    void switchToInventory(ActionEvent event) throws IOException
//    {
//        Educatorcontroller.pushtostack();
//        DBUtils.changeScene(event, "Inventory.fxml", false);
//    }
//
//    @FXML
//    void switchToGallery(ActionEvent event) throws IOException
//    {
//        Educatorcontroller.pushtostack();
//        DBUtils.changeScene(event, "PhotoGalleryScene.fxml", false);
//    }
//
//    @FXML
//    public void switchTotickets(ActionEvent event) throws IOException
//    {
//        Educatorcontroller.pushtostack();
//        DBUtils.changeScene(event, "Tickets.fxml", false);
//    }

    @FXML
    void run1(ActionEvent event)
    {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneside);
        slide.setToX(0);
        slide.play();

        paneside.setTranslateX(-155);

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
        slide.setToX(-155);
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
            NavigationHandler.HandleNavigation("educator.fxml", home, treeView, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);
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
    }
    public ArrayList<course> detalist()
    {
        ArrayList<course> datalist = new ArrayList<>();

        String sql;
        sql ="SELECT * FROM course";
        Connection con=null;
        PreparedStatement prep=null;
        Statement stat=null;
        ResultSet res=null;

        try {
            con= DBUtils.connectDB(url);
            prep = con.prepareStatement(sql);
            res=prep.executeQuery();


            while(res.next())
            {


                course emp;
                boolean fin;
                if(res.getString("courses")=="YES"){
                    fin=true;
                }
                else {
                    fin=false;
                }

                if(res.getDate("resign")==null && res.getString("teacher")==null) {


                    emp = new course(res.getString("Name"),res.getDate("jdate"),fin,Integer.valueOf(res.getString("total")));
                }
                else{
                    emp = new course(res.getString("Name"),res.getDate("jdate"),fin,Integer.valueOf(res.getString("total")));
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
                con.close();
                res.close();
                prep.close();
                if(stat!=null) {
                    stat.close();
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return datalist;
    }
    void getData(){
        DBUtils.courseArrayList.clear();
        DBUtils.courseArrayList=detalist();
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

        List<String> class_list = new ArrayList<>();
        for (String data : DBUtils.dept)
        {
            class_list.add(data);
        }
        ObservableList data_list_class = FXCollections.observableArrayList(class_list);
        Department.setItems(data_list_class);
        ArrayList<String> cors=new ArrayList<>();
        for(course c:DBUtils.courseArrayList){
            cors.add(c.getCourseName());
            System.out.println(c.getCourseName());
        }
        ObservableList cor = FXCollections.observableArrayList(cors);
        curcourse.setItems(cor);
    }
    @FXML
    public void clear()
    {
        Id.setText("");
        name.setText("");
        gender.getSelectionModel().clearSelection();
        curcourse.getSelectionModel().clearSelection();
        show.setImage(null);
        temp.setVisible(true);
        designation.setText("");
        email.setText("");
        phonenumber.setText("");
        adress.setText("");
        Department.getSelectionModel().clearSelection();
        joingdate.setValue(null);
        joingdate1.setValue(null);
        dob.setValue(null);
        photo = null;
        specializations.setText("");
        courses.setText("");
        //worktime.setText("");
        isimageChanged = false;

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
            if (resourcesPath.charAt(i) == 'm')
            {
                resourcesPath.delete(i + 1, resourcesPath.length());
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
    void insert(ActionEvent event)  {
        String sql="INSERT INTO educator VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        try {
            connect= DBUtils.connectDB(url);
            if(Id.getText().isEmpty() | name.getText().isEmpty()  | show.getImage()==null | phonenumber.getText().isEmpty() |
                    gender.getSelectionModel().isEmpty()|Department.getSelectionModel().isEmpty()|adress.getText().isEmpty() |
                    dob.getValue()==null | joingdate.getValue()==null|designation.getText().isEmpty()|specializations.getText().isEmpty() )
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            }
            else
            {
                StringBuilder im= copyImageToResources(photo);
                for(int i=0;i<im.length();i++){
                    if(im.charAt(i)=='\\'){
                        im.setCharAt(i,'/');
                    }
                }
                String files_path= im.toString();


                prepare=connect.prepareStatement(sql);
                prepare.setInt(1,Integer.parseInt(Id.getText()));
                prepare.setString(2,name.getText());
                prepare.setString(3,(String) gender.getSelectionModel().getSelectedItem());
                prepare.setString(4,designation.getText());
                prepare.setString(5,(String) Department.getSelectionModel().getSelectedItem());
                if(!email.getText().isEmpty()){
                    prepare.setString(6,email.getText());
                }
                prepare.setString(7,phonenumber.getText());
                prepare.setString(8,adress.getText());
                prepare.setDate(9,(java.sql.Date.valueOf(dob.getValue())));
                prepare.setDate(10,(java.sql.Date.valueOf(joingdate.getValue())));
                if(joingdate1.getValue()!=null) {
                    prepare.setDate(11, (java.sql.Date.valueOf(joingdate1.getValue())));
                }
                //prepare.setString(13, (String) gender.getSelectionModel().getSelectedItem());
                prepare.setString(12,files_path);
                prepare.setString(16,specializations.getText());
                prepare.setString(13,"9AM to 5PM");
                if(!curcourse.getSelectionModel().isEmpty()) {
                    prepare.setString(15,(String) curcourse.getSelectionModel().getSelectedItem());
                    course k=null;
                    for (course c : DBUtils.courseArrayList) {
                        if (curcourse.getSelectionModel().getSelectedItem().equals(c.getCourseName())) {
                            k = c;
                            break;

                        }
                    }
                    if(k!=null) {
                        if(k.getInstructor()!=null){
                            k.getInstructor().setCur_course(null);
                            upeducator(String.valueOf(k.getInstructor().getName()),"");
                        }

                    }
                    upcourse(k,String.valueOf(Id.getText()));

                }
                prepare.setString(14,courses.getText());
//
                prepare.execute();

                System.out.println("ok12");
                clear();
                showData();
                getData();

            }


        }catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                connect.close();
                if (result!=null) {
                    result.close();
                }
                prepare.close();
                statement.close();


            }catch (Exception e)
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
            if(resourcesPath.charAt(i)=='m'){
                resourcesPath.delete(i+1,resourcesPath.length());
                break;
            }
        }
        return resourcesPath;
    }
    public ObservableList<educator> datalist()
    {
        ObservableList<educator> datalist = FXCollections.observableArrayList();

        String sql;
        sql ="SELECT * FROM educator";

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
                educator emp;

                String projects=result.getString("spec");
                ArrayList<String> pro=new ArrayList<>();
                String s1[]=projects.split("\\s+");
                for(String s: s1){
                    pro.add(s);
                }
                String devs=result.getString("courses");
                System.out.println(devs);
                ArrayList<course> cour=new ArrayList<>();
               if(devs!=null) {
                   String s2[] = devs.split("\\s+");
                   for (String s : s2) {
                       for (course c : DBUtils.courseArrayList) {
                           if (s.equals(c.getCourseName())) {
                               cour.add(c);
                           }
                       }
                   }
               }

                course k=null;
                if(result.getString("curcourse")!=null) {
                    System.out.println(result.getString("curcourse"));
                    for (course c : DBUtils.courseArrayList) {
                        if (result.getString("curcourse").equals(c.getCourseName())) {
                            k = c;
                            cour.add(c);
                        }
                    }
                    //System.out.println(k.getCourseName()+ "1");

                }



                if(result.getDate("resign")!=null) {

                    emp = new educator(result.getString("Name"), gm,result.getString("phoneNo"),resourcesPath.toString(),
                            result.getString("email"),result.getDate("dob"),result.getString("adress"),
                            result.getInt("ID"),result.getString("Department"),result.getString("designation"),
                            result.getString("worktime"),result.getDate("jdate"),result.getDate("resign"),pro,cour,k);
                }
                else{
                    emp = new educator(result.getString("Name"), gm,result.getString("phoneNo"),resourcesPath.toString(),
                            result.getString("email"),result.getDate("dob"),result.getString("adress"),
                            result.getInt("ID"),result.getString("Department"),result.getString("designation"),
                            result.getString("worktime"),result.getDate("jdate"),pro,cour,k);
                }
                System.out.println(emp.getCur_course().getCourseName());
                datalist.add(emp);
            }

        }catch (Exception e) {
            System.out.println("username database error");
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
        ObservableList<educator> showlist = datalist();
        id_t.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        name_t.setCellValueFactory(new PropertyValueFactory<>("name"));
        gender_t.setCellValueFactory(new PropertyValueFactory<>("gender"));
        department_t.setCellValueFactory(new PropertyValueFactory<>("department"));
        designation_t.setCellValueFactory(new PropertyValueFactory<>("Designation"));
        //curcourse_t.setCellValueFactory(new PropertyValueFactory<>("cur_course"));
        jdate_t.setCellValueFactory(new PropertyValueFactory<>("JoiningDate"));
        resign_t.setCellValueFactory(new PropertyValueFactory<>("resigningDate"));
        phoneno_t.setCellValueFactory(new PropertyValueFactory<>("mobile_no"));
        email_t.setCellValueFactory(new PropertyValueFactory<>("email"));
        dob_t.setCellValueFactory(new PropertyValueFactory<>("dob"));
        adress_t.setCellValueFactory(new PropertyValueFactory<>("Adress"));
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
        curcourse_t.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<educator, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<educator, String> param) {
                return new SimpleStringProperty(param.getValue().getCur_course().getCourseName());
            }
        });
        course_t.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<educator, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<educator, String> cellData) {
                educator educator = cellData.getValue();
                List<course> courses = educator.getCourses();
                String courseNames = "";

                // Concatenate the names of all courses for this educator
                for (course course : courses) {
                    if (!courseNames.isEmpty()) {
                        courseNames += ", ";
                    }
                    courseNames += course.getCourseName();
                }

                return new SimpleStringProperty(courseNames);
            }
        });

        specialization_t.setCellValueFactory(cellData -> {
            ObservableList<String> specializationList = FXCollections.observableArrayList(cellData.getValue().getSpecializations());
            return Bindings.createStringBinding(() -> String.join(", ", specializationList));
        });




        table_view.setItems(showlist);

    }
    void upcourse(course c, String Id){
        String sql = "UPDATE course SET `teacher`= '" + Id  + "' WHERE Name = '" + c.getCourseName() + "'";
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
    void upeducator(String c, String name){
        String sql = "UPDATE educator SET `curcourse`= '" + name  + "' WHERE ID = '" + c + "'";
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


            if (joingdate1.getValue() == null && email.getText().isEmpty()) {
                sql = "UPDATE educator SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `curcourse` = '" + curcourse.getSelectionModel().getSelectedItem()+ "', `courses` = '" +courses.getText() + "', `img` = '" + file_path + "' WHERE Id = '" + Id.getText() + "'";
            } else if (joingdate1.getValue() == null) {
                sql = "UPDATE educator SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `Email` = '" + email.getText() + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `curcourse` = '" + curcourse.getSelectionModel().getSelectedItem()+ "', `courses` = '" + courses.getText() + "', `img` = '" + file_path+ "', `spec` = '" + specializations.getText() + "' WHERE Id = '" + Id.getText() + "'";
            } else if (email.getText().isEmpty()) {
                sql = "UPDATE educator SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + java.sql.Date.valueOf(joingdate1.getValue()) + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `curcourse` = '" + curcourse.getSelectionModel().getSelectedItem()+ "', `courses` = '" + courses.getText()+ "', `spec` = '" + specializations.getText() + "', `img` = '" + file_path + "' WHERE Id = '" + Id.getText() + "'";
            } else {
                sql = "UPDATE educator SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + java.sql.Date.valueOf(joingdate1.getValue()) + "', `Email` = '" + email.getText() + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `curcourse` = '" + curcourse.getSelectionModel().getSelectedItem()+ "', `courses` = '" + courses.getText()+ "', `spec` = '" + specializations.getText() + "', `img` = '" + file_path + "' WHERE Id = '" + Id.getText() + "'";
            }
        }
        else {
            if (joingdate1.getValue() == null && email.getText().isEmpty()) {
                sql = "UPDATE educator SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `courses` = '" + courses.getText()+ "', `curcourse` = '" + curcourse.getSelectionModel().getSelectedItem()+ "', `spec` = '" + specializations.getText() + "' WHERE Id = '" + Id.getText() + "'";
            } else if (joingdate1.getValue() == null) {
                sql = "UPDATE educator SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `Email` = '" + email.getText() + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `curcourse` = '" + curcourse.getSelectionModel().getSelectedItem()+ "', `courses` = '" + courses.getText()+ "', `spec` = '" + specializations.getText() + "' WHERE Id = '" + Id.getText() + "'";
            } else if (email.getText().isEmpty()) {
                sql = "UPDATE educator SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + java.sql.Date.valueOf(joingdate1.getValue()) + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `curcourse` = '" + curcourse.getSelectionModel().getSelectedItem()+ "', `courses` = '" + courses.getText()+ "', `spec` = '" + specializations.getText() + "' WHERE Id = '" + Id.getText() + "'";
            } else {
                sql = "UPDATE educator SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + java.sql.Date.valueOf(joingdate1.getValue()) + "', `Email` = '" + email.getText() + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `curcourse` = '" + curcourse.getSelectionModel().getSelectedItem()+ "', `courses` = '" + courses.getText()+ "', `spec` = '" + specializations.getText()  + "' WHERE Id = '" + Id.getText() + "'";
            }
        }


        try {
            connect= DBUtils.connectDB(url);
            if(Id.getText().isEmpty() | name.getText().isEmpty()  | show.getImage()==null | phonenumber.getText().isEmpty() |
                    gender.getSelectionModel().isEmpty()|Department.getSelectionModel().isEmpty()|adress.getText().isEmpty() |
                    dob.getValue()==null | joingdate.getValue()==null|designation.getText().isEmpty()|curcourse.getSelectionModel().isEmpty() )
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
                course k=null;
                for (course c : DBUtils.courseArrayList) {
                    if (curcourse.getSelectionModel().getSelectedItem().equals(c.getCourseName())) {
                        k = c;
                        break;

                    }
                }
                if(k!=null) {
                    if(k.getInstructor()!=null){
                        k.getInstructor().setCur_course(null);
                        upeducator(String.valueOf(k.getInstructor().getName()),"");
                    }

                }
                upcourse(k,String.valueOf(Id.getText()));
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
    @FXML
    void delete(ActionEvent event) {
        String sql = "DELETE from educator WHERE `ID` ='" + "1" + "'";

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
        educator employee = table_view.getSelectionModel().getSelectedItem();
        int no=table_view.getSelectionModel().getSelectedIndex();
        if((no-1)<-1)
        {
            return;
        }
        Id.setText(valueOf(employee.getEmployee_id()));
        name.setText(valueOf(employee.getName()));
        gender.setValue(valueOf(employee.getGender()));
        Department.setValue(valueOf(employee.getDepartment()));
        designation.setText(valueOf(employee.getDesignation()));
        adress.setText(valueOf(employee.getAdress()));
        if(employee.getEmail()!=null){
            email.setText(valueOf(employee.getEmail()));
        }

        temp.setVisible(false);
        curcourse.setValue(employee.getCur_course().getCourseName());
        phonenumber.setText(valueOf(employee.getMobile_no()));
        dob.setValue(LocalDate.parse(valueOf(employee.getDob())));
        joingdate.setValue(LocalDate.parse(valueOf(employee.getJoiningDate())));
        if(employee.getResigningDate()!=null){
            joingdate1.setValue(LocalDate.parse(valueOf(employee.getResigningDate())));
        }
        else joingdate1.setValue(null);
        ArrayList<course> project=employee.getCourses();
        StringBuilder s=new StringBuilder();
        for (course s1:project){
            s.append(s1.getCourseName()+" ");
        }
        courses.setText(s.toString());
        ArrayList<String> devs=employee.getSpecializations();
        StringBuilder s1=new StringBuilder();
        for (String s2:devs){
            s1.append(s2+" ");
        }
        specializations.setText(s1.toString());


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


}
