import enums.Enums;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.collections.*;

import java.util.ArrayList;
import goods.*;
import staff.*;

public class ManagerMode extends Application {

    private static PGATourSuperstore pga;

    private static ArrayList<Item> inventory;
    private static ArrayList<Item> soldInventory;
    private static ArrayList<Staff> employees;

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
        VBox employeesBox = new VBox();
        employeesBox.setAlignment(Pos.TOP_LEFT);

        //Create all the columns and populate their cell values
        //Uses pga getters and setters to get values
        TableColumn<Staff, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Staff, String> idColumn = new TableColumn<>("Staff ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("staffId"));

        TableColumn<Staff, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Staff, Integer> daysColumn = new TableColumn<>("Days Worked");
        daysColumn.setCellValueFactory(new PropertyValueFactory<>("daysWorked"));

        TableColumn<Staff, Integer> payColumn = new TableColumn<>("Pay Rate");
        payColumn.setCellValueFactory(new PropertyValueFactory<>("payRate"));

        TableColumn<Staff, Double> balanceColumn = new TableColumn<>("Balance");
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

        //Create table view
        TableView<Staff> employeesTable = new TableView<>();

        //Add the columns to the table view
        employeesTable.getColumns().addAll(idColumn, nameColumn, typeColumn, daysColumn,payColumn, balanceColumn);
        employeesTable.getColumns().add(removeEmployeeButton(employeesTable));

        //Converts the employees to observable list
        ObservableList<Staff> observableListStaff = FXCollections.observableArrayList(employees);
        employeesTable.setItems(observableListStaff);

        // Add the table to the layout
        employeesBox.getChildren().add(new Label("Add New:"));
        employeesBox.getChildren().add(addStaff(employeesTable));
        employeesBox.getChildren().add(new Label(""));
        employeesBox.getChildren().addAll(employeesTable);

        employeesTab.setContent(employeesBox);
        return employeesTab;
    }

    private TableColumn<Staff, Void> removeEmployeeButton(TableView<Staff> employeesTable)
    {
        // Create a table column for the remove button
        TableColumn<Staff, Void> removeColumn = new TableColumn<>("Remove");
        removeColumn.setCellFactory(param -> new TableCell<>() {
            private final Button removeBtn = new Button("Remove");

            // Event handler for the remove button
            {
                removeBtn.setOnAction(event -> {
                    Staff staff = getTableView().getItems().get(getIndex());
                    pga.removeEmployee(staff, staff.getType().ordinal());
                    updateLists();
                    ObservableList<Staff> observableListStaff = FXCollections.observableArrayList(employees);
                    employeesTable.setItems(observableListStaff);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(removeBtn);
                }
            }
        });

        return removeColumn;
    }

    private HBox addStaff(TableView<Staff> employeesTable) {
        // Create text fields for each staff field
        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");

        ComboBox<Enums.StaffType> typeInput = new ComboBox<>();
        typeInput.getItems().addAll(Enums.StaffType.values());

        TextField payInput = new TextField();
        payInput.setPromptText("Pay Rate");

        // Create an add button to add the new staff to the list
        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            // Create a new staff with the input values

            // Add the new staff to the list and update the table view
            pga.addEmployee(nameInput.getText(),
                    typeInput.getValue(),
                    Double.parseDouble(payInput.getText())
            );

            updateLists();
            employeesTable.setItems(FXCollections.observableArrayList(employees));
        });

        // Create a horizontal box to hold the text fields and add button
        HBox addBox = new HBox();
        addBox.setAlignment(Pos.CENTER_LEFT);
        addBox.setSpacing(10);
        addBox.getChildren().addAll(nameInput, typeInput, payInput, addButton);

        return addBox;
    }

    private Tab inventoryTab()
    {
        Tab inventoryTab = new Tab("Inventory");
        VBox inventory = new VBox();
        inventory.setAlignment(Pos.TOP_LEFT);

        ListView<String> inventoryList = new ListView<>(getInventoryObservableList());
        inventory.getChildren().addAll(new Label("Inventory Items"), inventoryList);

        // Add a button at the end of each list item
        inventoryList.setCellFactory(param -> new ListCell<String>() {

            private final Button btn = new Button("Remove");
            {
                // set action for button
                btn.setOnAction(event -> {
                    //Remove item from inventory
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
    private ObservableList<String> getInventoryObservableList()
    {
        ArrayList<String> itemStrings = new ArrayList<String>();
        for(Item item : inventory) {
            itemStrings.add(item.toString());
        }
        return FXCollections.observableArrayList(itemStrings);
    }

    private ObservableList<Staff> getEmployeeObservableList()
    {
        return FXCollections.observableArrayList(employees);
    }

    private void updateLists()
    {
        this.inventory = pga.getInventory();
        this.soldInventory = pga.getSoldInventory();

        ArrayList<Staff>[] old = pga.getStaff();
        ArrayList<Staff> staff = new ArrayList<>();
        for(int i = 0; i < old.length; i++){
            for(Staff s : old[i]){
                staff.add(s);
            }
        }
        this.employees = staff;
    }

    public void openWindow(PGATourSuperstore pga)
    {
        this.pga = pga;
        updateLists();
        launch();
    }
}
