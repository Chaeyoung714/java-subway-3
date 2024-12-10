package subway.view.answer;

import java.util.Arrays;
import subway.domain.Criteria;

public enum RetrieveChoice {
    BY_DISTANCE("1", Criteria.DISTANCE),
    BY_TIME("2", Criteria.TIME),
    BACK("B", null),
    ;

    private final String inputValue;
    private final Criteria retrieveCriteria;

    RetrieveChoice(String inputValue, Criteria retrieveCriteria) {
        this.inputValue = inputValue;
        this.retrieveCriteria = retrieveCriteria;
    }

    public static RetrieveChoice findByInputValue(String inputValue) {
        return Arrays.stream(RetrieveChoice.values())
                .filter(a -> a.getInputValue().equals(inputValue))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private String getInputValue() {
        return inputValue;
    }

    public Criteria getRetrieveCriteria() {
        if (retrieveCriteria == null) {
            throw new IllegalStateException();
        }
        return retrieveCriteria;
    }
}
