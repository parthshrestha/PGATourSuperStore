package UIHelpers;

import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogTab extends Tab {

    private final String logFile;

    public LogTab(String name, String logFile) {
        setText(name);
        this.logFile = logFile;
        setContent(new VBox());

        // Read the log file and add each log to the list view
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            ListView<String> listView = new ListView<>();
            String line;
            while ((line = reader.readLine()) != null) {
                listView.getItems().add(line);
            }
            ((VBox) getContent()).getChildren().add(listView);
        } catch (IOException e) {
            System.err.println("Failed to read log file: " + logFile);
        }
    }

}
