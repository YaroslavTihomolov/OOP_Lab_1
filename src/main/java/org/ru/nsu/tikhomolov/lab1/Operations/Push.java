package org.ru.nsu.tikhomolov.lab1.Operations;

import org.ru.nsu.tikhomolov.lab1.Calculator.Calculator;

public class Push implements Commands {
    Calculator.Context cur_context;

    public Push(Calculator.Context context) {
        cur_context = context;
    }

    @Override
    public boolean doCommand() {
        logger.debug("Do " + this.getClass().getName() + " { " + cur_context.arguments[1] + " }");
        try {
            cur_context.stackPush(Double.parseDouble(cur_context.arguments[1]));
            return true;
        } catch (NumberFormatException nfe) {
            try {
                Double d = cur_context.mapGet(cur_context.arguments[1]);
                if (d != null) {
                    cur_context.stackPush(d);
                    return true;
                } else {
                    return false;
                }
            } catch (NullPointerException npe) {
                logger.warn(npe.getMessage());
                return false;
            }
        }
    }
}
