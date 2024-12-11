package subway.domain;

import java.util.function.Function;

public enum Criteria {
    DISTANCE(Vertex::getDistance),
    TIME(Vertex::getTime),
    ;

    private final Function<Vertex, Integer> method;

    Criteria(Function<Vertex, Integer> method) {
        this.method = method;
    }

    public int apply(Vertex vertex) {
        return method.apply(vertex);
    }
}
