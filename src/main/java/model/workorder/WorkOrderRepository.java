package model.workorder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.ListView;
import model.ClientUser;
import view.WorkOrderListItem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkOrderRepository {

    private static WorkOrderRepository current = null;

    private List<WorkOrder> workOrderList;
    private ListView<WorkOrder> listView = null;

    private final ObjectMapper mapper;
    private final File file;


    private WorkOrderRepository() {
        mapper = new ObjectMapper();

        file = new File("work_order/work_orders.json");
        if (file.exists())
        {
            try {
                workOrderList =  mapper.readValue(file, new TypeReference<List<WorkOrder>>() {});
                System.out.println("Loaded WorkOrderList");
                System.out.println(workOrderList);
            } catch (IOException e) {
                System.out.println("WorkOrder.json failed to load");
                workOrderList = new ArrayList<>();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            workOrderList = new ArrayList<>();
        }
        updateList();

    }

    private void updateList() {
        if (listView != null) {
            listView.getItems().clear();
            listView.getItems().addAll(workOrderList);
        }
    }

    public void addWorkOrder(WorkOrder order) {
        if (!workOrderList.contains(order))
            workOrderList.add(order);
        try {
            mapper.writeValue(file, workOrderList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateList();
        System.out.println("Create Work Order");
    }

    public void addListViewSubscription(ListView<WorkOrder> view) {
        this.listView = view;
        updateList();
    }


    public static WorkOrderRepository getInstance() {
        if (current == null)
            current = new WorkOrderRepository();
        return current;
    }

}
