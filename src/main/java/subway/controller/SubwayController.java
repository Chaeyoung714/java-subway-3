package subway.controller;

import subway.service.SubwayService;
import subway.view.InputHandler;
import subway.view.OutputView;

public class SubwayController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final SubwayService subwayService;

    public SubwayController(InputHandler inputHandler, OutputView outputView, SubwayService subwayService) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.subwayService = subwayService;
    }

    public void run() {

    }
}
