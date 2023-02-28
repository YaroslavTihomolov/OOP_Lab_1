package org.ru.nsu.tikhomolov.lab1.Operations;

import org.ru.nsu.tikhomolov.lab1.Calculator.Calculator;


public class Division implements Commands {
    Calculator.Context cur_context;

    public Division(Calculator.Context context) {
        cur_context = context;
    }

    private boolean infinityCheck(Double value) {
        return value.isInfinite();
    }

    @Override
    public boolean doCommand() {
        logger.debug("Do " + this.getClass().getName());
        try {
            double a_1 = cur_context.stackPop();
            double a_2 = cur_context.stackPop();
            if (a_2 == 0) {
                logger.warn(new Exception("Division by zero"));
                return false;
            }
            if (infinityCheck(a_1 / a_2)) {
                logger.warn(new Exception("Double overflow"));
                return false;
            } else {
                cur_context.stackPush(a_1 / a_2);
                return true;
            }
        } catch (Exception ese) {
            logger.warn(ese.getMessage());
            return false;
        }
    }
}
