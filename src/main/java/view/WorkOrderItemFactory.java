package view;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.workorder.WorkOrder;


public class WorkOrderItemFactory implements Callback<ListView<WorkOrder>, ListCell<WorkOrder>> {

    @Override
    public ListCell<WorkOrder> call(ListView<WorkOrder> param) {
        return new WorkOrderListItem();
    }


}
