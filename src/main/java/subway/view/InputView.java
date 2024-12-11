package subway.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readFunctionChoice() {
        System.out.println(System.lineSeparator() + "## 메인 화면"
                + System.lineSeparator() + "1. 경로 조회"
                + System.lineSeparator() + "Q. 종료");
        System.out.println(System.lineSeparator() + "## 원하는 기능을 선택하세요.");
        return scanner.nextLine();
    }

    public String readRetrieveChoice() {
        System.out.println(System.lineSeparator() + "## 경로 기준"
                + System.lineSeparator() + "1. 최단 거리"
                + System.lineSeparator() + "2. 최소 시간"
                + System.lineSeparator() + "B. 돌아가기");
        System.out.println(System.lineSeparator() + "## 원하는 기능을 선택하세요.");
        return scanner.nextLine();
    }

    public String readFirstStation() {
        System.out.println(System.lineSeparator() + "## 출발역을 입력하세요.");
        return scanner.nextLine();
    }

    public String readLastStation() {
        System.out.println(System.lineSeparator() + "## 도착역을 입력하세요.");
        return scanner.nextLine();
    }
}
