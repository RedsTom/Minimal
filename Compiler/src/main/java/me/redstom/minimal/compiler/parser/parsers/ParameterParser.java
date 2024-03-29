package me.redstom.minimal.compiler.parser.parsers;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.type.ApplicativeType;
import me.redstom.minimal.compiler.parser.nodes.type.Type;
import me.redstom.minimal.compiler.parser.nodes.Parameter;

@Parses(Parameter.class)
public class ParameterParser implements IParser<Parameter> {

    @Override
    public Parameter parse(ParsingContext context) throws LanguageException {
        String name = context.eat(TokenType.IDENTIFIER).value();
        context.eat(TokenType.COLON);
        ApplicativeType type = context.parse(ApplicativeType.class);

        return new Parameter(context.info().line(), context.info().column(), name, type);
    }

}
