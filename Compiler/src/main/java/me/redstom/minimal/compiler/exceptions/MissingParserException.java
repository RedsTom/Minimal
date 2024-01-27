package me.redstom.minimal.compiler.exceptions;

public class MissingParserException extends LanguageException {

    public MissingParserException(Class<?> clazz) {
        super("Missing parser for " + clazz.getSimpleName());
    }
}
