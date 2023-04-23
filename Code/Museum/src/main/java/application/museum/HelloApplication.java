package application.museum;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application
{
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
            Scene scene = new Scene(root,1101,680);
            String css = this.getClass().getResource("hello.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle("Bangladesh Computer History Museum Management System");
            //stage.setFullScreen(true);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
