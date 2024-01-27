package me.redstom.minimal.compiler.parser.nodes;

import java.util.List;

public record Block(
        List<Instruction> instructions
) {
}
