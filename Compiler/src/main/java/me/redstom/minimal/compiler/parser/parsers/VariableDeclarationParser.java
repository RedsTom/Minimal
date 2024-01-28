package me.redstom.minimal.compiler.parser.parsers;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.Keyword;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.expression.Expression;
import me.redstom.minimal.compiler.parser.nodes.VariableDeclaration;
import me.redstom.minimal.compiler.parser.nodes.type.ApplicativeType;

import java.util.Optional;

@Parses(VariableDeclaration.class)
public class VariableDeclarationParser implements IParser<VariableDeclaration> {
    @Override
    public VariableDeclaration parse(ParsingContext context) throws LanguageException {
        context.eat(Keyword.LET);
        String name = context.eat(TokenType.IDENTIFIER).value();

        ApplicativeType type = null;
        if (context.lookahead(TokenType.COLON)) {
            context.eat(TokenType.COLON);
            type = context.parse(ApplicativeType.class);
        }

        Expression value = null;
        if (context.lookahead(TokenType.EQUAL)) {
            context.eat(TokenType.EQUAL);
            value = context.parse(Expression.class);
        }

        return new VariableDeclaration(name, Optional.ofNullable(type), Optional.ofNullable(value));
    }
}
