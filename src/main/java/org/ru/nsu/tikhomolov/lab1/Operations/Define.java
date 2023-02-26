package org.ru.nsu.tikhomolov.lab1.Operations;

import org.apache.log4j.Logger;
import org.ru.nsu.tikhomolov.lab1.Calculator.Calculator;

public class Define implements Commands {
    Calculator.Context cur_context;

    public Define(Calculator.Context context) {
        cur_context = context;
    }


    @Override
    public void doCommand() {
        logger.debug("Do " + this.getClass().getName());
        try {
            Double.parseDouble(cur_context.variable_name);
            logger.error(new Exception("'" + cur_context.variable_name + "' - Wrong variable Name"));
        } catch (NumberFormatException nfe) {
            cur_context.mapPut(cur_context.variable_name, cur_context.variable_value);
        }
    }
}
