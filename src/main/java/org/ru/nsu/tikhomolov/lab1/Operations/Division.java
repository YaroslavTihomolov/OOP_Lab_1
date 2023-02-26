package org.ru.nsu.tikhomolov.lab1.Operations;

import org.ru.nsu.tikhomolov.lab1.Calculator.Calculator;

import java.util.EmptyStackException;

public class Division implements Commands {
    Calculator.Context cur_context;

    public Division(Calculator.Context context) {
        cur_context = context;
    }

    @Override
    public void doCommand() {
        logger.debug("Do " + this.getClass().getName());
        try {
            double a_1 = cur_context.stackPop();
            double a_2 = cur_context.stackPop();
            if (a_2 == 0) {
                logger.warn(new Exception("Division by zero"));
            } else {
                cur_context.stackPush(a_1 / a_2);
            }
        } catch (EmptyStackException ese) {
            throw new RuntimeException(ese);
        }
    }
}
