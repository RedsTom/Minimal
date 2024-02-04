package me.redstom.minimal.compiler.parser.nodes;

import me.redstom.minimal.compiler.parser.nodes.type.ApplicativeType;

public record Parameter(
        long line,
        long column,
        String name,
        ApplicativeType type
) implements ASTNode {
}
