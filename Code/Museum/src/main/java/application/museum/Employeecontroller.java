package application.museum;

import application.museum.People.*;
import application.museum.People.Gender;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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

import static java.lang.String.*;

public class Employeecontroller implements Initializable {

    @FXML
    private TableColumn<Employee, String> Adress_t;

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
    private Button clear;

    @FXML
    private Button curator;

    @FXML
    private Button delete;

    @FXML
    private Button departments;

    @FXML
    private TableColumn<Employee, String> department_t;

    @FXML
    private TextField designation;


    @FXML
    private TableColumn<Employee, String> designation_t;

    @FXML
    private Button developer;

    @FXML
    private DatePicker dob;

    @FXML
    private TableColumn<Employee, java.util.Date> dob_t;

    @FXML
    private Button educator;

    @FXML
    private TextField email;

    @FXML
    private TableColumn<Employee, String> email_t;

    @FXML
    private Button employee;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TableColumn<Employee, String> gender_t;

    @FXML
    private Button home;

    @FXML
    private Button image;

    @FXML
    private TableColumn<Employee, Integer> id_t;

    @FXML
    private DatePicker joingdate;

    @FXML
    private TableColumn<Employee, java.util.Date> jdate_t;

    @FXML
    private DatePicker joingdate1;

    @FXML
    private TableColumn<Employee, java.util.Date> resi_t;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<Employee, String> name_t;

    @FXML
    private AnchorPane paneside;

    @FXML
    private TextField phonenumber;

    @FXML
    private TableColumn<Employee, String> phone_no_t;

    private File photo;


    @FXML
    private Button photogallery;

    @FXML
    private AnchorPane scene2;

    @FXML
    private ImageView show;

    @FXML
    private Button student;



    @FXML
    private Text studentName;

    @FXML
    private ImageView temp;


    @FXML
    private TableView<Employee> table_view;

    @FXML
    private Button tickets;

    @FXML
    private Button update;

    private String url="jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\employee.db";

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private boolean isimageChanged=false;

    @FXML
    private TextField worktime;

    @FXML
    private TableColumn<Employee, String> worktime_t;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        if(DBUtils.prevfxml.empty()){
            return;
        }
        String fxml=DBUtils.prevfxml.pop();
        //DBUtils.prevfxml.pop();
        System.out.println(fxml);
        if(fxml=="DashboardScene.fxml"){
            DBUtils.changeScene(event,fxml,DBUtils.username);
        }
        else {
            DBUtils.changeScene(event, fxml, true);
        }
    }

    @FXML
    void switchToHome(ActionEvent event) {
        DBUtils.prevfxml.push("employee.fxml");
        DBUtils.changeScene(event,"DashboardScene.fxml",DBUtils.username);
    }
    @FXML
    public void switchTODepartments(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("employee.fxml");
        DBUtils.changeScene(event, "DepartmentsScene.fxml", false);
    }
    @FXML
    void switchTobod(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("employee.fxml");
        DBUtils.changeScene(event,"aboutus.fxml",false);
    }

    @FXML
    void switchToGallery(ActionEvent event) throws IOException {
        Employeecontroller.pushtostack();
        DBUtils.changeScene(event,"PhotoGalleryScene.fxml",false);
    }
    @FXML
    void switchTostudents(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("employee.fxml");
        DBUtils.changeScene(event,"students.fxml",false);
    }
    @FXML
    void switchTocurato(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("employee.fxml");
        DBUtils.changeScene(event, "curator.fxml", false);
    }

    @FXML
    void switchToSceneOne(ActionEvent event) throws IOException {
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
    void switchTodevloper(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("employee.fxml");
        DBUtils.changeScene(event,"developer.fxml",false);
    }
    @FXML
    void switchToInventory(ActionEvent event) throws IOException {
        Employeecontroller.pushtostack();
        DBUtils.changeScene(event,"Inventory.fxml",false);
    }

    static void pushtostack(){
        DBUtils.prevfxml.push("employee.fxml");
    }
    @FXML
    public void switchTotickets(ActionEvent event) throws IOException
    {
        Employeecontroller.pushtostack();
        DBUtils.changeScene(event, "Tickets.fxml", false);
    }
    @FXML
    void switchToeducator(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("employee.fxml");
        DBUtils.changeScene(event,"educator.fxml",false);
    }
    @FXML
    void switchToadmins(ActionEvent event) throws IOException {
        DBUtils.prevfxml.push("employee.fxml");
        DBUtils.changeScene(event,"admins.fxml",false);
    }
    @FXML
    void run1(ActionEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneside);
        slide.setToX(0);
        slide.play();

        paneside.setTranslateX(-160);

        slide.setOnFinished((ActionEvent e)->{
            bar1.setVisible(false);
            bar2.setVisible(true);
        });

    }


    @FXML
    void run2(ActionEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneside);
        slide.setToX(-160);
        slide.play();

        paneside.setTranslateX(0);

        slide.setOnFinished((ActionEvent e)->{
            bar1.setVisible(true);
            bar2.setVisible(false);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Combo_box();
        showData();
        paneside.setTranslateX(0);
        bar2.setVisible(true);
        bar1.setVisible(false);
        bar3.setVisible(true);
        bar4.setVisible(false);
        scene2.setTranslateX(378);
    }
    @FXML
    void run3() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.6));
        slide.setNode(scene2);
        slide.setToX(0);
        slide.play();

        scene2.setTranslateX(378);

        slide.setOnFinished((ActionEvent e)->{
            bar3.setVisible(false);
            bar4.setVisible(true);
        });
    }

    @FXML
    void run4(ActionEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.6));
        slide.setNode(scene2);
        slide.setToX(378);
        slide.play();

        scene2.setTranslateX(0);

        slide.setOnFinished((ActionEvent e)->{
            bar4.setVisible(false);
            bar3.setVisible(true);
        });
    }
    @FXML
    void insertImage(ActionEvent event) throws IOException {
        FileChooser open= new FileChooser();
        Stage stage=(Stage) scene2.getScene().getWindow();
        File file=open.showOpenDialog(stage);
        isimageChanged=true;
        if(file!=null){
            StringBuilder im=new StringBuilder( file.toPath().toString());
            photo=file;
            for(int i=0;i<im.length();i++){
                if(im.charAt(i)=='\\'){
                    im.setCharAt(i,'/');
                }
            }
            String destination= im.toString();
            System.out.println(destination);
            Image img = new Image(destination);
            show.setImage(img);
            temp.setVisible(false);
        }
        else{
            System.out.println("Student pic is missing");
        }
    }
    public void Combo_box()
    {
        List<String> list=new ArrayList<>();
        for(Gender data:Gender.values())
        {
            list.add(data.toString());
        }
        ObservableList data_list= FXCollections.observableArrayList(list);
        gender.setItems(data_list);

        List<String> class_list=new ArrayList<>();
        for(String data:DBUtils.dept)
        {
            class_list.add(data);
        }
        ObservableList data_list_class= FXCollections.observableArrayList(class_list);
        Department.setItems(data_list_class);
    }
    @FXML
    public void clear()
    {
        Id.setText("");
        name.setText("");
        gender.getSelectionModel().clearSelection();
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
        photo=null;
        worktime.setText("");
        isimageChanged=false;

    }
    public StringBuilder copyImageToResources(File imageFile) throws IOException {
        // Determine the path to the resources folder
        StringBuilder resourcesPath = new StringBuilder(getClass().getResource("").getPath());
        //int n=resourcesPath.length();
        resourcesPath.deleteCharAt(0);
        for(int i=0;i<resourcesPath.length();i++){
            if(resourcesPath.charAt(i)=='/'){

                resourcesPath.replace(i,i+1,"\\\\");
            }
            if(resourcesPath.charAt(i)=='%'){
                resourcesPath.replace(i,i+3," ");
            }
            if(resourcesPath.charAt(i)=='m'){
                resourcesPath.delete(i+1,resourcesPath.length());
                break;
            }
        }
        System.out.println(resourcesPath);

        String name = imageFile.getName();
        String extension = "";

        int dotIndex = name.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < name.length() - 1) {
            extension = name.substring(dotIndex + 1);
        }

        String fileName = "image_" + System.currentTimeMillis() + "."+extension;
        StringBuilder destination= new StringBuilder("\\src\\main\\resources\\application\\museum\\StudentsPhotos\\"+fileName);

        // Copy the image file to the resources folder with the unique file name
        Path sourcePath = imageFile.toPath();
        Path destinationPath = Paths.get(  resourcesPath +"\\src\\main\\resources\\application\\museum\\StudentsPhotos\\"+fileName);
        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
//        direction=destinationPath.toString();
        return destination;
    }
    @FXML
    void insert(ActionEvent event)  {
        String sql="INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";


        try {
            connect= DBUtils.connectDB(url);
            if(Id.getText().isEmpty() | name.getText().isEmpty()  | show.getImage()==null | phonenumber.getText().isEmpty() |
                    gender.getSelectionModel().isEmpty()|Department.getSelectionModel().isEmpty()|adress.getText().isEmpty() |
                    dob.getValue()==null | joingdate.getValue()==null|designation.getText().isEmpty()|worktime.getText().isEmpty() )
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
                prepare.setDate(9,(Date.valueOf(dob.getValue())));
                prepare.setDate(10,(Date.valueOf(joingdate.getValue())));
                if(joingdate1.getValue()!=null) {
                    prepare.setDate(11, (Date.valueOf(joingdate1.getValue())));
                }
                //prepare.setString(13, (String) gender.getSelectionModel().getSelectedItem());
                prepare.setString(12,files_path);
                prepare.setString(13,worktime.getText());

                prepare.execute();
                showData();
                System.out.println("ok12");
                clear();
            }

        }catch (Exception e) {
            System.out.println(e);
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
    public ObservableList<Employee> datalist()
    {
        ObservableList<Employee> datalist = FXCollections.observableArrayList();

        String sql;
        sql ="SELECT * FROM Employee";

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
                Employee emp;

                if(result.getDate("resign")!=null) {

                    emp = new Employee(result.getString("Name"), gm,result.getString("phoneNo"),resourcesPath.toString(),
                            result.getString("email"),result.getDate("dob"),result.getString("adress"),
                            result.getInt("ID"),result.getString("Department"),result.getString("designation"),
                            result.getString("worktime"),result.getDate("jdate"),result.getDate("resign"));
                }
                else{
                    emp = new Employee(result.getString("Name"), gm,result.getString("phoneNo"),resourcesPath.toString(),
                            result.getString("email"),result.getDate("dob"),result.getString("adress"),
                            result.getInt("ID"),result.getString("Department"),result.getString("designation"),
                            result.getString("worktime"),result.getDate("jdate"));
                }
                datalist.add(emp);
            }

        }catch (Exception e) {
            System.out.println("username database error");
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
        ObservableList<Employee> showlist = datalist();
        id_t.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        name_t.setCellValueFactory(new PropertyValueFactory<>("name"));
        gender_t.setCellValueFactory(new PropertyValueFactory<>("gender"));
        department_t.setCellValueFactory(new PropertyValueFactory<>("department"));
        designation_t.setCellValueFactory(new PropertyValueFactory<>("Designation"));
        worktime_t.setCellValueFactory(new PropertyValueFactory<>("WorkTime"));
        jdate_t.setCellValueFactory(new PropertyValueFactory<>("JoiningDate"));
        resi_t.setCellValueFactory(new PropertyValueFactory<>("resigningDate"));
        phone_no_t.setCellValueFactory(new PropertyValueFactory<>("mobile_no"));
        email_t.setCellValueFactory(new PropertyValueFactory<>("email"));
        dob_t.setCellValueFactory(new PropertyValueFactory<>("dob"));
        Adress_t.setCellValueFactory(new PropertyValueFactory<>("Adress"));



        table_view.setItems(showlist);

    }
    @FXML
    void selectData(MouseEvent event) {
        clear();
        Employee employee = table_view.getSelectionModel().getSelectedItem();
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
        show.setImage(new Image(employee.getPhoto()));
        temp.setVisible(false);
        worktime.setText(valueOf(employee.getWorkTime()));
        phonenumber.setText(valueOf(employee.getMobile_no()));
        dob.setValue(LocalDate.parse(valueOf(employee.getDob())));
        joingdate.setValue(LocalDate.parse(valueOf(employee.getJoiningDate())));
        if(employee.getResigningDate()!=null){
            joingdate1.setValue(LocalDate.parse(valueOf(employee.getResigningDate())));
        }
        else joingdate1.setValue(null);
        System.out.println(employee.getPhoto());
        //file_path=employee.getPhoto();


        run3();

    }
    @FXML
    void delete(ActionEvent event) {
        String sql="DELETE from Employee WHERE `ID` ='"+Id.getText()+"'";

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
                sql = "UPDATE Employee SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `worktime` = '" + worktime.getText() + "', `img` = '" + file_path + "' WHERE Id = '" + Id.getText() + "'";
            } else if (joingdate1.getValue() == null) {
                sql = "UPDATE Employee SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `Email` = '" + email.getText() + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `worktime` = '" + worktime.getText() + "', `img` = '" + file_path + "' WHERE Id = '" + Id.getText() + "'";
            } else if (email.getText().isEmpty()) {
                sql = "UPDATE Employee SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + Date.valueOf(joingdate1.getValue()) + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `worktime` = '" + worktime.getText() + "', `img` = '" + file_path + "' WHERE Id = '" + Id.getText() + "'";
            } else {
                sql = "UPDATE Employee SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + Date.valueOf(joingdate1.getValue()) + "', `Email` = '" + email.getText() + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `worktime` = '" + worktime.getText() + "', `img` = '" + file_path + "' WHERE Id = '" + Id.getText() + "'";
            }
        }
        else {
            if (joingdate1.getValue() == null && email.getText().isEmpty()) {
                sql = "UPDATE Employee SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `worktime` = '" + worktime.getText() + "' WHERE Id = '" + Id.getText() + "'";
            } else if (joingdate1.getValue() == null) {
                sql = "UPDATE Employee SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `Email` = '" + email.getText() + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `worktime` = '" + worktime.getText() + "' WHERE Id = '" + Id.getText() + "'";
            } else if (email.getText().isEmpty()) {
                sql = "UPDATE Employee SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + Date.valueOf(joingdate1.getValue()) + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `worktime` = '" + worktime.getText() + "' WHERE Id = '" + Id.getText() + "'";
            } else {
                sql = "UPDATE Employee SET `phoneNo` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `resign` = '" + Date.valueOf(joingdate1.getValue()) + "', `Email` = '" + email.getText() + "', `designation` = '" + designation.getText() + "', `Department` = '" + Department.getSelectionModel().getSelectedItem() + "', `worktime` = '" + worktime.getText()  + "' WHERE Id = '" + Id.getText() + "'";
            }
        }


        try {
            connect= DBUtils.connectDB(url);
            if(Id.getText().isEmpty() | name.getText().isEmpty()  | show.getImage()==null | phonenumber.getText().isEmpty() |
                    gender.getSelectionModel().isEmpty()|Department.getSelectionModel().isEmpty()|adress.getText().isEmpty() |
                    dob.getValue()==null | joingdate.getValue()==null|designation.getText().isEmpty()|worktime.getText().isEmpty() )
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
