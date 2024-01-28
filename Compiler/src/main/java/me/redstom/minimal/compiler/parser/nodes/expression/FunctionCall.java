package me.redstom.minimal.compiler.parser.nodes.expression;

import me.redstom.minimal.compiler.parser.nodes.Instruction;

import java.util.List;

public record FunctionCall(
        Expression target,
        String name,
        List<Expression> arguments,
        List<String> joiners
) implements Expression, Instruction {
}
