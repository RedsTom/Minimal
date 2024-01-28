package me.redstom.minimal.compiler.parser.nodes.function;

import me.redstom.minimal.compiler.parser.nodes.Block;
import me.redstom.minimal.compiler.parser.nodes.Statement;
import me.redstom.minimal.compiler.parser.nodes.type.ApplicativeType;
import me.redstom.minimal.compiler.parser.nodes.type.DeclarativeType;

import java.util.List;
import java.util.Optional;

public record Function(
        String name,
        List<DeclarativeType> generics,
        FunctionParameterList parameters,
        Optional<ApplicativeType> returnType,
        Block body,
        boolean isInternal
) implements Statement {
}
