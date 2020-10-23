import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.ListView;
import model.ClientUser;
import model.EmployeeType;
import model.workorder.WorkOrder;
import model.workorder.WorkOrderRepository;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestWorkOrderRepository {



    @Test
    public void testAddWorkOrder() {
        WorkOrderRepository.setPath("src/test/resources/work_order_test");
        WorkOrderRepository repo = WorkOrderRepository.getInstance();


        WorkOrder w1 = new WorkOrder(WorkOrder.WorkType.FINANCIAL_REQUEST, EmployeeType.HR_EMPLOYEE, EmployeeType.HR_EMPLOYEE, 5);
        repo.addWorkOrder(w1);

        assertTrue(repo.getWorkOrderList().contains(w1));

    }

    @Test
    public void testAddDuplicate() {
        WorkOrderRepository.setPath("src/test/resources/work_order_test");
        WorkOrderRepository repo = WorkOrderRepository.getInstance();

        WorkOrder w1 = new WorkOrder(WorkOrder.WorkType.FINANCIAL_REQUEST, EmployeeType.HR_EMPLOYEE, EmployeeType.HR_EMPLOYEE, 5);
        repo.addWorkOrder(w1);

        int length = repo.getWorkOrderList().size();
        repo.addWorkOrder(w1);
        assertEquals(length, repo.getWorkOrderList().size());

    }

    @Test
    public void testListViewSubscription() {

        ClientUser.getInstance().setEmployeeType(EmployeeType.DEBUG);

        ListView<WorkOrder> lw = new ListView<>();
        WorkOrderRepository.setPath("src/test/resources/work_order_test");
        WorkOrderRepository repo = WorkOrderRepository.getInstance();

        repo.addListViewSubscription(lw);


        WorkOrder w1 = new WorkOrder(WorkOrder.WorkType.FINANCIAL_REQUEST, EmployeeType.HR_EMPLOYEE, EmployeeType.HR_EMPLOYEE, 5);
        repo.addWorkOrder(w1);

        assertNotEquals(lw.getItems().size(), 0);

    }

    @Test
    public void testFileWrite() throws IOException {
        WorkOrderRepository.setPath("src/test/resources/work_order_test");
        WorkOrderRepository repo = WorkOrderRepository.getInstance();

        WorkOrder w1 = new WorkOrder(WorkOrder.WorkType.FINANCIAL_REQUEST, EmployeeType.HR_EMPLOYEE, EmployeeType.HR_EMPLOYEE, 5);
        repo.addWorkOrder(w1);

        ObjectMapper mapper = new ObjectMapper();

        File file = new File( "src/test/resources/work_order_test/work_orders.json");

        List<WorkOrder> workOrderList =  mapper.readValue(file, new TypeReference<List<WorkOrder>>() {});

        assertEquals(workOrderList.size(), repo.getWorkOrderList().size());

    }


}
