package me.redstom.minimal.compiler.parser;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.Token;
import me.redstom.minimal.compiler.parser.ast.ProgramParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Queue;

public class Parser {

    private final ApplicationContext appCtx;
    private final ParsingRegistry parsingRegistry;

    public Parser() {
        this.appCtx = new AnnotationConfigApplicationContext("me.redstom.minimal.compiler.parser.ast");
        this.parsingRegistry = new ParsingRegistry();
        parsingRegistry.scan(appCtx);
    }

    public ProgramParser.Program parse(Queue<Token> tokens) throws LanguageException {
        var context = new ParsingContext(tokens, parsingRegistry);
        return context.parse(ProgramParser.Program.class);
    }

}
