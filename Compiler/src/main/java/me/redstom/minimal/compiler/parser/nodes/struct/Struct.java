package me.redstom.minimal.compiler.parser.nodes.struct;

import me.redstom.minimal.compiler.parser.nodes.Parameter;
import me.redstom.minimal.compiler.parser.nodes.Statement;
import me.redstom.minimal.compiler.parser.nodes.type.DeclarativeType;

import java.util.List;

public record Struct(
        DeclarativeType type,
        List<Parameter> fields
) implements Statement {
}
