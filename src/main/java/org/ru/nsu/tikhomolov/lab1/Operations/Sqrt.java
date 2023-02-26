package org.ru.nsu.tikhomolov.lab1.Operations;

import org.ru.nsu.tikhomolov.lab1.Calculator.Calculator;

import java.util.EmptyStackException;

public class Sqrt implements Commands {
    Calculator.Context cur_context;

    public Sqrt(Calculator.Context context) {
        cur_context = context;
    }

    @Override
    public void doCommand() {
        logger.debug("Do " + this.getClass().getName());
        try {
            cur_context.stackPush(Math.sqrt(cur_context.stackPop()));
        } catch (EmptyStackException ese) {
            throw new RuntimeException(ese);
        }
    }
}
