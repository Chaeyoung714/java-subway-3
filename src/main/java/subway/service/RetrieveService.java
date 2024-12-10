package subway.service;

import subway.domain.Station;
import subway.repository.StationRepository;

public class RetrieveService {

    public void registerStations(String startStationName, String endStationName) {
        //TODO : 검증 추가
        Station startStation = StationRepository.findStationByName(startStationName);
        Station endStation = StationRepository.findStationByName(endStationName);

        

    }
}
