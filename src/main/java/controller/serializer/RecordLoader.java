package controller.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.record.EventRecord;
import model.record.FinancialRequestRecord;
import model.record.Record;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class RecordLoader {

    private final String path;
    private final ObjectMapper mapper;

    public RecordLoader(String path) {
        this.path = path;

        mapper = new ObjectMapper();
    }

    private String getRecordFileName(Record r, Class<?> recordType) {
        return recordType.getName() + "-" + r.id + ".json";
    }

    public void saveRecord(Record record, Class<?> recordType) {
        String filename = path + "/" + recordType.getName() + "-" +record.id + ".json";
        File file  = new File(filename);
        try {
            mapper.writeValue(file, record);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Record> getAvailableRecords() {
        ArrayList<Record> recordList = new ArrayList<>();

        File recordDirectory = new File(path);
        for (File f : Objects.requireNonNull(recordDirectory.listFiles())) {
            System.out.println(f.getName());
            try {

                String filename = f.getName();
                Record r = null;

                if (filename.contains("EventRecord"))
                    r  = mapper.readValue(f, EventRecord.class);
                else if (filename.contains("FinancialRequestRecord"))
                    r  = mapper.readValue(f, FinancialRequestRecord.class);

                recordList.add(r);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(recordList);

        return recordList;
    }


}
