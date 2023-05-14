package application.museum;

import application.museum.People.Gender;
import application.museum.People.Students;
import application.museum.People.course;
import application.museum.People.educator;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

import static java.lang.String.valueOf;

public class PeducationController implements Initializable {

    @FXML
    TreeView<String> treeView;
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
    private Button articles;

    @FXML
    private ComboBox<String> ceducator;

    @FXML
    private Button clear;

    @FXML
    private TextField cname;

    @FXML
    private TableColumn<course, String> cname_t;

    @FXML
    private ComboBox<String> coursestat;

    @FXML
    private TableColumn<course, Boolean> cstats_t;

    @FXML
    private Button delete;

    @FXML
    private Button departments;

    @FXML
    private DatePicker edate;

    @FXML
    private TableColumn<course, Date> edate_t;

    @FXML
    private TableColumn<course, String> educator_t;

    @FXML
    private Button educators;

    @FXML
    private Button home;

    @FXML
    private Button photogallery;

    @FXML
    private DatePicker sdate;

    @FXML
    private TableColumn<course, Date> stdate_t;

    @FXML
    private Text studentName;

    @FXML
    private Button students;

    @FXML
    private TableColumn<course, Integer> students_t;

    @FXML
    private TableView<course> table_view;

    @FXML
    private Button tickets;

    @FXML
    private TextField tstd;

    @FXML
    private Button update;

    @FXML
    private TextField stext;

    private String url="jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\database.db";

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    @FXML
    void switchTOStudents(ActionEvent event) throws IOException {
        PeducationController.pushTostack();
        DBUtils.changeScene(event,"students.fxml",false);
    }

    @FXML
    void switchTOeducators(ActionEvent event) throws IOException {
        PeducationController.pushTostack();
        DBUtils.changeScene(event,"educator.fxml",false);
    }


    static void pushTostack(){
        DBUtils.prevfxml.push("PublicEducationScene.fxml");
    }
//    @FXML
//    void SwitchToHome(ActionEvent event) {
//        PeducationController.pushTostack();
//        DBUtils.changeScene(event,"DashboardScene.fxml",DBUtils.username);
//    }
//    @FXML
//    void switchToGallery(ActionEvent event) throws IOException {
//        PeducationController.pushTostack();
//        DBUtils.changeScene(event,"PhotoGalleryScene.fxml",false);
//    }

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

//    @FXML
//    void logout(ActionEvent event) {
//
//    }

//    @FXML
//    void switchTOaboutUs(ActionEvent event) throws IOException {
//        PeducationController.pushTostack();
//        DBUtils.changeScene(event,"aboutus.fxml",false);
//    }
//    @FXML
//    public void switchTODepartments(ActionEvent event) throws IOException
//    {
//        PeducationController.pushTostack();
//        DBUtils.changeScene(event, "DepartmentsScene.fxml", false);
//    }

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

//    @FXML
//    void switchTotickets(ActionEvent event) throws IOException {
//        PeducationController.pushTostack();
//        DBUtils.changeScene(event,"Tickets.fxml",false);
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try
        {

            NavigationHandler.HandleNavigation("PublicEducationScene.fxml", home, treeView, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);
        } catch (IOException e)
        {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        getData();
        Combo_box();
        showData();
        stext.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Search();
            }
        });

    }
    public ArrayList<course> detalist1()
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
    public void Combo_box()
    {
        List<String> list=new ArrayList<>();
        list.add("YES");
        list.add("NO");
        ObservableList data_list= FXCollections.observableArrayList(list);
        coursestat.setItems(data_list);

        List<String> class_list=new ArrayList<>();
        for(educator data:DBUtils.educators)
        {
            class_list.add(data.getName());
        }
        ObservableList data_list_class= FXCollections.observableArrayList(class_list);
        ceducator.setItems(data_list_class);
    }
    @FXML
    public void clear()
    {
        cname.setText("");
        ceducator.getSelectionModel().clearSelection();
        coursestat.getSelectionModel().clearSelection();
        tstd.setText("");
        sdate.setValue(null);
        edate.setValue(null);

    }
    @FXML
    void insert(ActionEvent event)  {
        String sql="INSERT INTO course VALUES (?,?,?,?,?,?)";


        try {
            connect= DBUtils.connectDB(url);
            if(cname.getText().isEmpty() | coursestat.getSelectionModel().isEmpty()| sdate.getValue()==null   )
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            }
            else
            {
                prepare=connect.prepareStatement(sql);
                if(!tstd.getText().isEmpty()) {
                    prepare.setInt(1, Integer.parseInt(tstd.getText()));
                }
                else{
                    prepare.setInt(1,0);
                }
                prepare.setString(2,cname.getText());

                prepare.setDate(3,(java.sql.Date.valueOf(sdate.getValue())));
                if(edate.getValue()!=null) {
                    prepare.setDate(4, (java.sql.Date.valueOf(edate.getValue())));
                }
                //prepare.setString(13, (String) gender.getSelectionModel().getSelectedItem());
                if(ceducator.getSelectionModel().isEmpty()){
                    prepare.setString(5,"");
                }
                else{
                    educator k=null;
                    for(educator c: DBUtils.educators){
                        if(ceducator.getSelectionModel().getSelectedItem().equals(c.getName())){
                            k=c;
                            break;
                        }
                    }
                    prepare.setString(5, k.getEmployee_id().toString());
                    upeducator(k,cname.getText());
                }
                prepare.setString(6,(String) coursestat.getSelectionModel().getSelectedItem());

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
    void getData(){
        DBUtils.educators.clear();
        DBUtils.educators=detalist();
        DBUtils.courseArrayList.clear();
        DBUtils.courseArrayList=detalist1();

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
    void upeducator(educator c, String name){
        String sql = "UPDATE educator SET `curcourse`= '" + name  + "' WHERE ID = '" + c.getEmployee_id() + "'";
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

    public ArrayList<educator> detalist()
    {
        ArrayList<educator> datalist = new ArrayList<>();

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
                //System.out.println(emp.getCur_course().getCourseName());
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
    public ObservableList<course> datalist()
    {
        ObservableList<course> datalist = FXCollections.observableArrayList();

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

                if(result.getDate("resign")==null && result.getString("teacher")==null) {


                    emp = new course(result.getString("Name"),result.getDate("jdate"),fin,Integer.valueOf(result.getString("total")));
                } else if (result.getDate("resign")==null) {
                    educator k=null;
                    for(educator e:DBUtils.educators){
                        if(e.getEmployee_id().equals(Integer.valueOf(result.getString("teacher")))){
                            k=e;
                        }
                    }
                    emp = new course(result.getString("Name"),result.getDate("jdate"),k,fin,Integer.valueOf(result.getString("total")));
                } else{
                    educator k=null;
                    for(educator e:DBUtils.educators){
                        if(e.getEmployee_id().equals(Integer.valueOf(result.getString("teacher")))){
                            k=e;
                        }
                    }
                    emp = new course(result.getString("Name"),result.getDate("jdate"),result.getDate("resign"),k,fin,Integer.valueOf(result.getString("total")));
                }
                datalist.add(emp);
            }

        }catch (Exception e) {
            System.out.println("course database error");
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
        ProfileIcon.setText(String.valueOf(DBUtils.username.charAt(0)));
        ObservableList<course> showlist = datalist();
        cname_t.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        stdate_t.setCellValueFactory(new PropertyValueFactory<>("StartingDate"));
        edate_t.setCellValueFactory(new PropertyValueFactory<>("finishingDate"));
        //educator_t.setCellValueFactory(new PropertyValueFactory<>("instructor"));
//        educator_t.setCellFactory(column -> {
//            return new TableCell<course, educator>() {
//                @Override
//                protected void updateItem(educator educator, boolean empty) {
//                    super.updateItem(educator, empty);
//                    if (educator == null || empty) {
//                        setText(null);
//                    } else {
//                        setText(educator.getName());
//                    }
//                }
//            };
//        });
        educator_t.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<course, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<course, String> param) {
                return new SimpleStringProperty(param.getValue().getInstructor().getName());
            }
        });
        students_t.setCellValueFactory(new PropertyValueFactory<>("studencnt"));
        cstats_t.setCellValueFactory(new PropertyValueFactory<course,Boolean>("courseCompleted"));


        table_view.setItems(showlist);

    }
    public void showData(ObservableList<course> showlist)
    {
        cname_t.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        stdate_t.setCellValueFactory(new PropertyValueFactory<>("StartingDate"));
        edate_t.setCellValueFactory(new PropertyValueFactory<>("finishingDate"));
        //educator_t.setCellValueFactory(new PropertyValueFactory<>("instructor"));
//        educator_t.setCellFactory(column -> {
//            return new TableCell<course, educator>() {
//                @Override
//                protected void updateItem(educator educator, boolean empty) {
//                    super.updateItem(educator, empty);
//                    if (educator == null || empty) {
//                        setText(null);
//                    } else {
//                        setText(educator.getName());
//                    }
//                }
//            };
//        });
        educator_t.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<course, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<course, String> param) {
                return new SimpleStringProperty(param.getValue().getInstructor().getName());
            }
        });
        students_t.setCellValueFactory(new PropertyValueFactory<>("studencnt"));
        cstats_t.setCellValueFactory(new PropertyValueFactory<course,Boolean>("courseCompleted"));


        table_view.setItems(showlist);

    }

    @FXML
    void selectData(MouseEvent event) {
        clear();
        course cour = table_view.getSelectionModel().getSelectedItem();
        int no=table_view.getSelectionModel().getSelectedIndex();
        if((no-1)<-1)
        {
            return;
        }
        cname.setText(valueOf(cour.getCourseName()));
        tstd.setText(valueOf(cour.getStudencnt()));
        sdate.setValue(LocalDate.parse(valueOf(cour.getStartingDate())));
        if(cour.getCourseCompleted()){
            coursestat.setValue("YES");
        }
        else{
            coursestat.setValue("NO");
        }
        if(cour.getFinishingDate()!=null){
            edate.setValue(LocalDate.parse(valueOf(cour.getFinishingDate())));
        }
        if(cour.getInstructor()!=null){
            ceducator.setValue(cour.getInstructor().getName());
        }
    }
    @FXML
    void delete(ActionEvent event) {
        String sql="DELETE from course WHERE `Name` ='"+cname.getText()+"'";

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
    void update_Crud(ActionEvent event)
    {

        String sql;
        if(ceducator.getSelectionModel().isEmpty()){
            if(edate.getValue()!=null) {
                sql = "UPDATE course SET  `total` = '" + tstd.getText() + "', `courses` = '" + coursestat.getSelectionModel().getSelectedItem() + "', `resign` = '" + java.sql.Date.valueOf(edate.getValue()) + "' WHERE Name = '" + cname.getText() + "'";
            }
            else{
                sql = "UPDATE course SET  `total` = '" + tstd.getText() + "', `courses` = '" + coursestat.getSelectionModel().getSelectedItem()  + "' WHERE Name = '" + cname.getText() + "'";
            }
        }
        else{
            educator k=null;
            for(educator e:DBUtils.educators){
                if(e.getName().equals(ceducator.getSelectionModel().getSelectedItem())){
                    k=e;
                    break;
                }
            }
            upeducator(k,cname.getText());
            if(edate.getValue()!=null) {
                sql = "UPDATE course SET  `total` = '" + tstd.getText() + "', `courses` = '" + coursestat.getSelectionModel().getSelectedItem() + "', `resign` = '" + java.sql.Date.valueOf(edate.getValue()) + "', `teacher` = '" + k.getEmployee_id() + "' WHERE Name = '" + cname.getText() + "'";
            }
            else {
                sql = "UPDATE course SET  `total` = '" + tstd.getText() + "', `courses` = '" + coursestat.getSelectionModel().getSelectedItem() +  "', `teacher` = '" + k.getEmployee_id() + "' WHERE Name = '" + cname.getText() + "'";
            }
        }

        try
        {
            connect = DBUtils.connectDB(url);
            if (cname.getText().isEmpty() | ceducator.getSelectionModel().isEmpty() | sdate.getValue()==null|
                    tstd.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("                                     Error!!!!!");
                alert.setHeaderText("            Some fields are empty.  ");
                alert.setContentText("                             Please enter all blank fields. ");
                alert.showAndWait();
            }  else
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
    void Search() {
        String searchName = null;
        if (!stext.getText().isEmpty())
            searchName = stext.getText(); // the name you want to search for
        else {
            showData();
            return;
        }
        ObservableList<course> dev=datalist();
        ObservableList<course> dev1=FXCollections.observableArrayList();
        for(course d: dev){
            if(searchName.equals(d.getCourseName())){
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

}
