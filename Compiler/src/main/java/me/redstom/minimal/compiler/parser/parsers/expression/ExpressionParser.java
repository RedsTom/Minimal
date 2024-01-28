package me.redstom.minimal.compiler.parser.parsers.expression;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.Token;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.expression.*;
import me.redstom.minimal.compiler.parser.nodes.FieldAccessor;
import me.redstom.minimal.compiler.parser.parsers.IParser;

@Parses(Expression.class)
public class ExpressionParser implements IParser<Expression> {
    @Override
    public Expression parse(ParsingContext context) throws LanguageException {
        Token token = context.upcomingTokens().peek();
        return switch (token.type()) {
            case NUMBER -> context.parse(NumericLiteral.class);
            case STRING -> context.parse(StringLiteral.class);
            case BOOLEAN -> context.parse(BooleanLiteral.class);
            case IDENTIFIER -> {
                try {
                    ParsingContext copy = context.copyClone();
                    StructInstantiation instantiation = copy.parse(StructInstantiation.class);

                    context.upcomingTokens().clear();
                    context.upcomingTokens().addAll(copy.upcomingTokens());

                    yield instantiation;
                } catch (LanguageException e) {
                    try {
                        yield context.parse(FieldAccessor.class);
                    } catch (LanguageException eprime) {
                        throw new LanguageException(e.getMessage() + "\n\tor\n" + eprime.getMessage());
                    }
                }
            }
            case LEFT_PAREN -> context.parse(FunctionCall.class);
            case AROBASE -> context.parse(LambdaExpression.class);
            case LEFT_BRACKET -> context.parse(List.class);
            default ->
                    throw new LanguageException(token.line() + ":" + token.column() + " Unknown token type " + token.type());
        };
    }
}
