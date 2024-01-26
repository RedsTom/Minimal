package me.redstom.minimal.compiler.parser.ast.statement;

import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.ast.IParser;
import me.redstom.minimal.compiler.parser.ast.TypeParser;

@Parses(StructFieldParser.StructField.class)
public class StructFieldParser implements IParser<StructFieldParser.StructField> {

    @Override
    public StructField parse(ParsingContext context) {
        String name = context.eat(TokenType.IDENTIFIER).value();
        context.eat(TokenType.COLON);
        TypeParser.Type type = context.parse(TypeParser.Type.class);

        return new StructField(name, type);
    }

    public record StructField(
            String name,
            TypeParser.Type type
    ) implements StatementParser.Statement {
    }
}
