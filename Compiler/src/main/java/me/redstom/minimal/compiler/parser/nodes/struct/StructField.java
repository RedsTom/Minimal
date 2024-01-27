package me.redstom.minimal.compiler.parser.nodes.struct;

import me.redstom.minimal.compiler.parser.nodes.Statement;
import me.redstom.minimal.compiler.parser.nodes.type.Type;

public record StructField(
        String name,
        Type type
) implements Statement {
}
