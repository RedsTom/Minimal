package me.redstom.minimal.compiler.parser.parsers.function;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.Keyword;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.expression.Expression;
import me.redstom.minimal.compiler.parser.nodes.function.Return;
import me.redstom.minimal.compiler.parser.parsers.IParser;

@Parses(Return.class)
public class ReturnParser implements IParser<Return> {
    @Override
    public Return parse(ParsingContext context) throws LanguageException {
        context.eat(Keyword.RT);
        Expression value = context.parse(Expression.class);

        return new Return(value);
    }
}
