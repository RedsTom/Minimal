package me.redstom.minimal.compiler.parser.nodes.extension;

import me.redstom.minimal.compiler.parser.nodes.Statement;
import me.redstom.minimal.compiler.parser.nodes.function.Function;
import me.redstom.minimal.compiler.parser.nodes.type.Type;

import java.util.List;

public record Extension(
        Type type,
        List<Function> functions
) implements Statement {
}
