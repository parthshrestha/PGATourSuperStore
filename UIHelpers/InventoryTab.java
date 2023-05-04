package UIHelpers;

import PGA.PGATourSuperstore;
import enums.Enums;
import goods.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InventoryTab {

    private PGATourSuperstore pga;
    private Tab tab;

    public InventoryTab(PGATourSuperstore pga)
    {
        this.pga = pga;
        this.tab = inventoryTab();
    }

    private Tab inventoryTab()
    {
        Tab inventoryTab = new Tab("Inventory");
        VBox inventoryBox = new VBox();
        inventoryBox.setAlignment(Pos.TOP_LEFT);

        //Create all the columns and populate their cell values
        //Uses pga getters and setters to get values
        TableColumn<Item, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("upc"));

        TableColumn<Item, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));

        TableColumn<Item, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Item, Integer> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Item, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Create table view
        TableView<Item> inventoryTable = new TableView<>();

        //Add the columns to the table view
        inventoryTable.getColumns().addAll(idColumn, brandColumn, modelColumn, typeColumn,priceColumn);
        inventoryTable.getColumns().add(removeItemButton(inventoryTable));

        //Converts the items to observable list
        ObservableList<Item> observableListInventory = FXCollections.observableArrayList(pga.getInventory());
        inventoryTable.setItems(observableListInventory);

        // Add the table to the layout
        inventoryBox.getChildren().add(new Label("Add New:"));
        inventoryBox.getChildren().add(addItemButton(inventoryTable));
        inventoryBox.getChildren().add(new Label(""));
        inventoryBox.getChildren().addAll(inventoryTable);

        inventoryTab.setContent(inventoryBox);
        return inventoryTab;
    }

    private HBox addItemButton(TableView<Item> inventoryTable) {
        // Create text fields for each item field
        TextField brandInput = new TextField();
        brandInput.setPromptText("Brand");

        TextField modelInput = new TextField();
        modelInput.setPromptText("Model");

        ComboBox<Enums.Goods> typeInput = new ComboBox<>();
        typeInput.getItems().addAll(Enums.Goods.values());

        TextField priceInput = new TextField();
        priceInput.setPromptText("Price");

        // Create an add button to add the new item to the list
        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            // Add the new staff to the list and update the table view
            pga.addItem(brandInput.getText(),
                    modelInput.getText(),
                    typeInput.getValue(),
                    Double.parseDouble(priceInput.getText())
            );

            inventoryTable.setItems(FXCollections.observableArrayList(pga.getInventory()));
        });

        // Create a horizontal box to hold the text fields and add button
        HBox addBox = new HBox();
        addBox.setAlignment(Pos.CENTER_LEFT);
        addBox.setSpacing(10);
        addBox.getChildren().addAll(brandInput, modelInput, typeInput, priceInput, addButton);

        return addBox;
    }

    private TableColumn<Item,Void> removeItemButton(TableView<Item> inventoryTable)
    {
        // Create a table column for the remove button
        TableColumn<Item, Void> removeColumn = new TableColumn<>("Remove");
        removeColumn.setCellFactory(param -> new TableCell<>() {
            private final Button removeBtn = new Button("Remove");

            // Event handler for the remove button
            {
                removeBtn.setOnAction(event -> {
                    Item item = getTableView().getItems().get(getIndex());
                    pga.removeItem(item);
                    ObservableList<Item> observableListInventory = FXCollections.observableArrayList(pga.getInventory());
                    inventoryTable.setItems(observableListInventory);
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

    public Tab getInstance(){ return this.tab; }
}
