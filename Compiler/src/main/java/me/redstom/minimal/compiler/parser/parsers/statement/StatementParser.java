package me.redstom.minimal.compiler.parser.parsers.statement;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.Token;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.parsers.IParser;
import me.redstom.minimal.compiler.parser.nodes.Statement;
import me.redstom.minimal.compiler.parser.nodes.struct.Struct;

@Parses(Statement.class)
public class StatementParser implements IParser<Statement> {


    @Override
    public Statement parse(ParsingContext context) throws LanguageException {
        Token token = context.upcomingTokens().peek();
        return switch (token.type()) {
            case KEYWORD -> switch (token.value()) {
                case "struct" -> context.parse(Struct.class);
                default -> throw new RuntimeException("Unknown keyword " + token.value());
            };
            default -> throw new RuntimeException("Unknown token type " + token.type());
        };
    }

}
