package me.redstom.minimal.compiler.parser.parsers.expression;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.expression.Identifier;
import me.redstom.minimal.compiler.parser.parsers.IParser;

@Parses(Identifier.class)
public class IdentifierParser implements IParser<Identifier> {
    @Override
    public Identifier parse(ParsingContext context) throws LanguageException {
        return new Identifier(context.eat(TokenType.IDENTIFIER).value());
    }
}
