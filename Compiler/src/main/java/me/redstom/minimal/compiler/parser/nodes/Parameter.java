package me.redstom.minimal.compiler.parser.nodes;

import me.redstom.minimal.compiler.parser.nodes.type.Type;

public record Parameter(
        String name,
        Type type
) implements Statement {
}
