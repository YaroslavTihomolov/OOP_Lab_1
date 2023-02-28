package org.ru.nsu.tikhomolov.lab1.Operations;

import org.ru.nsu.tikhomolov.lab1.Calculator.Calculator;

public class Pop implements Commands {
    Calculator.Context cur_context;

    public Pop(Calculator.Context context) {
        cur_context = context;
    }

    @Override
    public boolean doCommand() {
        logger.debug("Do " + this.getClass().getName());
        try {
            cur_context.stackPop();
            return true;
        } catch (Exception ese) {
            logger.warn(ese.getMessage());
            return false;
        }
    }
}
