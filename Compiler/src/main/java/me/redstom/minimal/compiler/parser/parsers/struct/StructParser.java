package me.redstom.minimal.compiler.parser.parsers.struct;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.Keyword;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.struct.Struct;
import me.redstom.minimal.compiler.parser.nodes.Parameter;
import me.redstom.minimal.compiler.parser.nodes.type.DeclarativeType;
import me.redstom.minimal.compiler.parser.parsers.IParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Parses(Struct.class)
public class StructParser implements IParser<Struct> {

    @Override
    public Struct parse(ParsingContext context) throws LanguageException {
        context.eat(Keyword.STRUCT);
        DeclarativeType type = context.parse(DeclarativeType.class);
        context.eat(Keyword.IS);

        List<Parameter> fields = new ArrayList<>();
        while (!context.lookahead(TokenType.DOT)) {
            fields.add(context.parse(Parameter.class));
        }
        context.eat(TokenType.DOT);

        return new Struct(type, Collections.unmodifiableList(fields));
    }

}
