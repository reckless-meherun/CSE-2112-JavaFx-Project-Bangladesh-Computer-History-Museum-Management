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
    AnchorPane deptPane;
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
    public TreeView<String> treeView;

    @FXML
    private AnchorPane SceneTwo;
    private boolean rootClicked = false;

    public void displayName(String username)
    {
        loggedIn.setText("Hello " + username + " !");
    }

    public static void pushtostack(){
        DBUtils.prevfxml.push("DashboardScene.fxml.fxml");
    }
    @FXML
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
    @FXML
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
    public void switchToDashboard(ActionEvent event) throws IOException
    {
        //DBUtils.prevfxml.push("DashboardScene.fxml");
        DBUtils.changeScene(event, "DashboardScene.fxml", false);
    }
    @FXML
    public void switchTODepartments(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("DashboardScene.fxml");
        DBUtils.changeScene(event, "DepartmentsScene.fxml", false);
    }
    @FXML
    public void switchTOaboutUs(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("DashboardScene.fxml");
        DBUtils.changeScene(event, "aboutus.fxml", false);
    }
    @FXML
    public void switchTotickets(ActionEvent event) throws IOException
    {
        DBUtils.prevfxml.push("DashboardScene.fxml");
        DBUtils.changeScene(event, "Tickets.fxml", false);
    }

    @FXML
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
    @FXML
    void switchToInventory(ActionEvent event) throws IOException {
        DashboardSceneController.pushtostack();
        DBUtils.changeScene(event,"Inventory.fxml",false);
    }
    @FXML
    void switchToGallery(ActionEvent event) throws IOException {
        DashboardSceneController.pushtostack();
        DBUtils.changeScene(event,"PhotoGalleryScene.fxml",false);
    }

    public void selectDept()
    {

    }

    private void collapseTreeItems(TreeItem<String> item) {
        item.setExpanded(false);

        for (TreeItem<String> child : item.getChildren()) {
            collapseTreeItems(child);
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        NavigationHandler.HandleNavigation(home, departments, photogallery, articles, aboutus, tickets, LogoutButton, GoBackButton);

        //TreeView<String> tree = new TreeView<>();
        TreeView<String> tree = new TreeView<>();
        tree = this.treeView;

        TreeItem<String> rootDept = new TreeItem<>("Departments");

        TreeItem<String> branchDept1 = new TreeItem<>("Curatorial\nDepartments");
        TreeItem<String> branchDept2 = new TreeItem<>("Non-curatorial\nDepartments");
        TreeItem<String> leafDept1 = new TreeItem<>("Software");
        TreeItem<String> leafDept2 = new TreeItem<>("Hardware");
        TreeItem<String> leafDept3 = new TreeItem<>("Language");
        TreeItem<String> leafDept4 = new TreeItem<>("Auditorium");
        TreeItem<String> leafDept5 = new TreeItem<>("Security");
        TreeItem<String> leafDept6 = new TreeItem<>("Public\nEducation");

        branchDept1.getChildren().addAll(leafDept1, leafDept2, leafDept3);
        branchDept2.getChildren().addAll(leafDept4, leafDept5, leafDept6);
        rootDept.getChildren().addAll(branchDept1, branchDept2);

        if(tree == null)
            System.out.println("NULL");

        try
        {
            tree.setRoot(rootDept);
            treeView.setOnMouseExited(event ->
            {
                // Get the root item of the tree
                TreeItem<String> root = treeView.getRoot();

                // Collapse all items under the root
                collapseTreeItems(root);
            });
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }

        selectDept();
    }
}
