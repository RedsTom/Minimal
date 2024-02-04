package me.redstom.minimal.compiler.parser.parsers.expression;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.expression.NumericLiteral;
import me.redstom.minimal.compiler.parser.parsers.IParser;

@Parses(NumericLiteral.class)
public class NumericLiteralParser implements IParser<NumericLiteral> {
    @Override
    public NumericLiteral parse(ParsingContext context) throws LanguageException {
        double value = Double.parseDouble(context.eat(TokenType.NUMBER).value());
        return new NumericLiteral(context.info().line(), context.info().column(), value);
    }
}
