package subway.util;

import java.util.Arrays;
import java.util.List;

public class InfoScanner {

    public static List<String> readInfo(String information) {
        return Arrays.stream(information.split(""))
                .map()
                .toList();
    }

    private InfoScanner() {
    }
}
