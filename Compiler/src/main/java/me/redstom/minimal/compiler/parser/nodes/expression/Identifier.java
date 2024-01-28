package me.redstom.minimal.compiler.parser.nodes.expression;

public record Identifier(
        String value
) implements Expression {
}
