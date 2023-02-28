package org.ru.nsu.tikhomolov.lab1.Calculator;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.log4j.Logger;
import org.ru.nsu.tikhomolov.lab1.ParseInputStream.*;
import org.ru.nsu.tikhomolov.lab1.ParseInputArgs.ParseInputArgs;
import org.ru.nsu.tikhomolov.lab1.Factory.Factory;
import org.ru.nsu.tikhomolov.lab1.Operations.Commands;

import static java.lang.Double.parseDouble;


public class Calculator {
    public static class Context {
        private static final Map<String, Double> values = new HashMap<>();
        private static final Stack<Double> stack = new Stack<>();

        public String[] arguments;

        Context(String[] inp_args) {
             arguments= inp_args;
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
                Double d = values.get(name);
                if (d != null) {
                    return d;
                } else {
                    logger.error(new Exception(name + " do not exist in map"));
                    return null;
                }
            } catch (ClassCastException | NullPointerException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        public Double stackPeek() {
            try {
                return stack.peek();
            } catch (EmptyStackException ese) {
                logger.error(new Exception("Use pick for empty stack"));
                throw new RuntimeException(ese);
            }
        }

        public boolean mapContainsKey(String name) {
            return values.containsKey(name);
        }
    }

    ParsingInputData a;

    private static final Logger logger = Logger.getLogger(Calculator.class.getName());

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

        while (true) {
            String[] input_line = a.getData();
            if (input_line[0].equals("EOF")) break;
            Commands command;
            try {
                command = (Commands) Factory.getInstance(input_line[0], new Context(input_line));
            } catch (Exception e) {
                logger.error("Factory error", new Exception(e.getMessage()));
                throw new RuntimeException(e.getMessage());
            }
            if (command == null) {
                continue;
            }
            try {
                if (command.doCommand()) {
                    logger.info(input_line[0] + " successfully complete");
                } else {
                    logger.warn("Error: " + input_line[0] + " can not be complete");
                }
            } catch (Exception e) {
                logger.error(new Exception(e.getMessage()));
                throw new RuntimeException(e.getMessage());
            }
        }
        logger.info("Calculation end");
    }
}
