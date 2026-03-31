import java.util.List;

public class Main {
    public static void main(String[] args) {

        String programi =
                "a = 5;" +
                        "b = 4;" +
                        "c = a + b;" +
                        "Afisho c;" +
                        "Lexo a;" +
                        "Lexo b;" +
                        "c = a * b;" +
                        "Afisho c;" +
                        "Lexo a;" +
                        "c = a / b;" +
                        "Afisho c;" +
                        "c = a - b;" +
                        "Afisho c;";

        System.out.println("=== Mini Interpreter ===\n");

        Lexer lexer = new Lexer(programi);
        List<Token> tokens = lexer.tokenizo();

        Parser parser = new Parser(tokens);
        List<Nod> ast = parser.program();

        Interpreter interpreter = new Interpreter();
        interpreter.ekzekuto(ast);

        System.out.println("\n=== Perfundoi! ===");
    }
}