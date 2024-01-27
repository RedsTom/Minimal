package me.redstom.minimal.compiler.parser.nodes.struct;

import me.redstom.minimal.compiler.parser.nodes.Statement;
import me.redstom.minimal.compiler.parser.nodes.type.DeclarativeType;
import me.redstom.minimal.compiler.parser.nodes.type.Type;

import java.util.List;

public record Struct(
        DeclarativeType type,
        List<StructField> fields
) implements Statement {
}