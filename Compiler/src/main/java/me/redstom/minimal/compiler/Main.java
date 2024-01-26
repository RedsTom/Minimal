import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.Lexer;
import me.redstom.minimal.compiler.parser.Parser;
import me.redstom.minimal.compiler.parser.ast.ProgramParser;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public void main() {
    try {
        exec();
    } catch (LanguageException e) {
        System.err.println(e.getMessage());
    }
}

public void exec() throws LanguageException {
    Lexer lexer = new Lexer();
    Parser parser = new Parser();

    String code = """
            struct Test<A<C>> is
                test: A
                machin: B
            .
            """;

    var tokens = lexer.lex(code);
    ProgramParser.Program program = parser.parse(tokens);
    System.out.println(program);
    beautify(program.toString());
}

private static void beautify(String input) {
    StringBuilder finalString = new StringBuilder();
    int indent = 0;
    char[] chars = input.toCharArray();
    for (int i = 0; i < chars.length; i++) {
        char c = chars[i];
        if (c == ' ') continue;

        if (c == '[') {
            if ((i + 1) < chars.length && chars[i + 1] != ']') {
                indent++;
                finalString.append(c);
                finalString.append("\n").append(" ".repeat(indent * 4));
            } else {
                finalString.append(c);
            }
        } else if (c == ']') {
            if (chars[i - 1] != '[') {
                --indent;
            }
            finalString.append(c);
            if ((i + 1) < chars.length && chars[i + 1] == ']') {
                finalString.append("\n").append(" ".repeat(Math.max(0, indent - 1) * 4));
            } else if ((i + 1) < chars.length && chars[i + 1] != ',') {
                finalString.append("\n").append(" ".repeat(indent * 4));
            }
        } else if (c == ',') {
            finalString.append(",\n").append(" ".repeat(indent * 4));
        } else {
            finalString.append(c);
        }
    }
    System.out.println(finalString);
}
