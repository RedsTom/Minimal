package me.redstom.minimal.compiler.lexer;

import java.security.Key;
import java.util.function.Function;
import java.util.regex.Pattern;

public enum TokenType {
    STRING(Pattern.compile("^\"[^\"]*\""), t -> STR."\{t.value()}"),
    NUMBER(Pattern.compile("^[+-]?\\d+(\\.\\d+)?"), Token::value),
    BOOLEAN(Pattern.compile("^(true|false)"), Token::value),
    LEFT_PAREN(Pattern.compile("^\\("), _ -> "("),
    RIGHT_PAREN(Pattern.compile("^\\)"), _ -> ")"),
    LEFT_BRACE(Pattern.compile("^\\{"), _ -> "{"),
    RIGHT_BRACE(Pattern.compile("^}"), _ -> "}"),
    LEFT_RAFTER(Pattern.compile("^<"), _ -> "<"),
    RIGHT_RAFTER(Pattern.compile("^>"), _ -> ">"),
    LEFT_BRACKET(Pattern.compile("^\\["), _ -> "["),
    RIGHT_BRACKET(Pattern.compile("^]"), _ -> "]"),
    LEFT_CURLY_BRACE(Pattern.compile("^\\{"), _ -> "{"),
    RIGHT_CURLY_BRACE(Pattern.compile("^}"), _ -> "}"),
    ARROW(Pattern.compile("^->"), _ -> "->"),
    AROBASE(Pattern.compile("^@"), _ -> "@"),
    COLON(Pattern.compile("^:"), _ -> ":"),
    EQUAL(Pattern.compile("^="), _ -> "="),
    DOT(Pattern.compile("^\\."), _ -> "."),
    COMMA(Pattern.compile("^,"), _ -> ","),
    NEW_LINE(Pattern.compile("^\\n"), _ -> "\n", true),
    SPACE(Pattern.compile("^\\s+"), true),
    KEYWORD(Pattern.compile("^(let|struct|ext|on|is|func|takes|returns|rt|internal)?(?=\\s)"), t -> Keyword.get(t.value()).stringify()),
    IDENTIFIER(Pattern.compile(".+?(?=<|>|:|\\s|\\.|\\(|\\)|,|[|]|\\{|})"), t -> STR."identifier: \{t.value()}"),
    ;

    private Pattern pattern;
    private boolean shouldBeIgnored;
    private Function<Token, String> stringifier;

    TokenType(Pattern pattern) {
        this(pattern, Record::toString);
    }

    TokenType(Pattern pattern, Function<Token, String> stringifier) {
        this(pattern, stringifier, false);
    }

    TokenType(Pattern pattern, boolean shouldBeIgnored) {
        this(pattern, Record::toString, shouldBeIgnored);
    }

    TokenType(Pattern pattern, Function<Token, String> stringifier, boolean shouldBeIgnored) {
        this.pattern = pattern;
        this.stringifier = stringifier;
        this.shouldBeIgnored = shouldBeIgnored;
    }

    public Pattern pattern() {
        return pattern;
    }

    public boolean shouldBeIgnored() {
        return shouldBeIgnored;
    }

    public Function<Token, String> stringifier() {
        return stringifier;
    }
}
