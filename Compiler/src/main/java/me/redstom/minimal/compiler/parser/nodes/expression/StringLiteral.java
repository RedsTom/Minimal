package me.redstom.minimal.compiler.parser.nodes.expression;

import me.redstom.minimal.compiler.parser.nodes.ASTNode;

public record StringLiteral(
        long line,
        long column,
        String value
) implements Expression, ASTNode {
}
