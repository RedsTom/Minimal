package me.redstom.minimal.compiler.parser.nodes.type;

import me.redstom.minimal.compiler.parser.nodes.ASTNode;

import java.util.List;

public record ApplicativeType(
        long line,
        long column,
        Type type,
        List<ApplicativeType> generics
) implements ASTNode {
}
