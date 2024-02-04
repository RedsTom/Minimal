package me.redstom.minimal.compiler.parser.nodes.type;

import me.redstom.minimal.compiler.parser.nodes.ASTNode;

import java.util.List;

public record DeclarativeType(
        long line,
        long column,
        Type type,
        List<Type> generics
) implements ASTNode {
}
