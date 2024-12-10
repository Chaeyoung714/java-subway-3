package subway.dto;

import subway.domain.Station;

public class StationDto {
    private final Station firstStation;
    private final Station lastStation;

    public StationDto(Station firstStation, Station lastStation) {
        this.firstStation = firstStation;
        this.lastStation = lastStation;
    }

    public Station getFirstStation() {
        return firstStation;
    }

    public Station getLastStation() {
        return lastStation;
    }
}
