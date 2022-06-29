import java.util.ArrayList;
import java.util.List;

public class Line {
    private String numberLine;
    private String nameLine;
    private List<Station> listStation;

    public Line(String numberLine, String nameLine) {
        this.numberLine = numberLine;
        this.nameLine = nameLine;
        listStation = new ArrayList<>();
    }

    public Line() {
    }

    @Override
    public String toString() {
        return "\n\t\t{" + "\n\t\t\t\"number\" : \"" + numberLine + "\"," + "\n\t\t\t\"name\" : \"" + nameLine + '\"' + "\n\t\t}";

    }
}
