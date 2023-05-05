import PGA.PGATourSuperstore;
import UIHelpers.*;
import enums.Enums;
import Activity.Ecommerce;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import goods.*;
import map.Map;
import searchAlgorithm.*;
import staff.*;

// Manager Mode is mostly inspired from this tutorial
// https://m.youtube.com/watch?v=Ope4icw6bVk
// The rest are mostly from searching around the internet, here are some
//https://www.geeksforgeeks.org/javafx-vbox-class/
//https://www.geeksforgeeks.org/javafx-hbox-class/
//https://www.geeksforgeeks.org/javafx-combobox-with-examples/
//https://www.tutorialspoint.com/javafx/layout_gridpane.htm
//https://stackoverflow.com/questions/65119396/javafx-how-to-make-a-button-to-display-and-hide-label
//https://www.geeksforgeeks.org/javafx-label/
//https://jenkov.com/tutorials/javafx/tableview.html
public class ManagerMode extends Application {
    private Label algorithmLabel;
    private static PGATourSuperstore pga;
    private static Simulation sim;
    private static Ecommerce ecommerce = new Ecommerce();
    Stage primstage;

    @Override
    public void start(Stage primaryStage)
    {
        TabPane tabPane = new TabPane();
        primstage = primaryStage;

        Tab budgetTab = new BudgetTab(pga).getInstance();
        Tab employeesTab = new EmployeesTab(pga).getInstance();
        Tab inventoryTab = new InventoryTab(pga).getInstance();
        Tab soldInventoryTab = new SoldInventoryTab(pga).getInstance();
        Tab virtualMapTab = virtualMapTab();
        Tab simulationTab = simulationTab();

        tabPane.getTabs().addAll(budgetTab, employeesTab, inventoryTab, soldInventoryTab, virtualMapTab, simulationTab);

        StackPane root = new StackPane(tabPane);

        Scene scene = new Scene(root, 1200, 540);

        primaryStage.setTitle("Manager Mode");
        primaryStage.setScene(scene);
        primaryStage.show();
//        primaryStage.close();

        // Select the second tab
        tabPane.getSelectionModel().select(employeesTab);
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
        currState.setAlgorithm(BFSSearchSingleton.getInstance().getBfsSearch());
        algorithmLabel.setText("BFS Search Currently");
    }
    private void toggleDFS(Ecommerce currState){
        currState.setAlgorithm(DFSSearchSingleton.getInstance().getDfsSearch());
        algorithmLabel.setText("DFS Search Currently");
    }
    private void toggleDijkstra(Ecommerce currState){
        currState.setAlgorithm(DijkstraSearchSingleton.getInstance().getDijkstraSearch());
        algorithmLabel.setText("Dijkstra Search Currently");
    }
    public void openWindow(PGATourSuperstore pga, Simulation sim)
    {
        this.pga = pga;
        this.sim = sim;
        launch();
    }

    private Tab simulationTab(){
        Tab simulationTab = new Tab("Simulation");
        simulationTab.setClosable(false);
        Label question = new Label("How many Days Would you want the Simulation to run?");
        TextField inputDay = new TextField();

        // numeric only textfield
        // http://www.java2s.com/example/java/javafx/require-the-javafx-text-field-to-contain-numeric-digits-only.html
        inputDay.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    inputDay.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        inputDay.setMaxWidth(50);

        // Buttom to start simulation
        Button startSim = new Button("Start Simulation!");
        startSim.setOnAction(e -> toggleStartSim(inputDay));

        VBox simulationInput = new VBox(30, question, inputDay, startSim);
        simulationInput.setAlignment(Pos.CENTER);

        simulationTab.setContent(simulationInput);
        return simulationTab;
    }

    public void toggleStartSim(TextField input){
        int temp = Integer.valueOf(input.getText());
        this.sim = new Simulation(new PGATourSuperstore("1"));
        this.sim.run(temp);
        this.pga = this.sim.getPga();
        start(primstage);
    }


}
