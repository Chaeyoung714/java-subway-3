package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.service.RetrieveService;
import subway.service.SubwayService;
import subway.view.InputHandler;
import subway.view.InputView;
import subway.view.OutputView;

public class DependencyConfig {
    private final Scanner scanner;

    public DependencyConfig(Scanner scanner) {
        this.scanner = scanner;
    }

    public SubwayController controller() {
        return new SubwayController(
                new InputHandler(new InputView(scanner))
                , new OutputView()
                , new SubwayService()
                , new RetrieveService()
        );
    }
}
