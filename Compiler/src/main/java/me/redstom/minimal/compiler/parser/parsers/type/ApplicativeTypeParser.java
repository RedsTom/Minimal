package me.redstom.minimal.compiler.parser.parsers.type;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.type.ApplicativeType;
import me.redstom.minimal.compiler.parser.nodes.type.Type;
import me.redstom.minimal.compiler.parser.parsers.IParser;

import java.util.ArrayList;
import java.util.Collections;

@Parses(ApplicativeType.class)
public class ApplicativeTypeParser implements IParser<ApplicativeType> {
    @Override
    public ApplicativeType parse(ParsingContext context) throws LanguageException {
        Type type = context.parse(Type.class);
        var generics = new ArrayList<ApplicativeType>();

        if (context.lookahead(TokenType.LEFT_RAFTER)) {
            context.eat(TokenType.LEFT_RAFTER);

            while (!context.lookahead(TokenType.RIGHT_RAFTER)) {
                generics.add(context.parse(ApplicativeType.class));

                if (context.lookahead(TokenType.COMMA)) {
                    context.eat(TokenType.COMMA);
                } else {
                    break;
                }
            }

            context.eat(TokenType.RIGHT_RAFTER);
        }

        return new ApplicativeType(type, Collections.unmodifiableList(generics));
    }
}
