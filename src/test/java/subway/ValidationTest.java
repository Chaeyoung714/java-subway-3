package subway;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import subway.domain.Station;
import subway.domain.Vertex;
import subway.repository.StationRepository;
import subway.service.RetrieveService;
import subway.service.SubwayService;
import subway.view.InputValidator;

public class ValidationTest {
    private static SubwayService subwayService = new SubwayService();;
    private static RetrieveService retrieveService = new RetrieveService();

    @BeforeAll
    static void setUp() {
        subwayService.registerSubwayInformation();
    }

    @Test
    void 출발역의_상행_방향에_도착역이_없을경우_예외가_발생한다() {
        Station startStation = StationRepository.findStationByName("매봉역");
        Station endStation = StationRepository.findStationByName("양재역");
        assertThatThrownBy(() -> retrieveService.retrieveShortestPath(startStation, endStation, Vertex::getDistance))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발역과 도착역은 연결 가능해야 합니다.");
    }

    @Test
    void 출발역과_도착역이_같을경우_예외가_발생한다() {
        String testName = "양재역";
        assertThatThrownBy(() -> subwayService.registerDestination(testName, testName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발역과 도착역이 동일합니다.");
    }

    @Test
    void 존재하지_않는_역이_입력될_경우_예외가_발생한다() {
        String testName = "양재재역";
        assertThatThrownBy(() -> subwayService.registerDestination(testName, "양재시민의숲역"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 역입니다.");
    }
}
