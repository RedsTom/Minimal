package me.redstom.minimal.compiler.parser.parsers;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.parser.ParsingContext;

public interface IParser<T> {

    T parse(ParsingContext context) throws LanguageException;
}
