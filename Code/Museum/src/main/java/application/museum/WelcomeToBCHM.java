package application.museum;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeToBCHM extends Application
{
    StringBuilder getrespath()
    {
        StringBuilder resourcesPath = new StringBuilder(getClass().getResource("").getPath());
        //int n=resourcesPath.length();
        resourcesPath.deleteCharAt(0);
        //System.out.println(resourcesPath);
        for (int i = 0; i < resourcesPath.length(); i++)
        {
            if (resourcesPath.charAt(i) == '%')
            {
                resourcesPath.replace(i, i + 3, " ");
            }
            if (resourcesPath.charAt(i) == 't' && resourcesPath.charAt(i + 1) == 'a' && resourcesPath.charAt(i + 2) == 'r' && resourcesPath.charAt(i + 3) == 'g' && resourcesPath.charAt(i + 4) == 'e' && resourcesPath.charAt(i + 5) == 't' && resourcesPath.charAt(i + 6) == '/')
            {
                resourcesPath.delete(i - 1, resourcesPath.length());
                break;
            }
        }
        return resourcesPath;
    }
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("HelloScene.fxml"));
            Scene scene = new Scene(root, 1101, 680);
            String css = this.getClass().getResource("hello.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle("Bangladesh Computer History Museum Management System");
            StringBuilder st=new StringBuilder(getrespath().append("\\src\\main\\resources\\Assets\\SmallLogo.png"));
            Image img = new Image(st.toString());
            stage.getIcons().add(img);
            //stage.setFullScreen(true);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

