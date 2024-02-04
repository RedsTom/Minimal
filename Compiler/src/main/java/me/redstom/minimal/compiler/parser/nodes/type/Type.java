package me.redstom.minimal.compiler.parser.nodes.type;

import me.redstom.minimal.compiler.parser.nodes.ASTNode;

public record Type(
        long line,
        long column,
        String name
) implements ASTNode {
}
