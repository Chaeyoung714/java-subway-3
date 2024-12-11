package subway.view;


import subway.handler.RetryHandler;
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

    public String readFirstStation() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readFirstStation();
            InputValidator.validateInputValue(answer);
            return answer;
        });
    }

    public String readLastStation() {
        return RetryHandler.retryUntilSuccessAndReturn(() -> {
            String answer = inputView.readLastStation();
            InputValidator.validateInputValue(answer);
            return answer;
        });
    }
}
