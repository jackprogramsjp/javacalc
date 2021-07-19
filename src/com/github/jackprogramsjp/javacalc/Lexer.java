package com.github.jackprogramsjp.javacalc;

import java.util.Iterator;

public class Lexer implements Iterator<Token> {
    String text;
    Character current = null;
    int position = 0;

    public Lexer(String text) {
        this.text = text;
        advance();
    }

    private void advance() {
        current = position < text.length() ? text.charAt(position++) : null;
    }

    private Token getNumber() {
        int decimalPointCount = 0;
        StringBuilder number = new StringBuilder(current.toString());
        advance();

        while (current != null && (current == '.' || Character.isDigit(current))) {
            if (current == '.') {
                if (++decimalPointCount > 1) {
                    break;
                }
            }

            number.append(current);
            advance();
        }

        if (number.toString().startsWith(".")) {
            number.append(number.append('0'));
        }

        if (number.toString().endsWith(".")) {
            number.append('0');
        }

        return new Token(Token.Type.NUMBER, Double.parseDouble(number.toString()));
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Token next() {
        if (current == null) return null;

        switch (current) {
            case '+' -> {
                advance();
                return new Token(Token.Type.PLUS);
            }
            case '-' -> {
                advance();
                return new Token(Token.Type.MINUS);
            }
            case '*' -> {
                advance();
                return new Token(Token.Type.MULTIPLY);
            }
            case '/' -> {
                advance();
                return new Token(Token.Type.DIVIDE);
            }
            case '(' -> {
                advance();
                return new Token(Token.Type.LPAREN);
            }
            case ')' -> {
                advance();
                return new Token(Token.Type.RPAREN);
            }
            default -> {
                if (Character.isWhitespace(current)) {
                    advance();
                    return next();
                } else if (current == '.' || Character.isDigit(current)) {
                    return getNumber();
                }
            }
        }

        throw new RuntimeException(String.format("Illegal character '%c'", current));
    }
}
