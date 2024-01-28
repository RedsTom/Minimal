package me.redstom.minimal.compiler.parser.nodes.expression;

import me.redstom.minimal.compiler.parser.nodes.type.ApplicativeType;

import java.util.Map;

public record StructInstantiation(
        ApplicativeType type,
        Map<String, Expression> fields
) implements Expression {
}
