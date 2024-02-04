package me.redstom.minimal.compiler.parser.parsers.type;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.type.Type;
import me.redstom.minimal.compiler.parser.parsers.IParser;

@Parses(Type.class)
public class TypeParser implements IParser<Type> {

    @Override
    public Type parse(ParsingContext context) throws LanguageException {
        String name = context.eat(TokenType.IDENTIFIER).value();

        return new Type(context.info().line(), context.info().column(), name);
    }

}
