package subway.dto;

public class EstimationDto {
    private final int totalDistance;
    private final int totalTime;

    public EstimationDto(int totalDistance, int totalTime) {
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public int getTotalTime() {
        return totalTime;
    }
}
