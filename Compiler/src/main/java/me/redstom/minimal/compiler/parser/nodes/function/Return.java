package me.redstom.minimal.compiler.parser.nodes.function;

import me.redstom.minimal.compiler.parser.nodes.ASTNode;
import me.redstom.minimal.compiler.parser.nodes.expression.Expression;
import me.redstom.minimal.compiler.parser.nodes.Instruction;

public record Return(
        long line,
        long column,
        Expression expression
) implements Instruction, ASTNode {
}
