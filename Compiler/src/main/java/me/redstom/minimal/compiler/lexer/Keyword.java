package me.redstom.minimal.compiler.lexer;

import java.util.Arrays;

public enum Keyword implements TokenValue {
    LET, STRUCT, EXT, ON, IS, FUNC, TAKES, RETURNS, RT, INTERNAL;

    public static Keyword get(String value) {
        return Arrays.stream(values())
                .filter(keyword -> keyword.toString().toLowerCase().equals(value))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return super.name().toLowerCase();
    }

    public static String generateRegex() {
        StringBuilder builder = new StringBuilder("^(");
        for (int i = 0; i < values().length; i++) {
            builder.append(values()[i].toString());
            if (i != values().length - 1) {
                builder.append("|");
            }
        }
        builder.append(")?(?=\\s)");
        return builder.toString();
    }

    @Override
    public TokenType type() {
        return TokenType.KEYWORD;
    }

    @Override
    public String value() {
        return toString();
    }
}
