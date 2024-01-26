package me.redstom.minimal.compiler.exceptions;

import me.redstom.minimal.compiler.lexer.Token;
import me.redstom.minimal.compiler.lexer.TokenType;

public class ParsingException extends RuntimeException {

    public ParsingException(String valueExpected, Token actual) {
        super(STR."Expected token \"\{valueExpected}\" but got \{actual.type().stringifier().apply(actual)}");
    }

    public ParsingException(TokenType expected, Token actual) {
        super(STR."Expected token type \{expected} but got \{actual.type().stringifier().apply(actual)}");
    }

    public ParsingException(TokenType expected) {
        super(STR."Expected token type \{expected} but got end of file");
    }

}
