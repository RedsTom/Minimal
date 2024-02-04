package me.redstom.minimal.compiler.parser.parsers.expression;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.expression.BooleanLiteral;
import me.redstom.minimal.compiler.parser.parsers.IParser;

@Parses(BooleanLiteral.class)
public class BooleanLiteralParser implements IParser<BooleanLiteral> {

    @Override
    public BooleanLiteral parse(ParsingContext context) throws LanguageException {
        String value = context.eat(TokenType.BOOLEAN).value();

        return new BooleanLiteral(context.info().line(), context.info().column(), Boolean.parseBoolean(value));
    }
}
