package me.redstom.minimal.compiler.parser.nodes;

import java.util.List;

public record Block(
        long line,
        long column,
        List<Instruction> instructions
) implements ASTNode {
}
