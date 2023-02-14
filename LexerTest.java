package lexer;

import org.junit.jupiter.api.*;

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

    public void  test_02_declaration(){
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
}
