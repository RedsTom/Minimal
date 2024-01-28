package me.redstom.minimal.compiler.parser.parsers.function;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.Keyword;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.Block;
import me.redstom.minimal.compiler.parser.nodes.function.Function;
import me.redstom.minimal.compiler.parser.nodes.function.FunctionParameterList;
import me.redstom.minimal.compiler.parser.nodes.type.ApplicativeType;
import me.redstom.minimal.compiler.parser.nodes.type.DeclarativeType;
import me.redstom.minimal.compiler.parser.parsers.IParser;

import java.util.ArrayList;
import java.util.Optional;

@Parses(Function.class)
public class FunctionParser implements IParser<Function> {
    @Override
    public Function parse(ParsingContext context) throws LanguageException {
        boolean isInternal = context.lookahead(Keyword.INTERNAL) && context.eat(Keyword.INTERNAL) != null;
        context.eat(Keyword.FUNC);

        String name = context.eat(TokenType.IDENTIFIER).value();

        var generics = new ArrayList<DeclarativeType>();
        if (context.lookahead(TokenType.RIGHT_RAFTER)) {
            context.eat(TokenType.LEFT_RAFTER);

            while (!context.lookahead(TokenType.RIGHT_RAFTER)) {
                generics.add(context.parse(DeclarativeType.class));
            }

            context.eat(TokenType.RIGHT_RAFTER);
        }

        FunctionParameterList parameters = context.parse(FunctionParameterList.class);

        ApplicativeType returnType = null;
        if (context.lookahead(Keyword.RETURNS)) {
            context.eat(Keyword.RETURNS);
            returnType = context.parse(ApplicativeType.class);
        }

        if (isInternal) {
            return new Function(name, generics, parameters, Optional.ofNullable(returnType), null, true);
        }

        context.eat(Keyword.IS);
        var body = context.parse(Block.class);

        return new Function(name, generics, parameters, Optional.ofNullable(returnType), body, false);
    }
}
