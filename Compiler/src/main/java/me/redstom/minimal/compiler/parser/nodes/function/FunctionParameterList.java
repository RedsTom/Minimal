package me.redstom.minimal.compiler.parser.nodes.function;

import me.redstom.minimal.compiler.parser.nodes.Parameter;

import java.util.List;

public record FunctionParameterList(
        List<Parameter> parameters,
        List<String> joiners
) {
}
