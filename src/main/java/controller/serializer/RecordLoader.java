package controller.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.EventRecord;
import model.Record;

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

    public void saveRecord(EventRecord record, Class<?> recordType) {
        String filename = path + "/" + record.name + "-" +record.id + ".json";
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
                EventRecord r  = mapper.readValue(f, EventRecord.class);
                recordList.add(r);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(recordList);

        return recordList;
    }


}
