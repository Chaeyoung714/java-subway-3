package subway.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import subway.domain.Vertex;
import subway.dto.EstimationDto;

public class OutputView {

    public void printRetrieveResult(List<Vertex> pathway, EstimationDto estimationDto) {
        System.out.println(System.lineSeparator() + "## 조회 결과");
        printInformation("---");
        printInformation(String.format("총 거리: %dkm", estimationDto.getTotalDistance()));
        printInformation(String.format("총 소요 시간: %d분", estimationDto.getTotalTime()));
        printInformation("---");
        printPathway(pathway);
    }

    private void printPathway(List<Vertex> pathway) {
        List<String> stations = new ArrayList<>();
        for (Vertex vertex : pathway) {
            if (stations.isEmpty()) {
                stations.add(vertex.getStartStationName());
            }
            stations.add(vertex.getEndStationName());
        }
        stations.forEach((stationName) -> {
            printInformation(stationName);
        });
    }

    private void printInformation(String information) {
        System.out.println(String.format("[INFO] %s", information));
    }
}
