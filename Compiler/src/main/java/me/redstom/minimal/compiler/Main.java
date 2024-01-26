import me.redstom.minimal.compiler.exceptions.LanguageException;
import me.redstom.minimal.compiler.lexer.Lexer;
import me.redstom.minimal.compiler.parser.Parser;

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
            
            """;

    var tokens = lexer.lex(code);
    System.out.println(parser.parse(tokens));
}
