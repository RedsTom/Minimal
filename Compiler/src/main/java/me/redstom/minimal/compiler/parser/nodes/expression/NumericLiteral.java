package me.redstom.minimal.compiler.parser.nodes.expression;

import me.redstom.minimal.compiler.parser.nodes.ASTNode;

public record NumericLiteral(
        long line,
        long column,
        double value
) implements Expression, ASTNode {
}
