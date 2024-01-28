package me.redstom.minimal.compiler.parser.nodes.expression;

public record NumericLiteral(
        double value
) implements Expression {
}
