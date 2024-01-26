package me.redstom.minimal.compiler.parser.ast;

import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ast.statement.StatementParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Parses(ProgramParser.Program.class)
public class ProgramParser implements IParser<ProgramParser.Program> {

    @Override
    public Program parse(ParsingContext context) {
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

        @Override
        public String toString() {
            return "Program{" +
                   "statements=" + Arrays.toString(statements) +
                   '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Program program = (Program) o;
            return Arrays.equals(statements, program.statements);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(statements);
        }
    }
}
