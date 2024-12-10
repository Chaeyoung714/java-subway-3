package subway.view;


import subway.exception.RetryHandler;
import subway.view.answer.FunctionChoice;
import subway.view.answer.RetrieveChoice;

public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public FunctionChoice readFunctionChoice() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readFunctionChoice();
            InputValidator.validateFunctionChoice(answer);
            return FunctionChoice.findByInputValue(answer);
        });
    }

    public RetrieveChoice readRetrieveChoice() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readRetrieveChoice();
            InputValidator.validateRetrieveChoice(answer);
            return RetrieveChoice.findByInputValue(answer);
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
