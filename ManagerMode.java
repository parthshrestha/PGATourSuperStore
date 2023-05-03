import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.collections.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import goods.*;
import staff.*;

public class ManagerMode extends Application {

    private static PGATourSuperstore pga;

    @Override
    public void start(Stage primaryStage)
    {
        TabPane tabPane = new TabPane();

        Tab employeesTab = employeeTab();
        Tab inventoryTab = inventoryTab();

        tabPane.getTabs().addAll(employeesTab, inventoryTab);

        StackPane root = new StackPane(tabPane);

        Scene scene = new Scene(root, 860, 540);

        primaryStage.setTitle("Manager Mode");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Select the second tab
        tabPane.getSelectionModel().select(inventoryTab);
    }

    private Tab employeeTab()
    {
        Tab employeesTab = new Tab("Employees");
        VBox employees = new VBox();
        employees.setAlignment(Pos.TOP_LEFT);

        ListView<String> employeeList = new ListView<>(getEmployeeObservableList(pga.getStaff()));
        employees.getChildren().addAll(new Label("Employees"), employeeList);

        employeesTab.setContent(employees);
        return employeesTab;
    }

    private Tab inventoryTab()
    {
        Tab inventoryTab = new Tab("Inventory");
        VBox inventory = new VBox();
        inventory.setAlignment(Pos.TOP_LEFT);

        ListView<String> inventoryList = new ListView<>(getInventoryObservableList(pga.getInventory()));
        inventory.getChildren().addAll(new Label("Inventory Items"), inventoryList);

        // Add a button at the end of each list item
        inventoryList.setCellFactory(param -> new ListCell<String>() {
            private final Button btn = new Button("Remove");
            {
                // set action for button
                btn.setOnAction(event -> {
                    System.out.println("Button clicked for item: " + getItem());
                });
            }

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(item);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });

        inventoryTab.setContent(inventory);
        return inventoryTab;
    }

    //Converts the ArrayList of Items to an Observable list
    private ObservableList<String> getInventoryObservableList(ArrayList<Item> items)
    {
        ArrayList<String> itemStrings = new ArrayList<String>();
        for(Item item : items) {
            itemStrings.add(item.toString());
        }
        return FXCollections.observableArrayList(itemStrings);
    }

    private ObservableList<String> getEmployeeObservableList(ArrayList<Staff>[] staff)
    {
        ArrayList<String> staffStrings = new ArrayList<String>();
        for(int i = 0; i < staff.length; i++) {
            for(Staff s : staff[i]){
                staffStrings.add(s.toString());
            }
        }
        return FXCollections.observableArrayList(staffStrings);
    }

    public void openWindow(PGATourSuperstore pga)
    {
        this.pga = pga;
        launch();
    }
}
