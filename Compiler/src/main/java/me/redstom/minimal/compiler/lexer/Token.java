package me.redstom.minimal.compiler.lexer;

public record Token(
        TokenType type,
        String value,
        int line,
        int column,
        int length,
        int position
) {
}
