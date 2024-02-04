package me.redstom.minimal.compiler.parser.nodes.function;

import me.redstom.minimal.compiler.parser.nodes.ASTNode;
import me.redstom.minimal.compiler.parser.nodes.Parameter;

import java.util.List;

public record FunctionParameterList(
        long line,
        long column,
        List<Parameter> parameters,
        List<String> joiners
) implements ASTNode {
}
