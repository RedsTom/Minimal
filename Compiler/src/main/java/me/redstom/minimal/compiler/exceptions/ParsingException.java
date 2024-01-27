package me.redstom.minimal.compiler.exceptions;

import me.redstom.minimal.compiler.lexer.Token;
import me.redstom.minimal.compiler.lexer.TokenType;

public class ParsingException extends LanguageException {

    public ParsingException(String valueExpected, Token actual) {
        super(STR."\{actual.line()}:\{actual.column()} Unexpected \"\{actual.type().stringifier().apply(actual)}\". Expected \"\{valueExpected}\"");
    }

    public ParsingException(TokenType expected, Token actual) {
        super(STR."\{actual.line()}:\{actual.column()} Unexpected \"\{actual.type().stringifier().apply(actual)}\". Expected \{tryStringify(expected)}");
    }

    public ParsingException(TokenType expected) {
        super(STR."Expected \{tryStringify(expected)} but got end of file");
    }

    private static String tryStringify(TokenType type) {
        try {
            return STR."\"\{type.stringifier().apply(null)}\"";
        } catch (Exception e) {
            return type.name();
        }
    }
}
