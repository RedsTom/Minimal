package me.redstom.minimal.compiler.parser.parsers;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.FieldAccessor;
import me.redstom.minimal.compiler.parser.nodes.VariableAssignation;
import me.redstom.minimal.compiler.parser.nodes.expression.Expression;

@Parses(VariableAssignation.class)
public class VariableAssignationParser implements IParser<VariableAssignation> {
    @Override
    public VariableAssignation parse(ParsingContext context) throws LanguageException {
        FieldAccessor accessor = context.parse(FieldAccessor.class);
        context.eat(TokenType.EQUAL);
        Expression expression = context.parse(Expression.class);

        return new VariableAssignation(accessor, expression);
    }
}
