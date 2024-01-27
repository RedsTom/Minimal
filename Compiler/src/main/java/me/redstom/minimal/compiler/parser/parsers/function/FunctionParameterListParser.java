package me.redstom.minimal.compiler.parser.parsers.function;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.Keyword;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.Parameter;
import me.redstom.minimal.compiler.parser.nodes.function.FunctionParameterList;
import me.redstom.minimal.compiler.parser.parsers.IParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Parses(FunctionParameterList.class)
public class FunctionParameterListParser implements IParser<FunctionParameterList> {
    @Override
    public FunctionParameterList parse(ParsingContext context) throws LanguageException {
        if (!context.lookahead(Keyword.TAKES)) {
            return new FunctionParameterList(Collections.emptyList(), Collections.emptyList());
        }

        context.eat(Keyword.TAKES);
        List<Parameter> parameters = new ArrayList<>();
        List<String> joiners = new ArrayList<>();

        parameters.add(context.parse(Parameter.class));

        while (context.lookahead(TokenType.COMMA) || context.lookahead(TokenType.STRING)) {
            if (context.lookahead(TokenType.STRING)) {
                joiners.add(context.eat(TokenType.STRING).value());
                parameters.add(context.parse(Parameter.class));
                continue;
            }

            context.eat(TokenType.COMMA);
            joiners.add(",");
            parameters.add(context.parse(Parameter.class));
        }


        return new FunctionParameterList(Collections.unmodifiableList(parameters), Collections.unmodifiableList(joiners));
    }
}
