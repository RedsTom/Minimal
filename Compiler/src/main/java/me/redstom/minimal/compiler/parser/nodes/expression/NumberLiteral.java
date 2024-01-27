package me.redstom.minimal.compiler.parser.nodes.expression;

import me.redstom.minimal.compiler.parser.nodes.Expression;

public record NumberLiteral(
        double value
) implements Expression {
}
