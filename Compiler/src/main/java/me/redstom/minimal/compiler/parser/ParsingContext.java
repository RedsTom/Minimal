package me.redstom.minimal.compiler.parser;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.exceptions.ParsingException;
import me.redstom.minimal.compiler.lexer.Token;
import me.redstom.minimal.compiler.lexer.TokenType;
import me.redstom.minimal.compiler.lexer.TokenValue;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class ParsingContext {

    private final Queue<Token> upcomingTokens;
    private final ParsingRegistry parsingRegistry;

    public ParsingContext(Queue<Token> upcomingTokens, ParsingRegistry parsingRegistry) {
        this.upcomingTokens = upcomingTokens;
        this.parsingRegistry = parsingRegistry;
    }

    public Queue<Token> upcomingTokens() {
        return upcomingTokens;
    }

    public ParsingRegistry parsingRegistry() {
        return parsingRegistry;
    }

    public ParsingContext copy() {
        return new ParsingContext(upcomingTokens, parsingRegistry);
    }
    public ParsingContext copyClone() {
        return new ParsingContext(new ArrayDeque<>(upcomingTokens), parsingRegistry);
    }

    public <T> T parse(Class<T> type) throws LanguageException {
        return parsingRegistry.ofType(type).parse(copy());
    }

    public Token eat(TokenValue value) throws LanguageException {
        Token upcoming = upcomingTokens.poll();

        if (upcoming == null) {
            throw new ParsingException(value.type());
        }

        if (upcoming.type() != value.type()) {
            throw new ParsingException(value, upcoming);
        }

        if (!upcoming.value().equals(value.value())) {
            throw new ParsingException(value, upcoming);
        }

        return upcoming;
    }

    public Token eat(TokenType type) throws LanguageException {
        Token upcoming = upcomingTokens.poll();
        if (upcoming == null) {
            throw new ParsingException(type);
        }
        if (upcoming.type() != type) {
            throw new ParsingException(type, upcoming);
        }
        return upcoming;
    }

    public boolean lookahead(TokenType tokenType) {
        Token upcoming = upcomingTokens.peek();
        if (upcoming == null) {
            return false;
        }

        return upcoming.type() == tokenType;
    }

    public boolean lookahead(TokenValue tokenValue) {
        Token upcoming = upcomingTokens.peek();
        if (upcoming == null) {
            return false;
        }

        return upcoming.type() == tokenValue.type() && upcoming.value().equals(tokenValue.value());
    }
}
