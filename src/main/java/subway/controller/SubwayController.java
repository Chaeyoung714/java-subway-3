package subway.controller;

import subway.view.InputHandler;
import subway.view.OutputView;

public class SubwayController {
    private final InputHandler inputHandler;
    private final OutputView outputView;

    public SubwayController(InputHandler inputHandler, OutputView outputView) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
    }

    public void run() {

    }
}
