package subway.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.Station;
import subway.domain.Vertex;
import subway.dto.EstimationDto;
import subway.repository.StationRepository;
import subway.repository.VertexRepository;

public class RetrieveService {
    private final WeightedMultigraph<String, DefaultWeightedEdge> subwayGraph;

    public RetrieveService() {
        this.subwayGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
    }

    public void registerSubwayGraph() {
        StationRepository.stations().forEach((s) -> {
            subwayGraph.addVertex(s.getName());
        });
    }

    public List<Vertex> retrievePathByDistance(Station startStation, Station endStation) {
        //TODO : 기능보완
        VertexRepository.vertexes().forEach((v) -> {
            subwayGraph.setEdgeWeight(subwayGraph.addEdge(v.getStartStationName(), v.getEndStationName()), v.getDistance());
        });
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(subwayGraph);
        List<String> shortestPath = dijkstraShortestPath.getPath(startStation.getName(), endStation.getName()).getVertexList();
        return transferToVertex(shortestPath);
    }

    public List<Vertex> retrievePathByTime(Station startStation, Station endStation) {
        //TODO : 기능보완, 람다로 줄이기
        VertexRepository.vertexes().forEach((v) -> {
            subwayGraph.setEdgeWeight(subwayGraph.addEdge(v.getStartStationName(), v.getEndStationName()), v.getTime());
        });
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(subwayGraph);
        List<String> shortestPath = dijkstraShortestPath.getPath(startStation.getName(), endStation.getName()).getVertexList();
        return transferToVertex(shortestPath);
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
