package subway.domain;


public class Vertex {
    private final Line line;
    private final Station startStation;
    private final Station endStation;
    private final int distance;
    private final int time;

    public Vertex(Line line, Station startStation, Station endStation, int distance, int time) {
        this.line = line;
        this.startStation = startStation;
        this.endStation = endStation;
        this.distance = distance;
        this.time = time;
    }

    public boolean equalsStartStation(Station startStation) {
        return this.startStation.equals(startStation);
    }

    public boolean equalsEndStation(Station endStation) {
        return this.endStation.equals(endStation);
    }

    public String getStartStationName() {
        return startStation.getName();
    }

    public String getEndStationName() {
        return endStation.getName();
    }

    public Station getStartStation() {
        return startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
