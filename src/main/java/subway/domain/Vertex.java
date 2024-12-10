package subway.domain;

import java.util.List;

public class Vertex {
    private final Line line;
    private final List<Station> stations;
    private final int distance;
    private final int time;

    public Vertex(Line line, List<Station> stations, int distance, int time) {
        this.line = line;
        this.stations = stations;
        this.distance = distance;
        this.time = time;
    }

    public Line getLine() {
        return line;
    }

    public List<Station> getStations() {
        return stations;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
