package org.ru.nsu.tikhomolov.lab1.Operations;

import org.apache.log4j.Logger;
import org.ru.nsu.tikhomolov.lab1.Calculator.Calculator;

import static java.lang.Double.parseDouble;

public class Define implements Commands {
    Calculator.Context cur_context;

    public Define(Calculator.Context context) {
        cur_context = context;
    }

    public static boolean isDouble(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    @Override
    public boolean doCommand() {
        logger.debug("Do " + this.getClass().getName() + " { " + cur_context.arguments[1] + ", " + cur_context.arguments[2] + " }");
        if (!isDouble(cur_context.arguments[1])) {
            if (cur_context.mapContainsKey(cur_context.arguments[1])) {
                logger.warn("'" + cur_context.arguments[1] + "' - Wrong variable Name");
                return false;
            }
            cur_context.mapPut(cur_context.arguments[1], parseDouble(cur_context.arguments[2]));
            return true;
        } else {
            logger.warn("'" + cur_context.arguments[1] + "' - Wrong variable Name");
            return false;
        }
    }
}
