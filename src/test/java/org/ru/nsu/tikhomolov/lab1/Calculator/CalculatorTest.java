package org.ru.nsu.tikhomolov.lab1.Calculator;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    ByteArrayOutputStream routine(String text_for_file) throws FileNotFoundException {
        PrintStream fileStream = new PrintStream("input.txt");
        System.setOut(fileStream);
        System.out.println(text_for_file);
        fileStream.close();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        return baos;
    }

    @org.junit.jupiter.api.Test
    void testExceptions() throws FileNotFoundException {
        var baos = routine("POP\nPRINT\n+\n-\n/\n*\nSQRT\n");
        String[] input = { "input.txt" };
        Calculator calc = new Calculator(input);
        calc.doCalculation();
        assertEquals("", baos.toString());
    }

    @org.junit.jupiter.api.Test
    void testWrongFileFormat() {
        try {
            String[] input = { "input.tx" };
            Calculator calc = new Calculator(input);
            calc.doCalculation();
        } catch (Exception e) {
            assertEquals("java.lang.Exception: Wrong file format.", e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void testWrongNotExistFile() {
        try {
            String[] input = { "inpt.txt" };
            Calculator calc = new Calculator(input);
            calc.doCalculation();
        } catch (Exception e) {
            assertEquals("java.lang.Exception: File inpt.txt does not exist.", e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void testOperations_1() throws FileNotFoundException {
        var baos = routine("DEFINE a 16\nPUSH a\nSQRT\nPRINT");
        String[] input = { "input.txt" };
        Calculator calc = new Calculator(input);
        calc.doCalculation();
        assertEquals("4.0\r\n", baos.toString());
    }

    @org.junit.jupiter.api.Test
    void testOperations_2() throws FileNotFoundException {
        var baos = routine("DEFINE a 16.14\nDEFINE b 12.3859\nDEFINE a 16\nPUSH 10\nPUSH a\nPUSH b\n/\nPRINT\n+\nPRINT\n");
        String[] input = { "input.txt" };
        Calculator calc = new Calculator(input);
        calc.doCalculation();
        assertEquals("0.7674039653035934\r\n10.767403965303593\r\n", baos.toString());
    }
}
