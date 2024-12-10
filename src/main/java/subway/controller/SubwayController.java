package subway.controller;

import java.util.List;
import subway.domain.Vertex;
import subway.dto.EstimationDto;
import subway.dto.StationDto;
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
                doRetrieveService();
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
        String firstStation = inputHandler.readFirstStation();
        String lastStation = inputHandler.readLastStation();
        StationDto stationDto = subwayService.registerDestination(firstStation, lastStation);
        if (choice.equals(RetrieveChoice.BY_DISTANCE)) {
            return retrieveService.retrieveShortestPath(
                    stationDto.getFirstStation(), stationDto.getLastStation(), Vertex::getDistance);
        }
        if (choice.equals(RetrieveChoice.BY_TIME)) {
            return retrieveService.retrieveShortestPath(
                    stationDto.getFirstStation(), stationDto.getLastStation(), Vertex::getTime);
        }
        throw new IllegalStateException();
    }
}
