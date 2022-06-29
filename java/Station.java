import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Station {
    private final String nameStation;
    private final String numberLineOnStation;

    Station(String nameStation, String numberLineOnStation) {
        this.nameStation = nameStation;
        this.numberLineOnStation = numberLineOnStation;
    }

    public static Map<String, List<String>> stationToMap(List<Station> stations) {
        List<String> linesNumber = new ArrayList<>();
        Map<String, List<String>> stationMap = new HashMap<>();
        stations.forEach(station -> linesNumber.add(station.getNumberLineOnStation()));

        for (String number : linesNumber) {
            List<String> names = new ArrayList<>();
            for (Station station : stations) {
                if (number.equals(station.getNumberLineOnStation())) {
                    names.add(station.getNameStation());
                }
            }
            stationMap.put(number, names);
        }
        return stationMap;
    }

    public String getNameStation() {
        return nameStation;
    }

    public String getNumberLineOnStation() {
        return numberLineOnStation;
    }


    @Override
    public String toString() {
        return nameStation + numberLineOnStation;
    }
}


