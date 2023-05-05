package UIHelpers;

import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.Scanner;

public class LogTab extends Tab {

    private final String logFile;

    public LogTab(String name, String logFile) {
        setText(name);
        this.logFile = logFile;
        setContent(new VBox());

        ListView<String> listView = new ListView<>();

        try {
            File file = new File(logFile);
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                listView.getItems().add(line);
            }

            myReader.close();
            ((VBox) getContent()).getChildren().add(listView);

        } catch (FileNotFoundException e) {
            System.out.println("Could not read log file.");
            e.printStackTrace();
        }
    }
}
