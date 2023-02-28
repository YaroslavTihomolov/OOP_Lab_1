package org.ru.nsu.tikhomolov.lab1.Operations;

import org.ru.nsu.tikhomolov.lab1.Calculator.Calculator;

public class Plus implements Commands {
    Calculator.Context cur_context;

    public Plus(Calculator.Context context) {
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
            if (infinityCheck(a_1 + a_2)) {
                logger.warn(new Exception("Double overflow"));
                return false;
            }
            cur_context.stackPush(a_1 + a_2);
            return true;
        } catch (Exception ese) {
            logger.warn(ese.getMessage());
            return false;        }
    }
}
