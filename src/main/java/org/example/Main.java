package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.json.simple.JSONArray;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;



public class Main {
    public static void main(String[] args) throws IOException {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "src/main/resources/data.csv";
        List<Employee> list = parseCSV(columnMapping, fileName);
        String json = listToJson(list);
        writeString(json);

    }

    private static void writeString(String json) {
        try(FileWriter fw = new FileWriter("src/main/resources/data.json")) {
            fw.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String listToJson(List<Employee> list) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(list));
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        String json = gson.toJson(list, listType);
        return json;
    }

    private static List<Employee> parseCSV(String[] columnMapping, String fileName) throws FileNotFoundException {
        CSVReader csvReader = new CSVReader(new FileReader(fileName));
        ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Employee.class);
        strategy.setColumnMapping(columnMapping);
        CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
                .withMappingStrategy(strategy)
                .build();

        List<Employee> list = csv.parse();
        list.forEach(System.out::println);
        return list;
    }
}