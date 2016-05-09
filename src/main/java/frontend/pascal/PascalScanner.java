package frontend.pascal;

/**
 * Created by patrick on 2016/5/8.
 */

import frontend.EofToken;
import frontend.Scanner;
import frontend.Source;
import frontend.Token;

import static frontend.Source.EOF;

/**
 * <h1>PascalScanner</h1>
 *
 * <p>The Pascal scanner.</p>
 */
public class PascalScanner extends Scanner{

    /**
     * Constructor
     * @param source the source to be used with this scanner.
     */
    public PascalScanner(Source source) {
        super(source);
    }

    /**
     * Extract and return the next Pascal token from the source.
     * @return the next token.
     * @throws Exception if an error occurred.
     */
    protected Token extractToken() throws Exception {
        Token token;
        char currentChar = currentChar();

        if(currentChar == EOF) {
            token = new EofToken(source);
        } else {
            token = new Token(source);
        }
        return token;
    }
}
