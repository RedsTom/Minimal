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
            struct List<T> is
                elements: Array<T>
            .
             
            ext on List is

                func stringified returns String is
                    let joined = (join (map self to @ _: Any -> rt (_ + "!").) with ",")
                    rt joined
                .
            .
             
            ext on Number is
                func through takes end: Number returns List<Number> is
                    let result = List<Number> {
                        elements = ["a"]
                    }
                    
                    (while (self <= end) do @->
                        (result += self)
                        (self += 1)
                    .)
            
                    rt result
                .
            .
            
            func map<T, R> takes list: List<T> "to" function: Function<T, R> returns List<R> is
                let result = List<R> {}
                (loop list:elements as @ _: Any ->
                    (result += function _)
                .)
                (loop 5 through 10 as @_: Any ->
                    (print _)
                .)
                rt result
            .
            
            internal func loop<T> takes array: Array<T> "as" body: Func<T, Result<None>> returns Result<None>
            
            func main is
                let names = List<String> {}
                (names += "John")
            
                (print (names stringified))
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
