package me.redstom.minimal.compiler.exceptions;

public class UnknownTokenException extends LanguageException {
    public UnknownTokenException(int position, int line, int col, String input) {
        super(STR."""

                Unknown token at position \{line}:\{col} -> "\{input.substring(position, Math.min(input.length(), position + 10)).strip()}\"""");
    }
}
