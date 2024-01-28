package me.redstom.minimal.compiler.parser.parsers;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.Keyword;
import me.redstom.minimal.compiler.lexer.Token;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.Instruction;
import me.redstom.minimal.compiler.parser.nodes.VariableDeclaration;
import me.redstom.minimal.compiler.parser.nodes.expression.FunctionCall;
import me.redstom.minimal.compiler.parser.nodes.function.Return;

@Parses(Instruction.class)
public class InstructionParser implements IParser<Instruction> {
    @Override
    public Instruction parse(ParsingContext context) throws LanguageException {
        Token token = context.upcomingTokens().peek();
        return switch (token.type()) {
            case KEYWORD -> switch (Keyword.get(token.value())) {
                case LET -> context.parse(VariableDeclaration.class);
                case RT -> context.parse(Return.class);
                default -> throw new LanguageException("Unknown keyword " + token.value());
            };
            case LEFT_PAREN -> context.parse(FunctionCall.class);
            default -> throw new LanguageException("Unknown token type " + token.type());
        };
    }
}
