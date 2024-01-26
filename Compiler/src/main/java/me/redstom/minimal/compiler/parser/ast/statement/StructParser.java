package me.redstom.minimal.compiler.parser.ast.statement;

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
    public Struct parse(ParsingContext context) {
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

        @Override
        public String toString() {
            return "Struct{" +
                   "type=" + type +
                   ", fields=" + Arrays.toString(fields) +
                   '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Struct struct = (Struct) o;
            return Objects.equals(type, struct.type) && Arrays.equals(fields, struct.fields);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(type);
            result = 31 * result + Arrays.hashCode(fields);
            return result;
        }
    }
}
