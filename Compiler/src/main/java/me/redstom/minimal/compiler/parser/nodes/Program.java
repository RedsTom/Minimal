package me.redstom.minimal.compiler.parser.nodes;

import java.util.List;

public record Program(
        List<Statement> statements
) {
}
