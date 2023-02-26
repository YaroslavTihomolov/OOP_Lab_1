package org.ru.nsu.tikhomolov.lab1.ParseInputStream;

import java.io.InputStream;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class ParsingInputData {
    final private Scanner scanner;
    private static final Logger logger = Logger.getLogger(ParsingInputData.class);

    public ParsingInputData(InputStream stream) {
        scanner = new Scanner(stream);
    }

    public Data getData() {
        logger.info("Getting data form file");
        String operation;
        while (true) {
            if (!scanner.hasNext()) {
                return new Data("EOF", null, null);
            }

            operation = scanner.next();
            if (operation.charAt(0) == '#') {
                scanner.nextLine();
            } else {
                break;
            }
        }

        switch (operation) {
            case "DEFINE" -> { return new Data(operation, scanner.next(), scanner.next()); }
            case "PUSH" -> { return new Data(operation, scanner.next(), "0"); }
            default -> { return new Data(operation, null, "0"); }
        }
    }
}
