import com.fasterxml.jackson.databind.ObjectMapper;
import controller.serializer.RecordLoader;
import model.record.EventRecord;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRecordLoader {

    @Test
    public void saveRecordTest() throws IOException {
        RecordLoader loader = new RecordLoader("src/test/resources/record_test");

        String name = "TestABC123";
        Date date = new Date();
        int id = 321;
        String creator = "JUnitTest";

        EventRecord e1 = new EventRecord("TestABC123", date, id, creator);
        loader.saveRecord(e1, EventRecord.class);

        ObjectMapper mapper = new ObjectMapper();
        File recordFile = new File("src/test/resources/record_test/model.record.EventRecord-321.json");
        EventRecord e2 = mapper.readValue(recordFile, EventRecord.class);

        assertEquals(e1.name, e2.name);
        assertEquals(e1.creationDate, e2.creationDate);
        assertEquals(e1.id, e2.id);
        assertEquals(e1.creator, e2.creator);
    }

    @Test
    public void getRecordsTest() throws IOException {
        RecordLoader loader = new RecordLoader("src/test/resources/record_test");

        String name = "TestABC123";
        Date date = new Date();
        int id = 321;
        String creator = "JUnitTest";

        EventRecord e1 = new EventRecord("TestABC123", date, id, creator);
        loader.saveRecord(e1, EventRecord.class);

        EventRecord e2 = (EventRecord) loader.getAvailableRecords().get(0);

        assertEquals(e1.name, e2.name);
        assertEquals(e1.creationDate, e2.creationDate);
        assertEquals(e1.id, e2.id);
        assertEquals(e1.creator, e2.creator);
    }
}
