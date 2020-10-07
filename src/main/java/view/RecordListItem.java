package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import model.EventRecord;
import model.Record;

import java.io.IOException;

public class RecordListItem extends ListCell<Record> {

    @FXML
    private Label nameLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label creatorLabel;

    public RecordListItem() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../record_list_item.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Record item, boolean empty) {
        super.updateItem(item, empty);

        if(empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
            nameLabel.setText(item.name);
            dateLabel.setText(item.creationDate.toString());
            idLabel.setText(Integer.toString(item.id));
            creatorLabel.setText("Doktor Alban");

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
