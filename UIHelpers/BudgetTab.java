package UIHelpers;

import PGA.PGATourSuperstore;
import enums.Enums;
import goods.Item;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BudgetTab {

    private PGATourSuperstore pga;
    private Tab tab;

    public BudgetTab(PGATourSuperstore pga)
    {
        this.pga = pga;
        this.tab = budgetTab();
    }

    private Tab budgetTab()
    {
        Tab budgetTab = new Tab("Budget");
        budgetTab.setClosable(false);

        HBox budgetBox = budgetBox();

        budgetTab.setContent(budgetBox);

        return budgetTab;
    }
    private HBox budgetBox() {

        Label budget = new Label("Budget: " + pga.getBudget());

        // Create text fields for each item field
        TextField moneyInput = new TextField();
        moneyInput.setPromptText("$Amount");

        ComboBox<String> typeInput = new ComboBox<>();
        typeInput.getItems().addAll("Add", "Remove");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            // Get the selected operation and the entered money value
            String operation = typeInput.getValue();
            double money = Double.parseDouble(moneyInput.getText());

            // Perform the selected operation on the money value
            if (operation.equals("Add")) {
                pga.income(money);
            } else {
                pga.income(-money);
            }
            budget.setText("Budget: " + pga.getBudget());
            // Clear the money input field
            moneyInput.clear();
        });

        // Create a horizontal box to hold the text fields and add button
        HBox addBox = new HBox();
        addBox.setAlignment(Pos.TOP_LEFT);
        addBox.setSpacing(10);
        addBox.setPadding(new Insets(15, 50, 15, 30));
        addBox.getChildren().add(budget);
        addBox.getChildren().addAll(moneyInput, typeInput, submitButton);

        return addBox;
    }

    public Tab getInstance(){ return this.tab; }
}
