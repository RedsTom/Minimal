package me.redstom.minimal.compiler.parser.nodes;

import me.redstom.minimal.compiler.parser.nodes.expression.Expression;
import me.redstom.minimal.compiler.parser.nodes.type.ApplicativeType;

import java.util.Optional;

public record VariableDeclaration(
        long line,
        long column,
        String name,
        Optional<ApplicativeType> type,
        Optional<Expression> value
) implements Instruction, ASTNode {
}
