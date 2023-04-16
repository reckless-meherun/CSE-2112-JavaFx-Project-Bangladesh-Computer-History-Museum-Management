package application.museum;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
public class NavigationHandler
{
    public static void HandleNavigation(Button homeButton, Button departmentsButton , Button photoGalleryButton, Button articlesButton, Button aboutUsButton, Button ticketsButton, Button logoutButton, Button goBackButton)
    {
//        homeButton.setOnMouseEntered(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                //changeEnteringButtonColor(homeButton);
//            }
//        });

//        homeButton.setOnMouseExited(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                //changeExitingButtonColor(homeButton);
//            }
//        });
        homeButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "DashboardScreen.fxml", "HOME", null);
            }
        });
//        departmentsButton.setOnMouseEntered(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                //changeEnteringButtonColor(departmentsButton);
//            }
//        });
//
//        departmentsButton.setOnMouseExited(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                //changeExitingButtonColor(departmentsButton);
//            }
//        });
        departmentsButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "DashboardScreen.fxml", "HOME", null);
            }
        });
//        photoGalleryButton.setOnMouseEntered(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                //changeEnteringButtonColor(photoGalleryButton);
//            }
//        });
//
//        photoGalleryButton.setOnMouseExited(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//               // changeExitingButtonColor(photoGalleryButton);
//            }
//        });
        photoGalleryButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "DashboardScreen.fxml", "HOME", null);
            }
        });
//        articlesButton.setOnMouseEntered(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//              //  changeEnteringButtonColor(articlesButton);
//            }
//        });
//
//        articlesButton.setOnMouseExited(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//              //  changeExitingButtonColor(articlesButton);
//            }
//        });
        articlesButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "DashboardScreen.fxml", "HOME", null);
            }
        });
//        aboutUsButton.setOnMouseEntered(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                //changeEnteringButtonColor(aboutUsButton);
//            }
//        });
//
//        aboutUsButton.setOnMouseExited(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//               // changeExitingButtonColor(aboutUsButton);
//            }
//        });
        aboutUsButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "DashboardScreen.fxml", "HOME", null);
            }
        });
//        ticketsButton.setOnMouseEntered(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//              //  changeEnteringButtonColor(ticketsButton);
//            }
//        });
//
//        ticketsButton.setOnMouseExited(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//               // changeExitingButtonColor(ticketsButton);
//            }
//        });
        ticketsButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "DashboardScreen.fxml", "HOME", null);
            }
        });
//        logoutButton.setOnMouseEntered(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//                //changeEnteringButtonColor(logoutButton);
//            }
//        });
//
//        logoutButton.setOnMouseExited(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//              //  changeExitingButtonColor(logoutButton);
//            }
//        });
        logoutButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "DashboardScreen.fxml", "HOME", null);
            }
        });
//        goBackButton.setOnMouseEntered(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//              //  changeEnteringButtonColor(goBackButton);
//            }
//        });
//
//        goBackButton.setOnMouseExited(new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent event)
//            {
//               // changeExitingButtonColor(goBackButton);
//            }
//        });
        goBackButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                DBUtils.changeScenceforMouseEvent(mouseEvent, "LoginScene.fxml", "Login", null);
            }
        });
    }
}
