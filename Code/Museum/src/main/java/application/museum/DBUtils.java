package application.museum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;


public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlfile, String title, String username){
        Parent root= null;
        if(username!=null){
            try{
                FXMLLoader loader =new FXMLLoader(DBUtils.class.getResource(fxmlfile));
                root =loader.load();
                DashboardSceneController scene2Controller = loader.getController();
                scene2Controller.displayName(username);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,1004,680);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
    public static void loginuser(ActionEvent event, String username, String password){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userpassword", "root","OOPsprojectv1#");
            preparedStatement =connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database!");
                Alert alert =  new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username or password is incorrect");
                alert.show();
                //return false;

            }
            else{
                while(resultSet.next()){
                    String reviewedpas = resultSet.getString("password");
                    if(reviewedpas.equals(password)){
                        //return true;
                        changeScene(event,"DashboardScene.fxml","Welcome!",username);
                    }
                    else{
                        System.out.println("password is incorrect!");
                        Alert alert= new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Username or Password is incorrect!");
                        alert.show();

                    }
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if(resultSet!=null){
                try{
                    resultSet.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try{
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            //return false;
        }
    }
}
