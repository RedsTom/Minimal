package me.redstom.minimal.compiler.parser.nodes.type;

import java.util.List;

public record ApplicativeType(
        Type type,
        List<ApplicativeType> generics
) {
}
