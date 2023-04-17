package application.museum;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Stack;

public class DBUtils
{
    public static String username;
    public static Stack<String> prevfxml=new Stack<>();
    public static void changeScene(ActionEvent event, String fxmlfile, String username)
    {
        Parent root = null;
        if (DBUtils.username != null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlfile));
                root = loader.load();
                DashboardSceneController scene2Controller = loader.getController();
                scene2Controller.displayName(DBUtils.username);

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1101, 680);
        stage.setScene(scene);
        //stage.setTitle(title);
        stage.show();
    }
    public static void changeScene(ActionEvent event, String fxml,boolean goback) throws IOException
    {
        Parent root = FXMLLoader.load(DBUtils.class.getResource(fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1101, 680);
        stage.setScene(scene);
        stage.show();
    }


    public static void loginuser(ActionEvent event, String username, String password)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Code\\Museum\\src\\main\\resources\\Database\\userpassword.db");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst())
            {
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username or password is incorrect");
                alert.show();
                //return false;

            } else
            {
                while (resultSet.next())
                {
                    String reviewedpas = resultSet.getString("password");
                    if (reviewedpas.equals(password))
                    {
                        //return true;
                        DBUtils.username=username;
                        DBUtils.prevfxml.push("LoginScene.fxml");
                        changeScene(event, "DashboardScene.fxml", username);
                    } else
                    {
                        System.out.println("password is incorrect!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Username or Password is incorrect!");
                        alert.show();

                    }
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (resultSet != null)
            {
                try
                {
                    resultSet.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if (connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            //return false;
        }
    }

    public static void changeScenceforMouseEvent(MouseEvent event, String fxmlFile, String Title, String UserName)
    {
        Parent root = null;
        if (UserName != null)
        {
        try
        {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            DashboardSceneController DashboardSceneController = loader.getController();
            DashboardSceneController.getClass();
        } catch (IOException e)
        {
            System.out.println(e.getStackTrace());
        }
        }
        else
        {
            try
            {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e)
            {
                System.out.println("Can't Load File");
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(Title);
        stage.setScene(new Scene(root, 1101, 680));
        stage.show();
    }
}
