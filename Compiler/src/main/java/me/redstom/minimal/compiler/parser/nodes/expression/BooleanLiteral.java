package me.redstom.minimal.compiler.parser.nodes.expression;

public record BooleanLiteral(
        boolean value
) implements Expression {
}
