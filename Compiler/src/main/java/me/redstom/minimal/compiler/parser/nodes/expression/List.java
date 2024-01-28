package me.redstom.minimal.compiler.parser.nodes.expression;

public record List(
    java.util.List<Expression> content
) implements Expression {
}
