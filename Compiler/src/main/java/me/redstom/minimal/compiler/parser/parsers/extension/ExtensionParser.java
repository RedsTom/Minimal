package me.redstom.minimal.compiler.parser.parsers.extension;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.Keyword;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.extension.Extension;
import me.redstom.minimal.compiler.parser.nodes.function.Function;
import me.redstom.minimal.compiler.parser.nodes.type.Type;
import me.redstom.minimal.compiler.parser.parsers.IParser;

import java.util.ArrayList;
import java.util.Collections;

@Parses(Extension.class)
public class ExtensionParser implements IParser<Extension> {
    @Override
    public Extension parse(ParsingContext context) throws LanguageException {
        context.eat(Keyword.EXT);
        context.eat(Keyword.ON);

        var type = context.parse(Type.class);

        context.eat(Keyword.IS);

        var functions = new ArrayList<Function>();
        while (!context.lookahead(TokenType.DOT)) {
            functions.add(context.parse(Function.class));
        }

        context.eat(TokenType.DOT);

        return new Extension(type, Collections.unmodifiableList(functions));
    }
}
