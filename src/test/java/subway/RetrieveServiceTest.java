package subway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import subway.domain.Station;
import subway.domain.Vertex;
import subway.dto.EstimationDto;
import subway.repository.StationRepository;
import subway.repository.VertexRepository;
import subway.service.RetrieveService;
import subway.service.SubwayService;

public class RetrieveServiceTest {
    private static SubwayService subwayService = new SubwayService();;
    private static RetrieveService retrieveService = new RetrieveService();

    @BeforeAll
    static void setUp() {
        subwayService.registerSubwayInformation();
    }

    @Test
    void 상행_방향에_없는_역은_고려하지_않는다() {
        Station testStation = StationRepository.findStationByName("매봉역");
        List<Vertex> testVertexes = VertexRepository.findAllVertexesFromFirstStation(testStation);
        assertThat(testVertexes.size()).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource(value = {"교대역:양재역:교대역,강남역,강남역,양재역:4:11"}, delimiter = ':')
    void 거리_기준으로_최소_경로를_구한다(String startName, String endName, String expectedPath, int expectedDistance, int expectedTime) {
        Station startStation = StationRepository.findStationByName(startName);
        Station endStation = StationRepository.findStationByName(endName);

        List<Vertex> shortestPath = retrieveService.retrieveShortestPath(startStation, endStation, Vertex::getDistance);
        List<String> vertexes = shortestPath.stream()
                .map(v -> String.join(",", v.getStartStationName(), v.getEndStationName()))
                .collect(Collectors.toList());
        String pathString = String.join(",", vertexes);

        EstimationDto estimationDto = retrieveService.estimateTimeAndDistance(shortestPath);

        assertThat(pathString).isEqualTo(expectedPath);
        assertThat(estimationDto.getTotalDistance()).isEqualTo(expectedDistance);
        assertThat(estimationDto.getTotalTime()).isEqualTo(expectedTime);
    }

    @ParameterizedTest
    @CsvSource(value = {"교대역:양재역:교대역,남부터미널역,남부터미널역,양재역:9:7"}, delimiter = ':')
    void 소요시간_기준으로_최소_경로를_구한다(String startName, String endName, String expectedPath, int expectedDistance, int expectedTime) {
        Station startStation = StationRepository.findStationByName(startName);
        Station endStation = StationRepository.findStationByName(endName);

        List<Vertex> shortestPath = retrieveService.retrieveShortestPath(startStation, endStation, Vertex::getTime);
        List<String> vertexes = shortestPath.stream()
                .map(v -> String.join(",", v.getStartStationName(), v.getEndStationName()))
                .collect(Collectors.toList());
        String pathString = String.join(",", vertexes);

        EstimationDto estimationDto = retrieveService.estimateTimeAndDistance(shortestPath);

        assertThat(pathString).isEqualTo(expectedPath);
        assertThat(estimationDto.getTotalDistance()).isEqualTo(expectedDistance);
        assertThat(estimationDto.getTotalTime()).isEqualTo(expectedTime);
    }
}
