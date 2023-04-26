package application.museum;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavigationHandler
{
    public static void pushtostack(String prevFXML)
    {
        DBUtils.prevfxml.push(prevFXML);
    }

    @FXML
    public static void switchToHome(MouseEvent event, String prevFXML) throws IOException
    {
        DBUtils.prevfxml.push(prevFXML);
        DBUtils.changeScene(event, "DashboardScene.fxml", DBUtils.username);
    }

    @FXML
    public static void switchToPhotoGallery(MouseEvent event, String prevFXML) throws IOException
    {
        DBUtils.prevfxml.push(prevFXML);
        DBUtils.changeScene(event, "PhotoGalleryScene.fxml", false);
    }

    @FXML
    public static void switchToInventory(MouseEvent event, String prevFXML) throws IOException
    {
        DBUtils.prevfxml.push(prevFXML);
        DBUtils.changeScene(event, "Inventory.fxml", false);
    }

    @FXML
    public static void switchToAboutUs(MouseEvent event, String prevFXML) throws IOException
    {
        DBUtils.prevfxml.push(prevFXML);
        DBUtils.changeScene(event, "aboutus.fxml", false);
    }

    @FXML
    public static void switchTotickets(MouseEvent event, String prevFXML) throws IOException
    {
        DBUtils.prevfxml.push(prevFXML);
        DBUtils.changeScene(event, "Tickets.fxml", false);
    }

    private static void collapseTreeItems(TreeItem<String> item)
    {
        item.setExpanded(false);

        for (TreeItem<String> child : item.getChildren())
        {
            collapseTreeItems(child);
        }
    }

    public static void HandleNavigation(String prevFXML, Button homeButton, TreeView<String> treeView, Button photoGalleryButton, Button inventoryButton, Button aboutUsButton, Button ticketsButton, Button logoutButton, Button goBackButton) throws IOException
    {
        TreeView<String> tree = new TreeView<>();
        tree = treeView;

        TreeItem<String> rootDept = new TreeItem<>("Departments");

        TreeItem<String> branchDept1 = new TreeItem<>("Curatorial\nDepartments");
        TreeItem<String> branchDept2 = new TreeItem<>("Non-curatorial\nDepartments");
        TreeItem<String> leafDept4 = new TreeItem<>("Auditorium");
        TreeItem<String> leafDept5 = new TreeItem<>("Security");
        TreeItem<String> leafDept6 = new TreeItem<>("Public\nEducation");
        TreeItem<String> leafDept7 = new TreeItem<>("Photo\nGallery");

        branchDept2.getChildren().addAll(leafDept4, leafDept5, leafDept6, leafDept7);
        rootDept.getChildren().addAll(branchDept1, branchDept2);

        if (tree == null)
            System.out.println("NULL");

        try
        {
            tree.setRoot(rootDept);
            treeView.setOnMouseExited(event ->
            {
                TreeItem<String> root = treeView.getRoot();
                collapseTreeItems(root);
            });
        } catch (NullPointerException e)
        {
            e.printStackTrace();
        }

        treeView.setOnMouseClicked(event -> {
                    TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
                    if (item != null)
                    {
                        if (item.getValue().equals("Curatorial\nDepartments"))
                        {
                            DashboardSceneController.pushtostack();
                            try
                            {
                                DBUtils.changeScene(event, "CuratorialDeptScene.fxml", false);
                            } catch (IOException e)
                            {
                                throw new RuntimeException(e);
                            }
                        }
                        if (item.getValue().equals("Auditorium"))
                        {
                            DBUtils.prevfxml.push(prevFXML);
                            try
                            {
                                DBUtils.changeScene(event, "AuditoriumScene.fxml", false);
                            } catch (IOException e)
                            {
                                throw new RuntimeException(e);
                            }
                        }
                        if (item.getValue() == "Photo\nGallery")
                        {
                            DBUtils.prevfxml.push(prevFXML);
                            try
                            {
                                DBUtils.changeScene(event, "PhotoGalleryScene.fxml", false);
                            } catch (IOException e)
                            {
                                throw new RuntimeException(e);
                            }
                        }
                        if (item.getValue().equals("Public\nEducation"))
                        {
                            DBUtils.prevfxml.push(prevFXML);
                            try
                            {
                                DBUtils.changeScene(event, "PublicEducationScene.fxml", false);
                            } catch (IOException e)
                            {
                                throw new RuntimeException(e);
                            }
                        }
                        if (item.getValue() == "Security")
                        {
                            DBUtils.prevfxml.push(prevFXML);
                            try
                            {
                                DBUtils.changeScene(event, "SecurityScene.fxml", false);
                            } catch (IOException e)
                            {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );

        homeButton.setOnMouseClicked(event ->
        {
            try
            {
                switchToHome(event, prevFXML);
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        });

        photoGalleryButton.setOnMouseClicked(event ->
        {
            try
            {
                switchToPhotoGallery(event, prevFXML);
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        });

        inventoryButton.setOnMouseClicked(event ->
        {
            try
            {
                switchToInventory(event, prevFXML);
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        });

        aboutUsButton.setOnMouseClicked(event ->
        {
            try
            {
                switchToAboutUs(event, prevFXML);
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        });

        ticketsButton.setOnMouseClicked(event ->
        {
            try
            {
                switchTotickets(event, prevFXML);
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        });
    }
}

