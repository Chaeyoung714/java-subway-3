package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.domain.Station;
import subway.repository.StationRepository;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        DependencyConfig config = new DependencyConfig(scanner);
        SubwayController controller = config.controller();
        controller.run();
    }
}
