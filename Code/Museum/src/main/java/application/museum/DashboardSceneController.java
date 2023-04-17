package application.museum;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardSceneController implements Initializable
{
    @FXML
    Label loggedIn;
    @FXML
    private Button home;
    @FXML
    private Button departments;
    @FXML
    private Button photogallery;
    @FXML
    private Button articles;
    @FXML
    private Button aboutus;
    @FXML
    private Button tickets;
    @FXML
    private Button LogoutButton;
    @FXML
    private Button GoBackButton;
    @FXML
    private Text notice1;
    @FXML
    private Text notice2;
    @FXML
    private Text notice3;
    @FXML
    private Text notice4;
    @FXML
    private Text ProfileIcon;
    @FXML
    private TreeView treeView;


    @FXML
    private AnchorPane SceneTwo;
    private boolean rootClicked = false;

    public void displayName(String username)
    {
        loggedIn.setText("Hello " + username + " !");
    }

    public void logout(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Do you want to save?");

        if (alert.showAndWait().get() == ButtonType.OK)
        {
            Stage stage = (Stage) SceneTwo.getScene().getWindow();
            System.out.println("You have successfully logged out");
            stage.close();
        }
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

    public void switchTOaboutUs(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("DashboardScene.fxml");
        DBUtils.changeScene(event, "aboutus.fxml", false);
    }

    public void goBack(ActionEvent event) throws IOException
    {
        if (DBUtils.prevfxml.empty())
        {
            return;
        }
        String fxml = DBUtils.prevfxml.pop();
        //DBUtils.prevfxml.pop();
        System.out.println(fxml);
        if (fxml == "LoginScene.fxml")
        {
            DBUtils.prevfxml.push(fxml);
            this.switchToSceneOne(event);
        } else if (fxml == "DashboardScene.fxml")
        {
            DBUtils.changeScene(event, fxml, DBUtils.username);
        } else
        {
            DBUtils.changeScene(event, fxml, true);
        }
    }

    public void selectDept()
    {

    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        NavigationHandler.HandleNavigation(home, departments, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);
//        TreeItem<String> rootDept = new TreeItem<>("Departments");
//
//        TreeItem<String> branchDept1 = new TreeItem<>("Curatorial\nDepartments");
//        TreeItem<String> branchDept2 = new TreeItem<>("Non-curatorial\nDepartments");
//
//        TreeItem<String> leafDept1 = new TreeItem<>("Software");
//        TreeItem<String> leafDept2 = new TreeItem<>("Hardware");
//        TreeItem<String> leafDept3 = new TreeItem<>("Language");
//        TreeItem<String> leafDept4 = new TreeItem<>("Auditorium");
//        TreeItem<String> leafDept5 = new TreeItem<>("Security");
//        TreeItem<String> leafDept6 = new TreeItem<>("Public\nEducation");
//
//        branchDept1.getChildren().addAll(leafDept1, leafDept2, leafDept3);
//        branchDept2.getChildren().addAll(leafDept4, leafDept5, leafDept6);
//
//        rootDept.getChildren().addAll(branchDept1, branchDept2);
//        departmentsTreeButton.setRoot(rootDept);
//
        selectDept();
//        TreeItem<String> rootItem = new TreeItem<>("Departments");
//        rootItem.setExpanded(true);
//
//        TreeItem<String> curatorialItem = new TreeItem<>("Curatorial");
//        TreeItem<String> nonCuratorialItem = new TreeItem<>("Non-curatorial");
//
//        rootItem.getChildren().addAll(curatorialItem, nonCuratorialItem);
//        treeView.setRoot(rootItem);
//
//        // Add listener to root node to set flag when clicked
//        rootItem.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//            rootClicked = true;
//        });
//
//        // Add style to child nodes when root node is clicked and cursor is over them
//        treeView.setOnMouseMoved(event -> {
//            if (rootClicked && event.getTarget() instanceof Cell && event.getPickResult().getIntersectedNode() instanceof Text) {
//                Cell cell = (Cell) event.getTarget();
//                if (!cell.getStyleClass().contains("tree-root")) {
//                    cell.setStyle("-fx-background-color: #e6e6e6;");
//                }
//            }
//        });
//
//        // Remove style from child nodes when cursor moves away
//        treeView.setOnMouseExited(event -> {
//            if (rootClicked) {
//                treeView.getRoot().getChildren().forEach(child -> {
//                    Cell cell = (Cell) treeView.lookup(".tree-cell[index=" + treeView.getRow(child) + "]");
//                    cell.setStyle("");
//                });
//            }
//        });
    }
}
