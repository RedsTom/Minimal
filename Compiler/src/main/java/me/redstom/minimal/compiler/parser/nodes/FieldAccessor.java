package me.redstom.minimal.compiler.parser.nodes;

import me.redstom.minimal.compiler.parser.nodes.expression.Expression;

import java.util.Optional;

public record FieldAccessor(
        String name,
        Optional<FieldAccessor> on
) implements Expression {
}
