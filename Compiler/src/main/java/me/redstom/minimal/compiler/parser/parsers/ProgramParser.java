package me.redstom.minimal.compiler.parser.parsers;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.parser.Parses;
import me.redstom.minimal.compiler.parser.ParsingContext;
import me.redstom.minimal.compiler.parser.nodes.Program;
import me.redstom.minimal.compiler.parser.nodes.Statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Parses(Program.class)
public class ProgramParser implements IParser<Program> {

    @Override
    public Program parse(ParsingContext context) throws LanguageException {
        List<Statement> statements = new ArrayList<>();

        while (!context.upcomingTokens().isEmpty()) {
            var statement = context.parse(Statement.class);
            statements.add(statement);
        }

        return new Program(Collections.unmodifiableList(statements));
    }

}
