package application.museum;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class NavigationHandler
{
    public static void changeEnteringButtonColor(Button button)
    {
        button.setStyle("-fx-background-color: linear-gradient(to top,hsba(24, 100%, 29%, 1),hsba(36, 57%, 52%, 1)); -fx-text-fill: white;");
    }
    public static void changeExitingButtonColor(Button button)
    {
        button.setStyle("-fx-background-color: transparent; -fx-text-fill:#4B4B4B;");
    }

    public static void HandleNavigation(Button home, Button departments , Button photoGallery, Button articles, Button aboutUs, Button tickets, Button logout, Button goBack)
    {
        home.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeEnteringButtonColor(home);
            }
        });

        home.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeExitingButtonColor(home);
            }
        });
        home.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "DashboardScreen.fxml", "HOME", null);
            }
        });
        departments.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeEnteringButtonColor(departments);
            }
        });

        departments.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeExitingButtonColor(departments);
            }
        });
        departments.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "DashboardScreen.fxml", "HOME", null);
            }
        });
        photoGallery.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeEnteringButtonColor(photoGallery);
            }
        });

        photoGallery.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeExitingButtonColor(photoGallery);
            }
        });
        photoGallery.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "DashboardScreen.fxml", "HOME", null);
            }
        });
        articles.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeEnteringButtonColor(articles);
            }
        });

        articles.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeExitingButtonColor(articles);
            }
        });
        articles.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "DashboardScreen.fxml", "HOME", null);
            }
        });
        aboutUs.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeEnteringButtonColor(aboutUs);
            }
        });

        aboutUs.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeExitingButtonColor(aboutUs);
            }
        });
        aboutUs.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "DashboardScreen.fxml", "HOME", null);
            }
        });
        tickets.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeEnteringButtonColor(tickets);
            }
        });

        tickets.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeExitingButtonColor(tickets);
            }
        });
        tickets.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "DashboardScreen.fxml", "HOME", null);
            }
        });
        logout.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeEnteringButtonColor(logout);
            }
        });

        logout.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeExitingButtonColor(logout);
            }
        });
        logout.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "DashboardScreen.fxml", "HOME", null);
            }
        });
        goBack.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeEnteringButtonColor(goBack);
            }
        });

        goBack.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                changeExitingButtonColor(goBack);
            }
        });
        goBack.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "LoginScene.fxml", "Login", null);
            }
        });
    }
}
