package frontend.pascal;

import frontend.EofToken;
import frontend.Parser;
import frontend.Scanner;
import frontend.Token;
import message.Message;
import message.MessageType;

/**
 * Created by patrick on 2016/5/8.
 */

/**
 * <h1>PascalParserTD</h1>
 *
 * <p>The top-down Pascal Parser.</p>
 */
public class PascalParserTD extends Parser{

    /**
     * Constructor.
     * @param scanner the scanner to be used with this parser.
     */
    public PascalParserTD(Scanner scanner) {
        super(scanner);
    }

    /**
     * Parse a Pascal source program and generate the symbol table and the intermediate code.
     * @throws Exception
     */
    public void parse() throws Exception {
        Token token;
        long startTime = System.currentTimeMillis();
        while(!((token = nextToken()) instanceof EofToken)){}
        float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
        sendMessage(new Message(MessageType.PARSER_SUMMARY, new Number[]{token.getLineNumber(), getErrorCount(), elapsedTime}));
    }

    /**
     * Return the number of syntax errors found by the parser.
     * @return the error count.
     */
    public int getErrorCount() {
        return 0;
    }
}
