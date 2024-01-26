package me.redstom.minimal.compiler.parser.ast.statement;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ast.IParser;
import me.redstom.minimal.compiler.parser.ast.TypeParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Parses(StructParser.Struct.class)
public class StructParser implements IParser<StructParser.Struct> {

    @Override
    public Struct parse(ParsingContext context) throws LanguageException {
        context.eat(TokenType.KEYWORD, "struct");
        TypeParser.Type name = context.parse(TypeParser.Type.class);
        context.eat(TokenType.KEYWORD, "is");

        List<StructFieldParser.StructField> fields = new ArrayList<>();
        while (!context.lookahead(TokenType.DOT)) {
            fields.add(context.parse(StructFieldParser.StructField.class));
        }
        context.eat(TokenType.DOT);

        return new Struct(name, fields.toArray(StructFieldParser.StructField[]::new));
    }

    public record Struct(
            TypeParser.Type type,
            StructFieldParser.StructField[] fields
    ) implements StatementParser.Statement {
    }
}
