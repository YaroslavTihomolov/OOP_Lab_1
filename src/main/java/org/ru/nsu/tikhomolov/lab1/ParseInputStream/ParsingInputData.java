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

    public String[] getData() {
        logger.info("Getting data form file");
        String operation;
        while (true) {
            if (!scanner.hasNext()) {
                return new String[] { "EOF" };
            }

            operation = scanner.nextLine();
            if (operation.charAt(0) == '#') {
                scanner.nextLine();
            } else {
                break;
            }
        }

        return operation.split(" ");
    }
}
