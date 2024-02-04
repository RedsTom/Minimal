package me.redstom.minimal.compiler.parser.parsers.expression;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.exceptions.ParsingException;
import me.redstom.minimal.compiler.lexer.Keyword;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.Block;
import me.redstom.minimal.compiler.parser.nodes.expression.Expression;
import me.redstom.minimal.compiler.parser.nodes.expression.FunctionCall;
import me.redstom.minimal.compiler.parser.nodes.function.Function;
import me.redstom.minimal.compiler.parser.nodes.function.FunctionParameterList;
import me.redstom.minimal.compiler.parser.nodes.type.ApplicativeType;
import me.redstom.minimal.compiler.parser.nodes.type.DeclarativeType;
import me.redstom.minimal.compiler.parser.parsers.IParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Parses(FunctionCall.class)
public class FunctionCallParser implements IParser<FunctionCall> {
    @Override
    public FunctionCall parse(ParsingContext context) throws LanguageException {
        try {
            ParsingContext contextCopy = context.copyClone();
            FunctionCall call = tryParseDotCall(contextCopy);

            context.upcomingTokens().clear();
            context.upcomingTokens().addAll(contextCopy.upcomingTokens());

            return call;
        } catch (LanguageException e) {
            ParsingContext contextCopy = context.copyClone();
            FunctionCall call;
            try {
                call = tryParseNormalCall(contextCopy);
            } catch (Exception eprime) {
                throw new ParsingException(context.upcomingTokens().peek(), e.getMessage() + "\n\tor\n" + eprime.getMessage());
            }

            context.upcomingTokens().clear();
            context.upcomingTokens().addAll(contextCopy.upcomingTokens());

            return call;
        }
    }

    private FunctionCall tryParseDotCall(ParsingContext context) throws LanguageException {
        context.eat(TokenType.LEFT_PAREN);

        Expression target = context.parse(Expression.class);
        String name = context.eat(TokenType.IDENTIFIER).value();

        List<Expression> arguments = new ArrayList<>();
        List<String> joiners = new ArrayList<>();

        if (!context.lookahead(TokenType.RIGHT_PAREN)) {
            arguments.add(context.parse(Expression.class));
        }

        while (!context.lookahead(TokenType.RIGHT_PAREN)) {
            if (context.lookahead(TokenType.COMMA)) {
                joiners.add(context.eat(TokenType.COMMA).value());
            } else {
                joiners.add(context.eat(TokenType.IDENTIFIER).value());
            }

            arguments.add(context.parse(Expression.class));
        }

        context.eat(TokenType.RIGHT_PAREN);

        return new FunctionCall(target, name, Collections.unmodifiableList(arguments), Collections.unmodifiableList(joiners));
    }

    private FunctionCall tryParseNormalCall(ParsingContext context) throws LanguageException {
        context.eat(TokenType.LEFT_PAREN);

        String name = context.eat(TokenType.IDENTIFIER).value();

        List<Expression> arguments = new ArrayList<>();
        List<String> joiners = new ArrayList<>();

        if (!context.lookahead(TokenType.RIGHT_PAREN)) {
            arguments.add(context.parse(Expression.class));
        }

        while (!context.lookahead(TokenType.RIGHT_PAREN)) {
            if (context.lookahead(TokenType.COMMA)) {
                joiners.add(context.eat(TokenType.COMMA).value());
            } else {
                joiners.add(context.eat(TokenType.IDENTIFIER).value());
            }

            arguments.add(context.parse(Expression.class));
        }

        context.eat(TokenType.RIGHT_PAREN);

        return new FunctionCall(null, name, Collections.unmodifiableList(arguments), Collections.unmodifiableList(joiners));
    }
}
