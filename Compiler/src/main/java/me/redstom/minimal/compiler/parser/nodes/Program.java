package me.redstom.minimal.compiler.parser.nodes;

import java.util.List;

public record Program(
        long line,
        long column,
        List<Statement> statements
) implements ASTNode {
}
