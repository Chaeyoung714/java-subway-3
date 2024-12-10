package subway.service;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.Criteria;
import subway.domain.Station;
import subway.domain.Vertex;
import subway.dto.EstimationDto;
import subway.exception.ExceptionMessages;
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

    public List<Vertex> retrieveShortestPath(Station startStation, Station endStation, Criteria criteria) {
        List<Vertex> availableStations = findAvailableVertexesBetween(startStation, endStation);
        setEdgeInformation(availableStations, criteria);
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(subwayGraph);
        List<String> shortestPath = dijkstraShortestPath.getPath(startStation.getName(), endStation.getName()).getVertexList();
        return transferToVertex(shortestPath);
    }

    private void setEdgeInformation(List<Vertex> availableStations, Criteria criteria) {
        availableStations.forEach((vertex) -> {
            subwayGraph.setEdgeWeight(subwayGraph.addEdge(vertex.getStartStationName(), vertex.getEndStationName()), criteria.apply(vertex));
        });
    }

    private List<Vertex> findAvailableVertexesBetween(Station startStation, Station endStation) {
        initialize();
        List<Vertex> availableStations = VertexRepository.findAllVertexesFromFirstStation(startStation);
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
        throw new IllegalArgumentException(ExceptionMessages.UNCONNECTED_STATIONS.getMessage());
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
