package org.ru.nsu.tikhomolov.lab1.Operations;

import org.apache.log4j.Logger;

public interface Commands {
    static final Logger logger = Logger.getLogger(Commands.class);

    public void doCommand();
}
