package UIHelpers;

import PGA.PGATourSuperstore;
import buyer.Customer;
import goods.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class SoldInventoryTab extends Tab{

    private PGATourSuperstore pga;
    private Tab tab;

    public SoldInventoryTab(PGATourSuperstore pga)
    {
        this.pga = pga;

        setText("Sold Inventory");
        setClosable(false);
        VBox soldInventoryBox = new VBox();
        soldInventoryBox.setAlignment(Pos.TOP_LEFT);

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
        TableView<Item> soldInventoryTable = new TableView<>();

        //Add the columns to the table view
        soldInventoryTable.getColumns().addAll(idColumn, brandColumn, modelColumn, typeColumn,priceColumn);

        //Converts the items to observable list
        ObservableList<Item> observableListInventory = FXCollections.observableArrayList(pga.getSoldInventory());
        soldInventoryTable.setItems(observableListInventory);

        // Add the table to the layout
        soldInventoryBox.getChildren().add(new Label(""));
        soldInventoryBox.getChildren().addAll(soldInventoryTable);

        setContent(soldInventoryBox);
    }
}
