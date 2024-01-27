package me.redstom.minimal.compiler.lexer;

import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.exceptions.UnknownTokenException;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.regex.Matcher;

import static java.lang.StringTemplate.STR;

public class Lexer {

    public Queue<Token> lex(String input) throws LanguageException {
        var tokens = new ArrayDeque<Token>();

        var position = 0;
        var line = 1;
        var column = 1;

        while (position < input.length()) {
            var token = lexToken(input.substring(position), position, line, column);

            if (token == null) {
                throw new UnknownTokenException(position, line, column, input);
            }

            position += token.length();
            column += token.length();

            if (token.type() == TokenType.NEW_LINE) {
                line++;
                column = 1;
            }
            if (token.type().shouldBeIgnored()) continue;

            tokens.add(token);
        }

        return tokens;
    }

    public Token lexToken(String input, int position, int line, int column) {
        for (var type : TokenType.values()) {
            Matcher matcher = type.pattern().matcher(input);

            if (!matcher.find()) {
                continue;
            }

            String group = matcher.group();

            return new Token(type, switch (type) {
                case STRING -> group.substring(1, group.length() - 1);
                default -> group;
            }, line, column, group.length(), position);
        }

        return null;
    }

}
