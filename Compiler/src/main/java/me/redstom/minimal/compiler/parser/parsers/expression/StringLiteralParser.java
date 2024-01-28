package me.redstom.minimal.compiler.parser.parsers.expression;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.expression.StringLiteral;
import me.redstom.minimal.compiler.parser.parsers.IParser;

@Parses(StringLiteral.class)
public class StringLiteralParser implements IParser<StringLiteral> {
    @Override
    public StringLiteral parse(ParsingContext context) throws LanguageException {
        return new StringLiteral(context.eat(TokenType.STRING).value());
    }
}
