package me.redstom.minimal.compiler.parser.nodes.function;

import me.redstom.minimal.compiler.parser.nodes.expression.Expression;
import me.redstom.minimal.compiler.parser.nodes.Instruction;

public record Return(
        Expression expression
) implements Instruction {
}
