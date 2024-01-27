package me.redstom.minimal.compiler.exceptions;

import me.redstom.minimal.compiler.lexer.Token;
import me.redstom.minimal.compiler.lexer.TokenType;

public class MissingParserException extends LanguageException {

    public MissingParserException(Class<?> clazz) {
        super("Missing parser for " + clazz.getSimpleName());
    }

    public MissingParserException(Token token) {
        super("Missing parser for " + token.type().name() + "(" +  token.value() + ")");
    }
}
