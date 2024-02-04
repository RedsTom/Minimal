package me.redstom.minimal.compiler.parser.nodes;

import me.redstom.minimal.compiler.parser.nodes.Parameter;
import me.redstom.minimal.compiler.parser.nodes.Statement;
import me.redstom.minimal.compiler.parser.nodes.type.ApplicativeType;
import me.redstom.minimal.compiler.parser.nodes.type.DeclarativeType;

import java.util.List;

public record Struct(
        long line,
        long column,
        DeclarativeType type,
        List<Parameter> fields,
        List<ApplicativeType> implementations
) implements Statement, ASTNode {
}
