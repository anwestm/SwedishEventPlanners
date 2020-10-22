package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import model.workorder.WorkOrder;

import java.io.IOException;

public class WorkOrderListItem extends ListCell<WorkOrder> {

    @FXML
    private Label nameLabel;

    @FXML
    private Label creatorLabel;

    @FXML
    private Label assignLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label statusLabel;


    public WorkOrderListItem() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../workorder_item.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(WorkOrder item, boolean empty) {
        super.updateItem(item, empty);

        if(empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
            nameLabel.setText(item.type.toString());
            creatorLabel.setText(item.initiator.toString());
            assignLabel.setText(item.worker.toString());
            idLabel.setText(Integer.toString(item.recordId));

            if (item.complete)
                statusLabel.setText("Complete");
            else
                statusLabel.setText("Not Finished");

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
