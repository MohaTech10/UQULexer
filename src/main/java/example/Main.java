package example;
import Lexer.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // int x  = 10;
        var srcBuffer = "x = 10;";
        var lexer = new LexerManager(srcBuffer);

        System.out.println(Arrays.toString(lexer.getTokens().toArray()));
    }
}
