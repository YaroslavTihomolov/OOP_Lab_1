package org.ru.nsu.tikhomolov.lab1.Operations;

import org.ru.nsu.tikhomolov.lab1.Calculator.Calculator;

import java.util.EmptyStackException;

public class Print implements Commands {
    Calculator.Context cur_context;

    public Print(Calculator.Context context) {
        cur_context = context;
    }

    @Override
    public void doCommand() {
        logger.debug("Do " + this.getClass().getName());
        try {
            System.out.println(cur_context.stackPeek());
        } catch (Exception ese) {
            logger.warn(new Exception("Can not PRINT: stack is empty"));
        }
    }
}
