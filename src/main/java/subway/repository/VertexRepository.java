package subway.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import subway.domain.Vertex;

public class VertexRepository {
    private static final List<Vertex> vertexes = new ArrayList<>();

    public static List<Vertex> vertexes() {
        return Collections.unmodifiableList(vertexes);
    }

    public static void addVertex(Vertex vertex) {
        vertexes.add(vertex);
    }

//    public static boolean deleteVertexByName() {
//
//    }

    public static void deleteAll() {
        vertexes.clear();
    }
}