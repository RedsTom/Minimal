package me.redstom.minimal.compiler.parser.nodes.function;

import me.redstom.minimal.compiler.parser.nodes.ASTNode;
import me.redstom.minimal.compiler.parser.nodes.Block;
import me.redstom.minimal.compiler.parser.nodes.Statement;
import me.redstom.minimal.compiler.parser.nodes.type.ApplicativeType;
import me.redstom.minimal.compiler.parser.nodes.type.DeclarativeType;
import me.redstom.minimal.compiler.parser.nodes.type.Type;

import java.util.List;
import java.util.Optional;

public record Function(
        long line,
        long column,
        String name,
        List<Type> generics,
        FunctionParameterList parameters,
        Optional<ApplicativeType> returnType,
        Block body,
        boolean isInternal
) implements Statement, ASTNode {
}
