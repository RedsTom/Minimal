package me.redstom.minimal.compiler.parser.parsers.type;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.type.DeclarativeType;
import me.redstom.minimal.compiler.parser.nodes.type.Type;
import me.redstom.minimal.compiler.parser.parsers.IParser;

import java.util.ArrayList;
import java.util.Collections;

@Parses(DeclarativeType.class)
public class DeclarativeTypeParser implements IParser<DeclarativeType> {
    @Override
    public DeclarativeType parse(ParsingContext context) throws LanguageException {
        Type name = context.parse(Type.class);
        var generics = new ArrayList<Type>();

        if (context.lookahead(TokenType.LEFT_RAFTER)) {
            context.eat(TokenType.LEFT_RAFTER);

            while (!context.lookahead(TokenType.RIGHT_RAFTER)) {
                generics.add(context.parse(Type.class));

                if (context.lookahead(TokenType.COMMA)) {
                    context.eat(TokenType.COMMA);
                } else {
                    break;
                }
            }

            context.eat(TokenType.RIGHT_RAFTER);
        }

        return new DeclarativeType(name, Collections.unmodifiableList(generics));
    }
}
