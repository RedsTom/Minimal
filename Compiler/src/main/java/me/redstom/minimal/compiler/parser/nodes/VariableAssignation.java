package me.redstom.minimal.compiler.parser.nodes;

import me.redstom.minimal.compiler.parser.nodes.expression.Expression;

public record VariableAssignation(
        long line,
        long column,
        FieldAccessor accessor,
        Expression value
) implements Instruction, ASTNode {
}
