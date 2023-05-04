import enums.Enums;
import Activity.Ecommerce;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.collections.*;

//import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import goods.*;
import map.Map;
import searchAlgorithm.BFSSearch;
import searchAlgorithm.DFSSearch;
import searchAlgorithm.DijkstraSearch;
import staff.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManagerMode extends Application {
    private Label algorithmLabel;

    private static ArrayList<Item> inventory;
    private static ArrayList<Item> soldInventory;
    private static ArrayList<Staff> employees;

    private static PGATourSuperstore pga;
    private static Ecommerce ecommerce = new Ecommerce();

    @Override
    public void start(Stage primaryStage)
    {
        TabPane tabPane = new TabPane();

        Tab employeesTab = employeeTab();
        Tab inventoryTab = inventoryTab();
        Tab virtualMapTab = virtualMapTab();

        tabPane.getTabs().addAll(employeesTab, inventoryTab, virtualMapTab);

        StackPane root = new StackPane(tabPane);

        Scene scene = new Scene(root, 1200, 540);

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
        inventoryTab.setClosable(false);
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

    private Tab virtualMapTab()
    {
        Tab virtualMapTab = new Tab("Virtual Map");
        virtualMapTab.setClosable(false);
        Map currentMap = ecommerce.getMap(); // Initialize the map here

        // Draw the map from ecommerce here
        GridPane grid = new GridPane();
        for(int i = 0 ; i < 20; i++){
            for(int j = 0; j < 10; j++){
                Color temp = currentMap.getRightColor(currentMap.getValueCoordinate(i,j));
                Rectangle rect = new Rectangle(25,25, temp);
                rect.setStroke(Color.BLACK);
                grid.add(rect, i, j);
            }
        }
//
//        Rectangle temp = (Rectangle) grid.getChildren().get((1 * grid.getRowCount()) + 0);
//        temp.setFill(Color.GOLD);


        // Create three buttons
        Button bfsBTN = new Button("BFS Search");
        bfsBTN.setOnAction(e->toggleBFS(ecommerce));
        Button dfsBTN = new Button("DFS Search");
        dfsBTN.setOnAction(e->toggleDFS(ecommerce));
        Button dijkstraBTN = new Button("Dijkstra Algorithm");
        dijkstraBTN.setOnAction(e->toggleDijkstra(ecommerce));
        Button solveBTN = new Button("Solve!");
        solveBTN.setOnAction(e->toggleSolveButton(ecommerce, grid));

        // Label to Show Which Algorithm is Being Runned
        algorithmLabel = new Label("No Algorithm");
        VBox vboxLabel = new VBox(algorithmLabel);

        // Put Buttons Together
        VBox vboxButtons = new VBox(20, algorithmLabel, bfsBTN, dfsBTN, dijkstraBTN, solveBTN);
        vboxButtons.setAlignment(Pos.CENTER);

        Label textFieldGoals = new Label();
        textFieldGoals.setText("Here are the items as goals: \n");
        textFieldGoals.setPrefWidth(300);
        textFieldGoals.setPrefHeight(500);
        textFieldGoals.setAlignment(Pos.TOP_LEFT);
        textFieldGoals.setWrapText(true);

        VBox vboxGoals = new VBox(30, textFieldGoals);
        vboxGoals.setAlignment(Pos.CENTER);



        // Create List of items that can be picked
        ComboBox<Item> comboBox = new ComboBox<>();
        comboBox.setPrefWidth(100);
        ArrayList<Item> temporaryItems = pga.getInventory();
        for(Item x : temporaryItems){
            comboBox.getItems().add(x);
            comboBox.setValue(x);
        }

        // Test if they can detect items being chosen in ComboBox
        comboBox.setOnAction(event -> {
            Item selected = comboBox.getSelectionModel().getSelectedItem();
            System.out.println("Selected item: " + selected.getBrand() + selected.getModel());
        });

        // Goal button which would add into the list or set of goals
        Button addGoalBTN = new Button("Add Goal");
        addGoalBTN.setOnAction(e -> toggleAddGoal(comboBox, textFieldGoals, ecommerce));
        addGoalBTN.setPrefWidth(100);

        Button resetGoalBTN = new Button("Reset");
        resetGoalBTN.setOnAction(e -> toggleResetGoal(comboBox, textFieldGoals, ecommerce));
        resetGoalBTN.setPrefWidth(100);

        HBox hboxGoals = new HBox(80, comboBox, addGoalBTN, resetGoalBTN);

        VBox vboxGrid = new VBox(50, grid, hboxGoals);
        vboxGrid.setAlignment(Pos.CENTER);



        // Put Elements Together
        HBox hbox = new HBox(100, vboxButtons, vboxGrid, vboxGoals);
        hbox.setPadding(new Insets(20,20,20,20));

        StackPane centerPane = new StackPane(hbox);


        virtualMapTab.setContent(centerPane);

        return virtualMapTab;
    }

    private void toggleSolveButton(Ecommerce currState, GridPane currGrid){
        currState.findPaths();
        Map currMap = currState.getMap();
        for(int i = 0 ; i < 20; i++){
            for(int j = 0; j < 10; j++){
                Color temp = currMap.getRightColor(currMap.getValueCoordinate(i,j));
                Rectangle rect = (Rectangle) currGrid.getChildren().get((i*currGrid.getRowCount()) + j);
                rect.setFill(temp);
            }
        }
        currState.clearMap();

    }

    private void toggleAddGoal(ComboBox itemsCombo, Label goalsTextField, Ecommerce currState){
        Item chosen = (Item) itemsCombo.getSelectionModel().getSelectedItem();
        currState.addGoals(chosen);
        goalsTextField.setText(goalsTextField.getText() + chosen + "\n");
    }
    private void toggleResetGoal(ComboBox itemsCombo, Label goalsTextField, Ecommerce currState){
        currState.resetGoals();
        goalsTextField.setText("Here are the items as goals \n");
    }

    private void toggleBFS(Ecommerce currState){
        currState.setAlgorithm(new BFSSearch());
        algorithmLabel.setText("BFS Search Currently");
    }

    private void toggleDFS(Ecommerce currState){
        currState.setAlgorithm(new DFSSearch());
        algorithmLabel.setText("DFS Search Currently");
    }

    private void toggleDijkstra(Ecommerce currState){
        currState.setAlgorithm(new DijkstraSearch());
        algorithmLabel.setText("Dijkstra Search Currently");
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

    public void openWindow(PGATourSuperstore pga)
    {
        this.pga = pga;
        updateLists();
        launch();
    }

}
