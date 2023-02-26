package org.ru.nsu.tikhomolov.lab1.ParseInputStream;

import org.apache.log4j.Logger;

public class Data {
    private final String operation;

    public String operation() {
        return operation;
    }

    private final String name;

    public String name() {
        return name;
    }

    private final String value;

    public String value() {
        return value;
    }

    private static final Logger logger = Logger.getLogger(Data.class);
    public Data (String cur_operation, String cur_name, String cur_value) {
        operation = cur_operation;
        name = cur_name;
        value = cur_value;
        logger.debug("Create Data Instance { " + operation + ", " + name + ", " + value + " }");
    }
}
