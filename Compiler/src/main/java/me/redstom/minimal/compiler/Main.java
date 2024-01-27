import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.Lexer;
import me.redstom.minimal.compiler.parser.Parser;
import me.redstom.minimal.compiler.parser.nodes.Program;


public void main() {
    try {
        exec();
    } catch (LanguageException e) {
        System.err.println(e.getMessage());

        e.printStackTrace();
    }
}

public void exec() throws LanguageException {
    Lexer lexer = new Lexer();
    Parser parser = new Parser();

    String code = """
            struct Test<A, B> is
                test: A
                machin: B
            .
                        
            ext on Test is
                func test is
                    rt 1
                .
            .
            """;

    var tokens = lexer.lex(code);
    Program program = parser.parse(tokens);
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
        } else if ((i + 1) < chars.length && c == ',' && chars[i + 1] != ',') {
            finalString.append(",\n").append(" ".repeat(indent * 4));
        } else {
            finalString.append(c);
            if ((i + 1) < chars.length && chars[i + 1] == ']') {
                finalString.append("\n").append(" ".repeat(Math.max(0, indent - 1) * 4));
            }
        }
    }
    System.out.println(finalString);
}
