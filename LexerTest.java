package us.calpoly.csc309;

import org.junit.jupiter.api.*;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class LexerTest {

    /**
     * checking to see if a java line to declare a variable
     *
     */
    @Test
    public void test_01_variable_declaration() {
        String inputText = "int x = 0; string y = \"'test'\";";
        Lexer lex = new Lexer(inputText);
        lex.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("int", "KEYWORD"));
        expected_tokens.add(new Token("x", "IDENTIFIER"));
        expected_tokens.add(new Token("=", "OPERATOR"));
        expected_tokens.add(new Token("0", "INTEGER"));
        expected_tokens.add(new Token(";", "DELIMITER"));
        expected_tokens.add(new Token("string", "KEYWORD"));
        expected_tokens.add(new Token("y", "IDENTIFIER"));
        expected_tokens.add(new Token("=", "OPERATOR"));
        expected_tokens.add(new Token("\"\'test\'\"", "STRING"));
        expected_tokens.add(new Token(";", "DELIMITER"));
        for (int i = 0; i < expected_tokens.size(); i++) {
            Assertions.assertEquals(expected_tokens.get(i).getToken(), lex.getTokens().get(i).getToken());
            Assertions.assertEquals(expected_tokens.get(i).getWord(), lex.getTokens().get(i).getWord());
        }
    }

    @Test
    public void test_02_class_declaration() {
        String inputText = """
                public class A extends B {
                 String s = "'this is crazy' said stan";
                 public method(){
                    int a = 2+2;
                }
                }""";
        Lexer lex = new Lexer(inputText);
        lex.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("public", "IDENTIFIER"));
        expected_tokens.add(new Token("class", "IDENTIFIER"));
        expected_tokens.add(new Token("A", "IDENTIFIER"));
        expected_tokens.add(new Token("extends", "IDENTIFIER"));
        expected_tokens.add(new Token("B", "IDENTIFIER"));
        expected_tokens.add(new Token("{", "DELIMITER"));
        expected_tokens.add(new Token("String", "IDENTIFIER"));
        expected_tokens.add(new Token("s", "IDENTIFIER"));
        expected_tokens.add(new Token("=", "OPERATOR"));
        expected_tokens.add(new Token("\"'this is crazy' said stan\"", "STRING"));
        expected_tokens.add(new Token(";", "DELIMITER"));
        expected_tokens.add(new Token("public", "IDENTIFIER"));
        expected_tokens.add(new Token("method", "IDENTIFIER"));
        expected_tokens.add(new Token("(", "DELIMITER"));
        expected_tokens.add(new Token(")", "DELIMITER"));
        expected_tokens.add(new Token("{", "DELIMITER"));
        expected_tokens.add(new Token("int", "KEYWORD"));
        expected_tokens.add(new Token("a", "IDENTIFIER"));
        expected_tokens.add(new Token("=", "OPERATOR"));
        expected_tokens.add(new Token("2", "INTEGER"));
        expected_tokens.add(new Token("+", "OPERATOR"));
        expected_tokens.add(new Token("2", "INTEGER"));
        expected_tokens.add(new Token(";", "DELIMITER"));
        expected_tokens.add(new Token("}", "DELIMITER"));
        expected_tokens.add(new Token("}", "DELIMITER"));

        for (int i = 0; i < expected_tokens.size(); i++) {
            Assertions.assertEquals(expected_tokens.get(i).getToken(), lex.getTokens().get(i).getToken());
            Assertions.assertEquals(expected_tokens.get(i).getWord(), lex.getTokens().get(i).getWord());
        }
    }

    @Test
    public void test_03_Lex_Token() {
        StringBuilder inputText = new StringBuilder();
        Scanner scanner;

        try {
            scanner = new Scanner(new File("src/main/java/us/calpoly/csc309/Token.java"));
            while (scanner.hasNextLine()) {
                inputText.append(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception ignored) {
        }

        Lexer lex = new Lexer(inputText.toString());
        lex.run();

        int correctTokens = 0;
        for (Token t : lex.getTokens()) {
            if (t.getWord().equals("Token") && t.getToken().equals("IDENTIFIER")) {
                correctTokens++;
            }

            if (t.getWord().equals("getWord") && t.getToken().equals("IDENTIFIER")) {
                correctTokens++;
            }

            if (t.getWord().equals("getToken") && t.getToken().equals("IDENTIFIER")) {
                correctTokens++;
            }

            if (t.getWord().equals("setToken") && t.getToken().equals("IDENTIFIER")) {
                correctTokens++;
            }

            if (t.getWord().equals("getLine") && t.getToken().equals("IDENTIFIER")) {
                correctTokens++;
            }

            if (t.getWord().equals("setLine") && t.getToken().equals("IDENTIFIER")) {
                correctTokens++;
            }
        }

        Assertions.assertEquals(8, correctTokens);
    }

    @Test
    public void test_04_errors_on_none_code_input() {
        StringBuilder inputText = new StringBuilder();

        try {
            Scanner scanner = new Scanner(new File("src/test/resources/war_and_peace.txt"));
            for (int i = 0; i < 100; i++) {
                inputText.append(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception ignored) {
        }

        Lexer lex = new Lexer(inputText.toString());
        lex.run();

        int numOfOperators = 0;
        for (Token t : lex.getTokens()) {
            if (t.getToken().equals("ERROR")) {
                numOfOperators++;
            }
        }

        Assertions.assertTrue(0 < numOfOperators);

    }

    @Test
    public void test_05_declarations_and_ifs() {
        String inputText = """
                int incorrect = ~;
                char c = 'A';
                float f = 0.9493;
                double d = 9.9983;
                String s = "q";
                int octal = 012;
                int hex = 0x0474
                byte b = 0b101
                short sh = 0b111
                String s2 = ""'";
                if(octal == hex){
                    System.out.println("true!");
                }
                if(octal != hex){
                    System.out.println("false!");
                }
                """;

        Lexer lex = new Lexer(inputText);
        lex.run();
        lex.getTokens().get(0).setLine(-1);
        lex.getTokens().get(0).setToken("IDENTIFIER");
        lex.getTokens().get(0).setWord("test");

        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("test", "IDENTIFIER", -1));
        expected_tokens.add(new Token("incorrect", "IDENTIFIER"));
        expected_tokens.add(new Token("c", "IDENTIFIER"));
        expected_tokens.add(new Token("f", "IDENTIFIER"));
        expected_tokens.add(new Token("d", "IDENTIFIER"));
        expected_tokens.add(new Token("s", "IDENTIFIER"));
        expected_tokens.add(new Token("b", "IDENTIFIER"));
        expected_tokens.add(new Token("sh", "IDENTIFIER"));
        expected_tokens.add(new Token("s2", "IDENTIFIER"));
        expected_tokens.add(new Token("==", "OPERATOR"));
        expected_tokens.add(new Token("!=", "OPERATOR"));

        int tokenMatch = 0;
        for (Token t : lex.getTokens()) {
            for (Token t2 : expected_tokens) {
                if (t.getToken().equals(t2.getToken()) && t.getWord().equals(t2.getWord())) {
                    tokenMatch++;
                }
            }
        }
        Assertions.assertEquals(11, tokenMatch);
    }
}
