package me.redstom.minimal.compiler.parser.parsers;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.Block;

@Parses(Block.class)
public class BlockParser implements IParser<Block> {
    @Override
    public Block parse(ParsingContext context) throws LanguageException {
        return null;
    }
}
