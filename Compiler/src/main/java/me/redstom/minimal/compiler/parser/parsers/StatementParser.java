package me.redstom.minimal.compiler.parser.parsers;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.exceptions.MissingParserException;
import me.redstom.minimal.compiler.lexer.Keyword;
import me.redstom.minimal.compiler.lexer.Token;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.nodes.expression.FunctionCall;
import me.redstom.minimal.compiler.parser.nodes.extension.Extension;
import me.redstom.minimal.compiler.parser.nodes.function.Function;
import me.redstom.minimal.compiler.parser.parsers.IParser;
import me.redstom.minimal.compiler.parser.nodes.Statement;
import me.redstom.minimal.compiler.parser.nodes.struct.Struct;
import me.redstom.minimal.compiler.parser.parsers.expression.FunctionCallParser;

@Parses(Statement.class)
public class StatementParser implements IParser<Statement> {


    @Override
    public Statement parse(ParsingContext context) throws LanguageException {
        Token token = context.upcomingTokens().peek();
        return switch (token.type()) {
            case KEYWORD -> switch (Keyword.get(token.value())) {
                case Keyword.STRUCT -> context.parse(Struct.class);
                case Keyword.EXT -> context.parse(Extension.class);
                case Keyword.FUNC -> context.parse(Function.class);
                default -> throw new MissingParserException(token);
            };
            default -> throw new LanguageException("Unknown token type " + token.type());
        };
    }

}
