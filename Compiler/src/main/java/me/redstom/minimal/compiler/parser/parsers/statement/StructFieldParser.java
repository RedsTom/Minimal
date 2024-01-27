package me.redstom.minimal.compiler.parser.parsers.statement;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.type.Type;
import me.redstom.minimal.compiler.parser.nodes.struct.StructField;
import me.redstom.minimal.compiler.parser.parsers.IParser;

@Parses(StructField.class)
public class StructFieldParser implements IParser<StructField> {

    @Override
    public StructField parse(ParsingContext context) throws LanguageException {
        String name = context.eat(TokenType.IDENTIFIER).value();
        context.eat(TokenType.COLON);
        Type type = context.parse(Type.class);

        return new StructField(name, type);
    }

}
