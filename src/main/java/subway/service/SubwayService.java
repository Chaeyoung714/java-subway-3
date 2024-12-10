package subway.service;

import java.util.List;
import subway.domain.Line;
import subway.domain.Station;
import subway.domain.Vertex;
import subway.repository.LineRepository;
import subway.repository.StationRepository;
import subway.repository.VertexRepository;

public class SubwayService {

    public void registerSubwayInformation() {
        registerStation();
        registerLine();
        registerVertex();
    }
    private void registerStation() {
        StationRepository.addStation(new Station("교대역"));
        StationRepository.addStation(new Station("강남역"));
        StationRepository.addStation(new Station("역삼역"));
        StationRepository.addStation(new Station("남부터미널역"));
        StationRepository.addStation(new Station("양재역"));
        StationRepository.addStation(new Station("양재시민의숲역"));
        StationRepository.addStation(new Station("매봉역"));
    }

    private void registerLine() {
        LineRepository.addLine(new Line("2호선"));
        LineRepository.addLine(new Line("3호선"));
        LineRepository.addLine(new Line("신분당선"));
    }

    private void registerVertex() {
        VertexRepository.addVertex(new Vertex(LineRepository.findLineByName("2호선"),
                List.of(StationRepository.findStationByName("교대역"), StationRepository.findStationByName("강남역"))
                , 2, 3));
        VertexRepository.addVertex(new Vertex(LineRepository.findLineByName("2호선"),
                List.of(StationRepository.findStationByName("강남역"), StationRepository.findStationByName("역삼역"))
                , 2, 3));
        VertexRepository.addVertex(new Vertex(LineRepository.findLineByName("3호선"),
                List.of(StationRepository.findStationByName("교대역"), StationRepository.findStationByName("남부터미널역"))
                , 3, 2));
        VertexRepository.addVertex(new Vertex(LineRepository.findLineByName("3호선"),
                List.of(StationRepository.findStationByName("남부터미널역"), StationRepository.findStationByName("양재역"))
                , 6, 5));
        VertexRepository.addVertex(new Vertex(LineRepository.findLineByName("3호선"),
                List.of(StationRepository.findStationByName("양재역"), StationRepository.findStationByName("매봉역"))
                , 1, 1));
        VertexRepository.addVertex(new Vertex(LineRepository.findLineByName("신분당선"),
                List.of(StationRepository.findStationByName("강남역"), StationRepository.findStationByName("양재역"))
                , 2, 8));
        VertexRepository.addVertex(new Vertex(LineRepository.findLineByName("신분당선"),
                List.of(StationRepository.findStationByName("양재역"), StationRepository.findStationByName("양재시민의숲역"))
                , 10, 3));
    }
}
