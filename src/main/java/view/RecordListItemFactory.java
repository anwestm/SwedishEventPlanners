package view;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.record.Record;


public class RecordListItemFactory implements Callback<ListView<Record>, ListCell<Record>> {

    @Override
    public ListCell<Record> call(ListView<Record> param) {
        return new RecordListItem();
    }


}
