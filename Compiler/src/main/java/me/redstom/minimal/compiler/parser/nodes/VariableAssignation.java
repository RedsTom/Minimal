package me.redstom.minimal.compiler.parser.nodes;

import me.redstom.minimal.compiler.parser.nodes.expression.Expression;

public record VariableAssignation(
        FieldAccessor accessor,
        Expression value
) implements Instruction {
}
