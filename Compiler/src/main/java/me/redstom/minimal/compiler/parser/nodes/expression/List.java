package me.redstom.minimal.compiler.parser.nodes.expression;

import me.redstom.minimal.compiler.parser.nodes.ASTNode;

public record List(
        long line,
        long column,
    java.util.List<Expression> content
) implements Expression, ASTNode {
}
