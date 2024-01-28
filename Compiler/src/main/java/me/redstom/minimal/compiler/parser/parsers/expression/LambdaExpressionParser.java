package me.redstom.minimal.compiler.parser.parsers.expression;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.Block;
import me.redstom.minimal.compiler.parser.nodes.Parameter;
import me.redstom.minimal.compiler.parser.nodes.expression.LambdaExpression;
import me.redstom.minimal.compiler.parser.parsers.IParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Parses(LambdaExpression.class)
public class LambdaExpressionParser implements IParser<LambdaExpression> {
    @Override
    public LambdaExpression parse(ParsingContext context) throws LanguageException {
        context.eat(TokenType.AROBASE);

        List<Parameter> parameters = new ArrayList<>();
        if (!context.lookahead(TokenType.ARROW)) {
            parameters.add(context.parse(Parameter.class));
            while (context.lookahead(TokenType.COMMA)) {
                context.eat(TokenType.COMMA);
                parameters.add(context.parse(Parameter.class));
            }
        }

        context.eat(TokenType.ARROW);
        Block body = context.parse(Block.class);

        return new LambdaExpression(Collections.unmodifiableList(parameters), body);
    }
}
