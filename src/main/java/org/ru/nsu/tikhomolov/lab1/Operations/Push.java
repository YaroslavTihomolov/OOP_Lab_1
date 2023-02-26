package org.ru.nsu.tikhomolov.lab1.Operations;

import org.ru.nsu.tikhomolov.lab1.Calculator.Calculator;

public class Push implements Commands {
    Calculator.Context cur_context;

    public Push(Calculator.Context context) {
        cur_context = context;
    }

    @Override
    public void doCommand() {
        logger.debug("Do " + this.getClass().getName());
        double d;
        try {
            d = Double.parseDouble(cur_context.variable_name);
            cur_context.stackPush(d);
        } catch (NumberFormatException nfe) {
            try {
                cur_context.stackPush(cur_context.mapGet(cur_context.variable_name));
            } catch (NullPointerException npe) {
                throw new RuntimeException(npe.getMessage());
            }
        }
    }
}
