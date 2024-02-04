package me.redstom.minimal.compiler.parser.parsers.expression;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.expression.Expression;
import me.redstom.minimal.compiler.parser.nodes.expression.List;
import me.redstom.minimal.compiler.parser.parsers.IParser;

import java.util.ArrayList;
import java.util.Collections;

@Parses(List.class)
public class ListParser implements IParser<List> {
    @Override
    public List parse(ParsingContext context) throws LanguageException {
        context.eat(TokenType.LEFT_BRACKET);

        java.util.List<Expression> expressions = new ArrayList<>();
        if(!context.lookahead(TokenType.RIGHT_BRACKET)) {
            expressions.add(context.parse(Expression.class));
            while (context.lookahead(TokenType.COMMA)) {
                context.eat(TokenType.COMMA);
                expressions.add(context.parse(Expression.class));
            }
        }
        context.eat(TokenType.RIGHT_BRACKET);

        return new List(context.info().line(), context.info().column(), Collections.unmodifiableList(expressions));
    }
}
