package me.redstom.minimal.compiler.parser.ast;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ast.statement.StatementParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Parses(ProgramParser.Program.class)
public class ProgramParser implements IParser<ProgramParser.Program> {

    @Override
    public Program parse(ParsingContext context) throws LanguageException {
        List<StatementParser.Statement> statements = new ArrayList<>();

        while (!context.upcomingTokens().isEmpty()) {
            var statement = context.parse(StatementParser.Statement.class);
            statements.add(statement);
        }

        return new Program(statements.toArray(StatementParser.Statement[]::new));
    }

    public record Program(
            StatementParser.Statement[] statements
    ) {
    }
}
