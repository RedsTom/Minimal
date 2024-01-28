package me.redstom.minimal.compiler.parser.parsers.expression;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.Token;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.expression.*;
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
            case IDENTIFIER -> context.parse(Identifier.class);
            case LEFT_PAREN -> context.parse(FunctionCall.class);
            case AROBASE -> context.parse(LambdaExpression.class);
            default -> throw new LanguageException(token.line() + ":" + token.column() + " Unknown token type " + token.type());
        };
    }
}
