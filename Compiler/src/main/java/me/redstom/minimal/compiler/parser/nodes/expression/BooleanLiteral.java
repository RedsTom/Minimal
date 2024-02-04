package me.redstom.minimal.compiler.parser.nodes.expression;

import me.redstom.minimal.compiler.parser.nodes.ASTNode;

public record BooleanLiteral(
        long line,
        long column,
        boolean value
) implements Expression, ASTNode {
}
