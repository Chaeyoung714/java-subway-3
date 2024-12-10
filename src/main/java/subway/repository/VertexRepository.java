package subway.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import subway.domain.Station;
import subway.domain.Vertex;

public class VertexRepository {
    private static final List<Vertex> vertexes = new ArrayList<>();

    public static List<Vertex> vertexes() {
        return Collections.unmodifiableList(vertexes);
    }

    public static void addVertex(Vertex vertex) {
        vertexes.add(vertex);
    }

    public static Vertex findByStations(Station startStation, Station endStation) {
        return vertexes.stream()
                .filter(v -> v.getStartStation().equals(startStation) && v.getEndStation().equals(endStation))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public static List<Vertex> findAllStationsFromStartStation(Station firstStation) {
        List<Vertex> availablePaths = new ArrayList<>();
        List<Station> visitedStations = new ArrayList<>();
        Queue<Station> startStations = new LinkedList();
        visitedStations.add(firstStation);
        startStations.add(firstStation);
        while (!startStations.isEmpty()) {
            Station startStation = startStations.poll();
            for (Vertex vertex : vertexes) {
                if (vertex.equalsStartStation(startStation)) {
                    updateAvailableStatus(availablePaths, visitedStations, startStations, vertex);
                }
            }
        }
        return availablePaths;
    }

    private static void updateAvailableStatus(List<Vertex> availablePaths, List<Station> visitedStations,
                                              Queue<Station> startStations, Vertex vertex) {
        if (visitedStations.contains(vertex.getEndStation())) {
            return;
        }
        startStations.add(vertex.getEndStation());
        visitedStations.add(vertex.getEndStation());
        availablePaths.add(vertex);
    }
}
