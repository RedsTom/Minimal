package me.redstom.minimal.compiler.parser.ast.statement;

import me.redstom.minimal.compiler.lexer.Token;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ast.IParser;

@Parses(StatementParser.Statement.class)
public class StatementParser implements IParser<StatementParser.Statement> {


    @Override
    public Statement parse(ParsingContext context) {
        Token token = context.upcomingTokens().peek();
        return switch (token.type()) {
            case KEYWORD -> switch (token.value()) {
                case "struct" -> context.parse(StructParser.Struct.class);
                default -> throw new RuntimeException("Unknown keyword " + token.value());
            };
            default -> throw new RuntimeException("Unknown token type " + token.type());
        };
    }

    public interface Statement {
    }
}
