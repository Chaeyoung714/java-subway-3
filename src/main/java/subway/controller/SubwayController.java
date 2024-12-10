package subway.controller;

import java.util.List;
import subway.domain.Vertex;
import subway.dto.EstimationDto;
import subway.dto.StationDto;
import subway.exception.ErrorHandler;
import subway.exception.RetryHandler;
import subway.service.RetrieveService;
import subway.service.SubwayService;
import subway.view.InputHandler;
import subway.view.OutputView;
import subway.view.answer.FunctionChoice;
import subway.view.answer.RetrieveChoice;

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
        subwayService.registerSubwayInformation();
        doOneService();
    }

    private void doOneService() {
        while (true) {
            FunctionChoice choice = inputHandler.readFunctionChoice();
            if (choice.equals(FunctionChoice.RETRIEVE)) {
                doRetrieveService(); //TODO : 예외처리 추가
            }
            if (choice.equals(FunctionChoice.QUIT)) {
                return;
            }
        }
    }

    private void doRetrieveService() {
        RetryHandler.retryUntilSuccess(() -> {
            RetrieveChoice choice = inputHandler.readRetrieveChoice();
            if (choice.equals(RetrieveChoice.BY_DISTANCE) || choice.equals(RetrieveChoice.BY_TIME)) {
                List<Vertex> pathway = retrievePathway(choice);
                EstimationDto estimationDto = retrieveService.estimateTimeAndDistance(pathway);
                outputView.printRetrieveResult(pathway, estimationDto);
            }
            if (choice.equals(RetrieveChoice.BACK)) {
                doOneService();
            }
        });
    }

    private List<Vertex> retrievePathway(RetrieveChoice choice) {
        try {
            String startStation = inputHandler.readStartStation();
            String endStation = inputHandler.readEndStation();
            StationDto stationDto = subwayService.registerDestination(startStation, endStation);
            if (choice.equals(RetrieveChoice.BY_DISTANCE)) {
                return retrieveService.retrieveShortestPath(
                        stationDto.getStartStation(), stationDto.getEndStation(), Vertex::getDistance);
            }
            if (choice.equals(RetrieveChoice.BY_TIME)) {
                return retrieveService.retrieveShortestPath(
                        stationDto.getStartStation(), stationDto.getEndStation(), Vertex::getTime);
            }
            return null; //TODO : 보완
        } catch (IllegalArgumentException e) {
            ErrorHandler.handleUserError(e);
            doRetrieveService();
            return null; //TODO : 보완
        }
    }
}
