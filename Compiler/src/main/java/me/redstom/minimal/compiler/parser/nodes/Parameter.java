package me.redstom.minimal.compiler.parser.nodes;

import me.redstom.minimal.compiler.parser.nodes.type.ApplicativeType;

public record Parameter(
        String name,
        ApplicativeType type
) implements Statement {
}
