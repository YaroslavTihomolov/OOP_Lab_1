package org.ru.nsu.tikhomolov.lab1.Operations;

import org.ru.nsu.tikhomolov.lab1.Calculator.Calculator;


public class Print implements Commands {
    Calculator.Context cur_context;

    public Print(Calculator.Context context) {
        cur_context = context;
    }

    @Override
    public boolean doCommand() {
        logger.debug("Do " + this.getClass().getName());
        try {
            System.out.println(cur_context.stackPeek());
            return true;
        } catch (Exception ese) {
            logger.warn(ese.getMessage());
            return false;
        }
    }
}
