package subway.dto;

import subway.domain.Station;

public class StationDto {
    private final Station startStation;
    private final Station endStation;

    public StationDto(Station startStation, Station endStation) {
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public Station getStartStation() {
        return startStation;
    }

    public Station getEndStation() {
        return endStation;
    }
}
