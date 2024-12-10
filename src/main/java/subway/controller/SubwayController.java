package subway.controller;

import subway.exception.ErrorHandler;
import subway.service.RetrieveService;
import subway.service.SubwayService;
import subway.view.InputHandler;
import subway.view.OutputView;

public class SubwayController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final SubwayService subwayService;
    private final RetrieveService retrieveService;

    public SubwayController(InputHandler inputHandler, OutputView outputView, SubwayService subwayService,
                            RetrieveService retrieveService) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.subwayService = subwayService;
        this.retrieveService = retrieveService;
    }

    public void run() {
        doOneService();
    }

    private void doOneService() {
        String choice = inputHandler.readFunctionChoice();
        if (choice.equals("1")) {
            doPathwayService();
        }
        if (choice.equals("Q")) {

        }
    }

    private void doPathwayService() {
        String choice = inputHandler.readPathwayChoice();
        if (choice.equals("1")) {
            retrievePathwayByDistance();
        }
        if (choice.equals("2")) {
            retrievePathwayByTime();
        }
        if (choice.equals("B")) {
            doOneService();
        }
    }

    private void retrievePathwayByDistance() {
        try {
            String startStation = inputHandler.readStartStation();
            String endStation = inputHandler.readEndStation();
            retrieveService.registerStations(startStation, endStation);
        } catch () {
            ErrorHandler.handleSystemError();
            doPathwayService();
        }
    }

    private void retrievePathwayByTime() {
    }
}
