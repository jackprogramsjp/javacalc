package com.github.jackprogramsjp.javacalc;

public class Main {

    public static void main(String[] args) {
        Lexer lexer = new Lexer("1 + 2 * 3 ");

        try {
            for (Token token = lexer.next(); lexer.hasNext(); token = lexer.next()) {
                System.out.println(token.toString());
            }
        } catch (RuntimeException e) {
            e.printStackTrace(System.err);
        }
    }
}
