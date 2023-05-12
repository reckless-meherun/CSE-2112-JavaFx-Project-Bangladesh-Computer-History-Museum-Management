package application.museum;

import application.museum.People.*;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class Aboutus implements Initializable
{

    private File photo=null;
    @FXML
    TreeView<String> treeView;
    @FXML
    private Button BOD;

    @FXML
    private Button GoBackButton;

    @FXML
    private TextField ID;

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
    private Button curator;

    @FXML
    private Button clear;

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
    private ComboBox<String> gender;

    @FXML
    private Text heading;

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
    private ComboBox<String> post;

    @FXML
    private AnchorPane scene2;

    @FXML
    private ImageView showimage;

    @FXML
    private ImageView showimage1;

    @FXML
    private Button student;

    @FXML
    private Text studentName;

    @FXML
    private Button tickets;

    @FXML
    private Button update;

    @FXML
    private Text heading1;

    @FXML
    private Text heading11;

    @FXML
    private Text heading12;

    @FXML
    private Text heading13;

    @FXML
    private Text heading14;

    @FXML
    private ImageView imageViewCDO;

    @FXML
    private ImageView imageViewCDO1;

    @FXML
    private ImageView imageViewCFO;

    @FXML
    private ImageView imageViewCFO1;

    @FXML
    private ImageView imageViewCIO;

    @FXML
    private ImageView imageViewCIO1;

    @FXML
    private ImageView imageViewPres;

    @FXML
    private ImageView imageViewVicePres;

    @FXML
    private ImageView imageViewVicePres1;



    @FXML
    private Text nameCDO;

    @FXML
    private Text nameCFO;

    @FXML
    private Text nameCIO;

    @FXML
    private Text namePres;

    @FXML
    private Text nameVicePres;


    @FXML
    private Button updateCDOButton;

    @FXML
    private Button updateCFOButton;

    @FXML
    private Button updateCIOButton;

    @FXML
    private Button updatePresButton;

    @FXML
    private Button updateVicePresButton;

    private String url = "jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\aboutus.db";

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    public ArrayList<BOD> worklist=new ArrayList<>();

    private boolean isimageChanged = false;

    @FXML
    private ImageView imageViewPres1;

    public static void pushtostack()
    {
        DBUtils.prevfxml.push("aboutus.fxml");
    }

    @FXML
    void switchToemployee(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("aboutus.fxml");
        DBUtils.changeScene(event, "employee.fxml", false);
    }

    @FXML
    void switchTocurator(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("aboutus.fxml");
        DBUtils.changeScene(event, "curator.fxml", false);
    }

    @FXML
    void switchTostudents(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("aboutus.fxml");
        DBUtils.changeScene(event, "students.fxml", false);
    }

    @FXML
    void switchTodevloper(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("aboutus.fxml");
        DBUtils.changeScene(event, "developer.fxml", false);
    }

    @FXML
    void switchToadmins(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("aboutus.fxml");
        DBUtils.changeScene(event, "admins.fxml", false);
    }

    @FXML
    void switchToeducator(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("aboutus.fxml");
        DBUtils.changeScene(event, "educator.fxml", false);
    }

    public void switchToSceneOne(ActionEvent event) throws IOException
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
            NavigationHandler.HandleNavigation("aboutus.fxml", home, treeView, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        Combo_box();
        showData();
        paneside.setTranslateX(0);
        bar2.setVisible(true);
        bar1.setVisible(false);
        bar3.setVisible(true);
        bar4.setVisible(false);
        scene2.setTranslateX(378);
        updatePresButton.setOnAction(e->{
            selectData(worklist.get(0));
        });
        updateVicePresButton.setOnAction(e->{
            selectData(worklist.get(1));
        });
        updateCIOButton.setOnAction(e->{
            selectData(worklist.get(2));
        });
        updateCFOButton.setOnAction(e->{
            selectData(worklist.get(3));
        });
        updateCDOButton.setOnAction(e->{
            selectData(worklist.get(4));
        });


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
    void run4()
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
    void clear()
    {
        ID.setText("");
        name.setText("");
        gender.getSelectionModel().clearSelection();
        post.getSelectionModel().clearSelection();
        email.setText("");
        phonenumber.setText("");
        adress.setText("");
        dob.setValue(null);
        showimage1.setVisible(true);
        showimage.setVisible(false);
        photo=null;
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
            showimage.setImage(img);
            showimage1.setVisible(false);
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
        ArrayList<String> posts=new ArrayList<>();

        for(Posts post1 : Posts.values()){
            posts.add(post1.toString());
        }

        ObservableList cor = FXCollections.observableArrayList(posts);
        post.setItems(cor);

//        List<String> class_list=new ArrayList<>();
//        for(String data:Combo_subject)
//        {
//            class_list.add(data);
//        }
//        ObservableList data_list_class= FXCollections.observableArrayList(class_list);
//        crud_subject.setItems(data_list_class);
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
        String sql = "INSERT INTO BOD VALUES (?,?,?,?,?,?,?,?,?)";


        try
        {
            connect = DBUtils.connectDB(url);
            if (ID.getText().isEmpty() | name.getText().isEmpty() | showimage.getImage() == null | post.getSelectionModel().isEmpty() |
                     dob.getValue() == null |gender.getSelectionModel().isEmpty()|
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
                prepare.setString(1, ID.getText());
                prepare.setString(2, name.getText());
                prepare.setString(3, (String)gender.getSelectionModel().getSelectedItem() );
                prepare.setString(4,(String)post.getSelectionModel().getSelectedItem());
                prepare.setString(5, email.getText());
                prepare.setString(6, phonenumber.getText());
                prepare.setString(7, adress.getText());
                prepare.setDate(8, (Date.valueOf(dob.getValue())));
                //prepare.setDate(10, (Date.valueOf(dob.getValue())));

                prepare.setString(9, file_path);

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
    @FXML
    void delete(ActionEvent event) {
        String sql = "DELETE from BOD WHERE `id` ='" + ID.getText() + "'";

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
                run4();
            }
            //showData();
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

    public ArrayList<application.museum.People.BOD> datalist()
    {
        ArrayList<BOD> datalist = new ArrayList<>();

        String sql;
        sql ="SELECT * FROM BOD";

        try {
            connect= DBUtils.connectDB(url);
            prepare = connect.prepareStatement(sql);
            result=prepare.executeQuery();


            while(result.next())
            {
                StringBuilder resourcesPath=getrespath();
                resourcesPath.append(result.getString("photo"));
                Gender gm;
                if(result.getString("Gender").equals("MALE")){
                    gm= Gender.MALE;
                } else if (result.getString("Gender").equals("FEMALE")) {
                    gm=Gender.FEMALE;
                }
                else gm= Gender.OTHER;
                Students emp;
                Posts p=null;
                for(Posts post:Posts.values()){
                    if(post.toString().equals(result.getString("post"))){
                        p=post;
                    }
                }
                BOD bod= new BOD(result.getString("name"),gm,result.getString("phone"),resourcesPath.toString(),result.getString("email"),result.getDate("dob"),result.getString("adress"),p,result.getInt("ID"));
                datalist.add(bod);
                System.out.println(bod.getName());
                System.out.println(bod.getDesignation());






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
        ArrayList<BOD> Datalist=datalist();
        worklist.clear();
        BOD president=null;
        for(int i=Datalist.size()-1;i>=0;i--){
            if(Datalist.get(i).getDesignation().toString().equals("President")){
                president=Datalist.get(i);
                System.out.println("1");
                break;
            }
        }
        worklist.add(president);
        //System.out.println(president);
        BOD vpres=null;
        for(int i=Datalist.size()-1;i>=0;i--){
            if(Datalist.get(i).getDesignation().toString().equals("Vice_President")){
                vpres=Datalist.get(i);
                break;
            }
        }
        worklist.add(vpres);
        BOD cio=null;
        for(int i=Datalist.size()-1;i>=0;i--){
            if(Datalist.get(i).getDesignation().toString().equals("Chief_Information_Officer")){
                cio=Datalist.get(i);
                break;
            }
        }
        worklist.add(cio);
        BOD cfo=null;
        for(int i=Datalist.size()-1;i>=0;i--){
            if(Datalist.get(i).getDesignation().toString().equals("Chief_Financial_Officer")){
                cfo=Datalist.get(i);
                break;
            }
        }
        worklist.add(cfo);
        BOD cdo=null;
        for(int i=Datalist.size()-1;i>=0;i--){
            if(Datalist.get(i).getDesignation().toString().equals("Chief_Development_Officer")){
                cdo=Datalist.get(i);
                break;
            }
        }
        worklist.add(cdo);
        if(president!=null){
            namePres.setText(president.getName());
            imageViewPres.setVisible(false);

            imageViewPres1.setVisible(true);
            try {
                imageViewPres1.setImage(new Image("file:/"+president.getPhoto()));
                System.out.println(president.getPhoto());
                System.out.println("11");
            }
            catch (Exception e){
                System.out.println(president.getPhoto());
            }

        }
        else{
            namePres.setText("name");
            imageViewPres1.setVisible(false);
            imageViewPres.setVisible(true);
        }
        if(vpres!=null){
            nameVicePres.setText(vpres.getName());
            imageViewVicePres.setVisible(false);

            imageViewVicePres1.setVisible(true);
            try {
                imageViewVicePres1.setImage(new Image("file:/"+vpres.getPhoto()));
                System.out.println(vpres.getPhoto());
            }
            catch (Exception e){
                System.out.println(vpres.getPhoto());
            }

        }
        else{
            nameVicePres.setText("name");
            imageViewVicePres1.setVisible(false);
            imageViewVicePres.setVisible(true);
        }
        if(cio!=null){
            nameCIO.setText(cio.getName());
            imageViewCIO.setVisible(false);


            try {
                imageViewCIO1.setImage(new Image("file:/"+cio.getPhoto()));
            }
            catch (Exception e){
                System.out.println(cio.getPhoto());
            }
            imageViewCIO1.setVisible(true);
        }
        else{
            nameCIO.setText("name");
            imageViewCIO1.setVisible(false);
            imageViewCIO.setVisible(true);
        }
        if(cfo!=null){
            nameCFO.setText(cfo.getName());
            imageViewCFO.setVisible(false);


            try {
                imageViewCFO1.setImage(new Image("file:/"+cfo.getPhoto()));
            }
            catch (Exception e){
                System.out.println(cfo.getPhoto());
            }
            imageViewCFO1.setVisible(true);
        }
        else{
            nameCFO.setText("name");
            imageViewCFO1.setVisible(false);
            imageViewCFO.setVisible(true);
        }
        if(cdo!=null){
            nameCDO.setText(cdo.getName());
            imageViewCDO.setVisible(false);


            try {
                imageViewCDO1.setImage(new Image("file:/"+cdo.getPhoto()));
            }
            catch (Exception e){
                System.out.println(cdo.getPhoto());
            }
            imageViewCDO1.setVisible(true);
        }
        else{
            nameCDO.setText("name");
            imageViewCDO1.setVisible(false);
            imageViewCDO.setVisible(true);
        }




    }
    public void selectData(BOD bod) {
        if (bod != null) {
            ID.setText(valueOf(bod.getId()));
            name.setText(bod.getName());
            gender.setValue(valueOf(bod.getGender()));
            post.setValue(valueOf(bod.getDesignation()));
            email.setText(bod.getEmail());
            phonenumber.setText(bod.getMobile_no());
            adress.setText(bod.getAdress());
            dob.setValue(LocalDate.parse(valueOf(bod.getDob())));
            run3();
            run2();
            showimage1.setVisible(false);
            showimage.setVisible(true);
            try {
                showimage.setImage(new Image("file:/" + bod.getPhoto()));
            } catch (Exception e) {
                System.out.println(bod.getPhoto());
            }

        }
    }
    @FXML
    void update_Crud(ActionEvent event) throws IOException
    {
        String sql;
        if (isimageChanged)
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

            sql = "UPDATE BOD SET `phone` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText() + "', `post` = '" + post.getSelectionModel().getSelectedItem() + "', `photo` = '" + file_path + "' WHERE Id = '" + ID.getText() + "'";

        } else
        {

            sql = "UPDATE BOD SET `phone` = '" + phonenumber.getText() + "', `adress` = '" + adress.getText()  + "', `Email` = '" + email.getText() + "', `post` = '" + post.getSelectionModel().getSelectedItem() + "' WHERE Id = '" + ID.getText() + "'";
        }


        try
        {
            connect = DBUtils.connectDB(url);
            if (ID.getText().isEmpty() | name.getText().isEmpty() | showimage.getImage() == null | phonenumber.getText().isEmpty() |
                    gender.getSelectionModel().isEmpty() | post.getSelectionModel().isEmpty() | adress.getText().isEmpty() |
                    dob.getValue() == null )
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
                run4();
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
