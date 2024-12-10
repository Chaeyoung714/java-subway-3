package subway.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.Station;
import subway.domain.Vertex;
import subway.dto.EstimationDto;
import subway.repository.StationRepository;
import subway.repository.VertexRepository;

public class RetrieveService {
    private WeightedMultigraph<String, DefaultWeightedEdge> subwayGraph;

    public RetrieveService() {
        initialize();
    }

    private void initialize() {
        this.subwayGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
    }

    public List<Vertex> retrieveShortestPath(Station startStation, Station endStation, Function<Vertex, Integer> criteria) {
        //TODO : 기능보완
        List<Vertex> availableStations = findConnectedPathsBetween(startStation, endStation);
        availableStations.forEach((vertex) -> {
            subwayGraph.setEdgeWeight(subwayGraph.addEdge(vertex.getStartStationName(), vertex.getEndStationName()), criteria.apply(vertex));
        });
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(subwayGraph);
        List<String> shortestPath = dijkstraShortestPath.getPath(startStation.getName(), endStation.getName()).getVertexList();
        return transferToVertex(shortestPath);
    }

//    public List<Vertex> retrievePathByTime(Station startStation, Station endStation) {
//        //TODO : 기능보완, 람다로 줄이기
//        findConnectedPathsBetween(startStation, endStation);
//        VertexRepository.vertexes().forEach((v) -> {
//            subwayGraph.setEdgeWeight(subwayGraph.addEdge(v.getStartStationName(), v.getEndStationName()), v.getTime());
//        });
//        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(subwayGraph);
//        List<String> shortestPath = dijkstraShortestPath.getPath(startStation.getName(), endStation.getName()).getVertexList();
//        return transferToVertex(shortestPath);
//    }

    private List<Vertex> findConnectedPathsBetween(Station startStation, Station endStation) {
        initialize();
        List<Vertex> availableStations = VertexRepository.findAllStationsFromStartStation(startStation);
        validateConnection(availableStations, endStation);
        availableStations.forEach((vertex) -> {
            if (!subwayGraph.containsVertex(vertex.getStartStationName())) {
                subwayGraph.addVertex(vertex.getStartStationName());
            }
            if (!subwayGraph.containsVertex(vertex.getEndStationName())) {
                subwayGraph.addVertex(vertex.getEndStationName());
            }
        });
        return availableStations;
    }

    private void validateConnection(List<Vertex> availableStations, Station endStation) {
        for (Vertex vertex : availableStations) {
            if (vertex.equalsStartStation(endStation) || vertex.equalsEndStation(endStation)) {
                return;
            }
        }
        throw new IllegalArgumentException("출발역과 도착역은 연결 가능해야 합니다.");
    }

    private List<Vertex> transferToVertex(List<String> shortestPath) {
        List<Vertex> vertexes = new ArrayList<>();
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            Station beforeStation = StationRepository.findStationByName(shortestPath.get(i));
            Station afterStation = StationRepository.findStationByName(shortestPath.get(i + 1));
            Vertex vertex = VertexRepository.findByStations(beforeStation, afterStation);
            vertexes.add(vertex);
        }
        return vertexes;
    }

    public EstimationDto estimateTimeAndDistance(List<Vertex> pathway) {
        int totalDistance = pathway.stream().map(v -> v.getDistance()).reduce(0, Integer::sum);
        int totalTime = pathway.stream().map(v -> v.getTime()).reduce(0, Integer::sum);
        return new EstimationDto(totalDistance, totalTime);
    }
}
