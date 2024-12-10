package subway.view;


import subway.exception.RetryHandler;

public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public String readFunctionChoice() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readFunctionChoice();
            //TODO : 검증추가
            return answer;
        });
    }

    public String readPathwayChoice() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readPathwayChoice();
            //TODO : 검증추가
            return answer;
        });
    }

    public String readStartStation() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readStartStation();
            //TODO : 검증추가
            return answer;
        });
    }

    public String readEndStation() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readEndStation();
            //TODO : 검증추가
            return answer;
        });
    }
}
