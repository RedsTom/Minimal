package me.redstom.minimal.compiler.parser.parsers;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.Block;
import me.redstom.minimal.compiler.parser.nodes.Instruction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Parses(Block.class)
public class BlockParser implements IParser<Block> {
    @Override
    public Block parse(ParsingContext context) throws LanguageException {
        List<Instruction> instructions = new ArrayList<>();

        while(!context.lookahead(TokenType.DOT)) {
            var instruction = context.parse(Instruction.class);
            instructions.add(instruction);
        }

        context.eat(TokenType.DOT);

        return new Block(Collections.unmodifiableList(instructions));
    }
}
