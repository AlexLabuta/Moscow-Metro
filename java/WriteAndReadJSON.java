import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WriteAndReadJSON {
    private static final JSONObject JSON = new JSONObject();
    private static final String PATH = "src/main/result/map.json";

    static void writeJSONFiles(List<Line> lines, Map<String, List<String>> stationMap) {
        JSONArray linesJSON = new JSONArray();
        linesJSON.addAll(lines);
        JSONObject stationJSON = new JSONObject();
        stationJSON.putAll(stationMap);
        JSON.put("stations ", stationJSON);
        JSON.put("lines: ", linesJSON);

        try (FileWriter file = new FileWriter(PATH)) {
            file.write(JSON.toJSONString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getJSONFiles() {
        StringBuilder sb = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(PATH));
            lines.forEach(line -> sb.append(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    static void printNumberOfStationsOnEachLineFromJSON() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJSONFiles());
            JSONObject stationObject = (JSONObject) jsonData.get("stations ");
            Set keys = stationObject.keySet();
            for (Object key : keys) {
                JSONArray arr = (JSONArray) stationObject.get(key);
                System.out.println("На линии " + key + ": находиться " + arr.size() + " станций");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
