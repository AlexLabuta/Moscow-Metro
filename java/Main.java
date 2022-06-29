import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String url = "https://www.moscowmap.ru/metro.html#lines";
        try {
            Document doc = Jsoup.connect(url).
                    userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.160 YaBrowser/22.5.1.985 Yowser/2.5 Safari/537.36").
                    maxBodySize(0).get();

            WriteAndReadJSON.writeJSONFiles(parserLine(doc), Station.stationToMap(parserStation(doc)));
            WriteAndReadJSON.printNumberOfStationsOnEachLineFromJSON();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Line> parserLine(Document doc) {
        List<Line> lines = new ArrayList<>();
        Elements nameLines = doc.getElementsByClass("js-metro-line");

        for (Element element : nameLines) {
            lines.add(new Line(element.attr("data-line"), element.text()));
        }
        return lines;
    }

    private static List<Station> parserStation(Document doc) {
        List<Station> stations = new ArrayList<>();
        Elements nameStation = doc.getElementsByClass("name");
        for (Element element : nameStation) {
            stations.add(new Station(element.text(), element.parent().parent().parent().attr("data-line")));
        }
        return stations;
    }
}
