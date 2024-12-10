package subway.controller;

import java.util.List;
import subway.domain.Station;
import subway.domain.Vertex;
import subway.dto.EstimationDto;
import subway.dto.StationDto;
import subway.exception.ErrorHandler;
import subway.repository.StationRepository;
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
        subwayService.registerSubwayInformation();
        retrieveService.registerSubwayGraph();
        doOneService();
    }

    private void doOneService() {
        while (true) {
            String choice = inputHandler.readFunctionChoice();
            if (choice.equals("1")) {
                doPathwayService(); //TODO : 예외처리 추가
            }
            if (choice.equals("Q")) {
                return;
            }
        }
    }

    private void doPathwayService() {
        String choice = inputHandler.readPathwayChoice();
        if (choice.equals("1") || choice.equals("2")) {
            List<Vertex> pathway = retrievePathway(choice);
            EstimationDto estimationDto = retrieveService.estimateTimeAndDistance(pathway);
            outputView.printRetrieveResult(pathway, estimationDto);
        }
        if (choice.equals("B")) {
            doOneService();
        }
    }

    private List<Vertex> retrievePathway(String choice) {
        try {
            String startStation = inputHandler.readStartStation();
            String endStation = inputHandler.readEndStation();
            StationDto stationDto = subwayService.registerDestination(startStation, endStation);
            if (choice.equals("1")) {
                return retrieveService.retrievePathByDistance(
                        stationDto.getStartStation(), stationDto.getEndStation());
            }
            if (choice.equals("2")) {
                return retrieveService.retrievePathByTime(
                        stationDto.getStartStation(), stationDto.getEndStation());
            }
            return null; //TODO : 보완
        } catch (IllegalArgumentException e) {
//            ErrorHandler.handleUserError(e);
            throw e;
//            doPathwayService();
//            return null; //TODO : 보완
        }
    }
}
