package UIHelpers;
import PGA.PGATourSuperstore;
import enums.Enums;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import staff.Staff;

public class EmployeesTab{
    private PGATourSuperstore pga;
    private Tab tab;
    public EmployeesTab(PGATourSuperstore pga)
    {
        this.pga = pga;
        this.tab = employeeTab();
    }

    private Tab employeeTab()
    {
        Tab employeesTab = new Tab("Employees");
        employeesTab.setClosable(false);
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
        ObservableList<Staff> observableListStaff = FXCollections.observableArrayList(pga.getStaffArrayList());
        employeesTable.setItems(observableListStaff);

        // Add the table to the layout
        employeesBox.getChildren().add(new Label("Add New:"));
        employeesBox.getChildren().add(addStaffButton(employeesTable));
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
                    ObservableList<Staff> observableListStaff = FXCollections.observableArrayList(pga.getStaffArrayList());
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


    private HBox addStaffButton(TableView<Staff> employeesTable) {
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
            // Add the new staff to the list and update the table view
            pga.addEmployee(nameInput.getText(),
                    typeInput.getValue(),
                    Double.parseDouble(payInput.getText())
            );

            employeesTable.setItems(FXCollections.observableArrayList(pga.getStaffArrayList()));
        });

        // Create a horizontal box to hold the text fields and add button
        HBox addBox = new HBox();
        addBox.setAlignment(Pos.CENTER_LEFT);
        addBox.setSpacing(10);
        addBox.getChildren().addAll(nameInput, typeInput, payInput, addButton);

        return addBox;
    }

    public Tab getInstance() { return this.tab; }
}
