package lexer;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

public class LexerTest {

    /**
     * checking to see if a java line to declare a variable
     *
     */
    @Test
    public void  test_01_declaration(){
        String inputText = "int x = 0;";
        Lexer lex = new Lexer(inputText);
        lex.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("int", "KEYWORD"));
        expected_tokens.add(new Token("x", "IDENTIFIER"));
        expected_tokens.add(new Token("=", "OPERATOR"));
        expected_tokens.add(new Token("0", "INTEGER"));
        expected_tokens.add(new Token(";", "DELIMITER"));
        for(int i = 0; i < expected_tokens.size(); i++){
            Assertions.assertEquals(expected_tokens.get(i).getToken(), lex.getTokens().get(i).getToken());
            Assertions.assertEquals(expected_tokens.get(i).getWord(), lex.getTokens().get(i).getWord());
        }
    }

    @Test
    public void test_02_whitespace() {
        String inputText = " int  x  =   0  ; ";
        Lexer lex = new Lexer(inputText);
        lex.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("int", "KEYWORD"));
        expected_tokens.add(new Token("x", "IDENTIFIER"));
        expected_tokens.add(new Token("=", "OPERATOR"));
        expected_tokens.add(new Token("0", "INTEGER"));
        expected_tokens.add(new Token(";", "DELIMITER"));
        for(int i = 0; i < expected_tokens.size(); i++){
            Assertions.assertEquals(expected_tokens.get(i).getToken(), lex.getTokens().get(i).getToken());
            Assertions.assertEquals(expected_tokens.get(i).getWord(), lex.getTokens().get(i).getWord());
        }
    }

    @Test
    public void test_03_multi_declaration() {
        String inputText = "int x = 0; float y = 3.14;";
        Lexer lex = new Lexer(inputText);
        lex.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("int", "KEYWORD"));
        expected_tokens.add(new Token("x", "IDENTIFIER"));
        expected_tokens.add(new Token("=", "OPERATOR"));
        expected_tokens.add(new Token("0", "INTEGER"));
        expected_tokens.add(new Token(";", "DELIMITER"));
        expected_tokens.add(new Token("float", "KEYWORD"));
        expected_tokens.add(new Token("y", "IDENTIFIER"));
        expected_tokens.add(new Token("=", "OPERATOR"));
        expected_tokens.add(new Token("3.14", "FLOAT"));
        expected_tokens.add(new Token(";", "DELIMITER"));
        for(int i = 0; i < expected_tokens.size(); i++){
            Assertions.assertEquals(expected_tokens.get(i).getToken(), lex.getTokens().get(i).getToken());
            Assertions.assertEquals(expected_tokens.get(i).getWord(), lex.getTokens().get(i).getWord());
        }
    }

    @Test
    public void test_04_single_line_comment() {
        String inputText = "int x = 0; // this is a comment";
        Lexer lex = new Lexer(inputText);
        lex.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("int", "KEYWORD"));
        expected_tokens.add(new Token("x", "IDENTIFIER"));
        expected_tokens.add(new Token("=", "OPERATOR"));
        expected_tokens.add(new Token("0", "INTEGER"));
        expected_tokens.add(new Token(";", "DELIMITER"));
        for(int i = 0; i < expected_tokens.size(); i++){
            Assertions.assertEquals(expected_tokens.get(i).getToken(), lex.getTokens().get(i).getToken());
            Assertions.assertEquals(expected_tokens.get(i).getWord(), lex.getTokens().get(i).getWord());
        }
    }

    @Test
    public void test_05_multi_line_comment() {
        String inputText = "int x = 8; /*\n" +
                "multi-line comment\n" +
                "*/";
        Lexer lex = new Lexer(inputText);
        lex.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("int", "KEYWORD"));
        expected_tokens.add(new Token("x", "IDENTIFIER"));
        expected_tokens.add(new Token("=", "OPERATOR"));
        expected_tokens.add(new Token("8", "INTEGER"));
        expected_tokens.add(new Token(";", "DELIMITER"));
        for(int i = 0; i < expected_tokens.size(); i++){
            Assertions.assertEquals(expected_tokens.get(i).getToken(), lex.getTokens().get(i).getToken());
            Assertions.assertEquals(expected_tokens.get(i).getWord(), lex.getTokens().get(i).getWord());
        }
    }

    @Test
    public void test_06_boolean_operators() {
        String inputText = "boolean a = true && false; \nboolean b = !true || !false;";
        Lexer lex = new Lexer(inputText);
        lex.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("boolean", "KEYWORD"));
        expected_tokens.add(new Token("a", "IDENTIFIER"));
        expected_tokens.add(new Token("=", "OPERATOR"));
        expected_tokens.add(new Token("true", "BOOLEAN"));
        expected_tokens.add(new Token("&&", "OPERATOR"));
        expected_tokens.add(new Token("false", "BOOLEAN"));
        expected_tokens.add(new Token(";", "DELIMITER"));
        expected_tokens.add(new Token("boolean", "KEYWORD"));
        expected_tokens.add(new Token("b", "IDENTIFIER"));
        expected_tokens.add(new Token("=", "OPERATOR"));
        expected_tokens.add(new Token("!", "OPERATOR"));
        expected_tokens.add(new Token("true", "BOOLEAN"));
        expected_tokens.add(new Token("||", "OPERATOR"));
        expected_tokens.add(new Token("!", "OPERATOR"));
        expected_tokens.add(new Token("false", "BOOLEAN"));
        expected_tokens.add(new Token(";", "DELIMITER"));
        for(int i = 0; i < expected_tokens.size(); i++){
            Assertions.assertEquals(expected_tokens.get(i).getToken(), lex.getTokens().get(i).getToken());
            Assertions.assertEquals(expected_tokens.get(i).getWord(), lex.getTokens().get(i).getWord());
        }
    }

    @Test
    public void test_07_single_quote_state() {
        String inputText = "'a'";
        Lexer lex = new Lexer(inputText);
        lex.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("'", "SINGLE_QUOTE"));
        expected_tokens.add(new Token("a", "CHARACTER"));
        expected_tokens.add(new Token("'", "SINGLE_QUOTE"));
        for(int i = 0; i < expected_tokens.size(); i++){
            Assertions.assertEquals(expected_tokens.get(i).getToken(), lex.getTokens().get(i).getToken());
            Assertions.assertEquals(expected_tokens.get(i).getWord(), lex.getTokens().get(i).getWord());
        }
    }

    @Test
    public void test_08_escape_char_state() {
        String inputText = "\"Hello\\nWorld!\"";
        Lexer lex = new Lexer(inputText);
        lex.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("\"", "QUOTE"));
        expected_tokens.add(new Token("Hello", "STRING"));
        expected_tokens.add(new Token("\\n", "ESCAPE_CHAR"));
        expected_tokens.add(new Token("World!", "STRING"));
        expected_tokens.add(new Token("\"", "QUOTE"));
        for(int i = 0; i < expected_tokens.size(); i++){
            Assertions.assertEquals(expected_tokens.get(i).getToken(), lex.getTokens().get(i).getToken());
            Assertions.assertEquals(expected_tokens.get(i).getWord(), lex.getTokens().get(i).getWord());
        }
    }

    @Test
    public void test_10_dollar_underscore_special_symbols() {
        String inputText = "$foo_bar! 1*2+3/4-5%6";
        Lexer lex = new Lexer(inputText);
        lex.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("$foo_bar", "IDENTIFIER"));
        expected_tokens.add(new Token("!", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token(" ", "WHITESPACE"));
        expected_tokens.add(new Token("1", "INTEGER"));
        expected_tokens.add(new Token("*", "OPERATOR"));
        expected_tokens.add(new Token("2", "INTEGER"));
        expected_tokens.add(new Token("+", "OPERATOR"));
        expected_tokens.add(new Token("3", "INTEGER"));
        expected_tokens.add(new Token("/", "OPERATOR"));
        expected_tokens.add(new Token("4", "INTEGER"));
        expected_tokens.add(new Token("-", "OPERATOR"));
        expected_tokens.add(new Token("5", "INTEGER"));
        expected_tokens.add(new Token("%", "OPERATOR"));
        expected_tokens.add(new Token("6", "INTEGER"));
        for(int i = 0; i < expected_tokens.size(); i++){
            Assertions.assertEquals(expected_tokens.get(i).getToken(), lex.getTokens().get(i).getToken());
            Assertions.assertEquals(expected_tokens.get(i).getWord(), lex.getTokens().get(i).getWord());
        }
    }

    @Test
    public void test_11_special_symbol() {
        String inputText = "!@#$%^&*()_+{}|:\"<>?-=[]\\;',./";
        Lexer lex = new Lexer(inputText);
        lex.run();
        Vector<Token> expected_tokens = new Vector<>();
        expected_tokens.add(new Token("!", "OPERATOR"));
        expected_tokens.add(new Token("@", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("#", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("$", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("%", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("^", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("&", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("*", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("(", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token(")", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("_", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("+", "OPERATOR"));
        expected_tokens.add(new Token("{", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("}", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("|", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token(":", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("\"", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("<", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token(">", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("?", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("-", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("=", "OPERATOR"));
        expected_tokens.add(new Token("[", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("]", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("\\", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token(";", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("'", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token(",", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token(".", "SPECIAL_SYMBOL"));
        expected_tokens.add(new Token("/", "SPECIAL_SYMBOL"));
        for (int i = 0; i < expected_tokens.size(); i++) {
            Assertions.assertEquals(expected_tokens.get(i).getToken(), lex.getTokens().get(i).getToken());
            Assertions.assertEquals(expected_tokens.get(i).getWord(), lex.getTokens().get(i).getWord());
        }
    }

}
