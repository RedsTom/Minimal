package me.redstom.minimal.compiler.lexer;

public interface TokenValue {
    TokenType type();
    String value();
}
