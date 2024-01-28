package me.redstom.minimal.compiler.parser.parsers.type;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.FieldAccessor;
import me.redstom.minimal.compiler.parser.parsers.IParser;

import java.util.Optional;

@Parses(FieldAccessor.class)
public class FieldAccessorParser implements IParser<FieldAccessor> {

    @Override
    public FieldAccessor parse(ParsingContext context) throws LanguageException {
        String name = context.eat(TokenType.IDENTIFIER).value();

        FieldAccessor accessor = new FieldAccessor(name, Optional.empty());
        if (context.lookahead(TokenType.COLON)) {
            context.eat(TokenType.COLON);
            FieldAccessor parsed = context.parse(FieldAccessor.class);
            return new FieldAccessor(accessor.name(), Optional.of(parsed));
        }

        return accessor;
    }
}
