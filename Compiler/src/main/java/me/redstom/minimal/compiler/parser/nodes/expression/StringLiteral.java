package me.redstom.minimal.compiler.parser.nodes.expression;

public record StringLiteral(
        String value
) implements Expression {
}
