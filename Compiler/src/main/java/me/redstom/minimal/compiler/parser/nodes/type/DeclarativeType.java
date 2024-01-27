package me.redstom.minimal.compiler.parser.nodes.type;

import java.util.List;

public record DeclarativeType(
        Type type,
        List<Type> generics
) {
}
