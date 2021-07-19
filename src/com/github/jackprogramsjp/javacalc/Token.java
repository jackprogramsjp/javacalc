package com.github.jackprogramsjp.javacalc;

public class Token {

    public enum Type {
        NUMBER,
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        LPAREN,
        RPAREN
    }

    public Type type;
    public double value;

    public Token(Type type) {
        this.type = type;
    }

    public Token(Type type, double value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        if (type == Type.NUMBER) {
            return String.format("%s:%f", type, value);
        }

        return type.toString();
    }
}
