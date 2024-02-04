package me.redstom.minimal.compiler.parser.parsers.expression;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.exceptions.ParsingException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.expression.Expression;
import me.redstom.minimal.compiler.parser.nodes.expression.StructInstantiation;
import me.redstom.minimal.compiler.parser.nodes.type.ApplicativeType;
import me.redstom.minimal.compiler.parser.parsers.IParser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Parses(StructInstantiation.class)
public class StructInstantiationParser implements IParser<StructInstantiation> {
    @Override
    public StructInstantiation parse(ParsingContext context) throws LanguageException {
        ApplicativeType type = context.parse(ApplicativeType.class);

        context.eat(TokenType.LEFT_CURLY_BRACE);

        Map<String, Expression> fields = new HashMap<>();
        while (!context.lookahead(TokenType.RIGHT_CURLY_BRACE)) {
            String name = context.eat(TokenType.IDENTIFIER).value();

            if(fields.containsKey(name)) {
                throw new ParsingException(context.upcomingTokens().peek(), STR."Field \"\{name}\" is assigned twice!");
            }

            context.eat(TokenType.EQUAL);
            Expression value = context.parse(Expression.class);

            fields.put(name, value);
        }
        context.eat(TokenType.RIGHT_CURLY_BRACE);

        return new StructInstantiation(context.info().line(), context.info().column(), type, Collections.unmodifiableMap(fields));
    }
}
