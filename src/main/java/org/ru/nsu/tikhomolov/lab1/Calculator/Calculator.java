package org.ru.nsu.tikhomolov.lab1.Calculator;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.log4j.Logger;
import org.ru.nsu.tikhomolov.lab1.Main;
import org.ru.nsu.tikhomolov.lab1.ParseInputStream.*;
import org.ru.nsu.tikhomolov.lab1.ParseInputArgs.ParseInputArgs;
import org.ru.nsu.tikhomolov.lab1.Factory.Factory;
import org.ru.nsu.tikhomolov.lab1.Operations.Commands;

import static java.lang.Double.parseDouble;

record parameter(String name, Double number) { }

public class Calculator {
    public static class Context {
        private static final Map<String, Double> values = new HashMap<>();
        private static final Stack<Double> stack = new Stack<>();

        public String variable_name;
        public double variable_value;

        Context(parameter a) {
            variable_name = a.name();
            variable_value = a.number();
        }

        public Double stackPop() {
            try {
                return stack.pop();
            } catch (EmptyStackException ese) {
                logger.error(new Exception("Use pop for empty stack"));
                throw new RuntimeException(ese);
            }
        }

        public void stackPush(Double value) {
            try {
                stack.push(value);
            } catch (NullPointerException npe) {
                throw new RuntimeException(npe.getMessage());
            }
        }

        public void mapPut(String name, Double number) {
            try {
                parseDouble(name);
                throw new RuntimeException("Wrong variable Name");
            } catch (NumberFormatException nfe) {
                values.put(name, number);
            }
        }

        public Double mapGet(String name) {
            try {
                return values.get(name);
            } catch (ClassCastException | NullPointerException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        public Double stackPeek() {
            try {
                return stack.peek();
            } catch (EmptyStackException ese) {
                throw new RuntimeException(ese);
            }
        }
    }

    ParsingInputData a;

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public Calculator(String[] args) {
        logger.debug("Create calculator instance");
        try {
            a = new ParsingInputData(ParseInputArgs.getInputStream(args));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void doCalculation() {
        logger.info("Start calculation");

        Data b;
        while (true) {
            b = a.getData();
            if (b.operation().equals("EOF")) break;
            Commands c = null;
            try {
                c = (Commands) Factory.getInstance(b.operation(), new Context(new parameter(b.name(), parseDouble(b.value()))));
            } catch (Exception e) {
                logger.error("Factory error", new Exception(e.getMessage()));
                throw new RuntimeException(e.getMessage());
            }
            try {
                c.doCommand();
            } catch (Exception e) {
                logger.error(new Exception(e.getMessage()));
                throw new RuntimeException(e.getMessage());
            }
        }
        logger.info("Calculation end");
    }
}
