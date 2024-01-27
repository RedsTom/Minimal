package me.redstom.minimal.compiler.parser.nodes.type;

import java.util.List;

public record Type(
        String name,
        List<Type> generics
) {
}
