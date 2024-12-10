package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
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
        );
    }
}
