package me.redstom.minimal.compiler.parser.parsers;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.Token;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.Expression;
import me.redstom.minimal.compiler.parser.nodes.expression.NumberLiteral;

@Parses(Expression.class)
public class ExpressionParser implements IParser<Expression> {
    @Override
    public Expression parse(ParsingContext context) throws LanguageException {
        Token token = context.upcomingTokens().peek();
        return switch (token.type()) {
            case NUMBER -> {
                context.eat(token.type());
                yield new NumberLiteral(Double.parseDouble(token.value()));
            }
            default -> throw new LanguageException("Unknown token type " + token.type());
        };
    }
}
