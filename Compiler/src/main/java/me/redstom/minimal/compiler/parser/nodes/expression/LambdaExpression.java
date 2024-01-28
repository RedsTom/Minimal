package me.redstom.minimal.compiler.parser.nodes.expression;

import me.redstom.minimal.compiler.parser.nodes.Block;
import me.redstom.minimal.compiler.parser.nodes.Parameter;

import java.util.List;

public record LambdaExpression(
        List<Parameter> parameters,
        Block body
) implements Expression {
}
