package me.redstom.minimal.compiler.parser.ast;

import me.redstom.minimal.compiler.parser.ParsingContext;

public interface IParser<T> {

    T parse(ParsingContext context);
}
