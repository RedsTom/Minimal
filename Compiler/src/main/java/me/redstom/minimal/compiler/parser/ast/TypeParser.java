package me.redstom.minimal.compiler.parser.ast;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Parses(TypeParser.Type.class)
public class TypeParser implements IParser<TypeParser.Type> {

    @Override
    public Type parse(ParsingContext context) throws LanguageException {
        String name = context.eat(TokenType.IDENTIFIER).value();
        List<Type> generics = new ArrayList<>();

        if(context.lookahead(TokenType.LEFT_RAFTER)) {
            context.eat(TokenType.LEFT_RAFTER);

            while(context.upcomingTokens().peek().type() != TokenType.RIGHT_RAFTER) {
                generics.add(context.parse(Type.class));
            }

            context.eat(TokenType.RIGHT_RAFTER);
        }

        return new Type(name, Collections.unmodifiableList(generics));
    }

    public record Type(
            String name,
            List<Type> generics
    ) {
    }
}
