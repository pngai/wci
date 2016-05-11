package frontend.pascal;

import frontend.*;
import message.Message;
import message.MessageType;

import java.io.IOException;

/**
 * Created by patrick on 2016/5/8.
 */

/**
 * <h1>PascalParserTD</h1>
 *
 * <p>The top-down Pascal Parser.</p>
 */
public class PascalParserTD extends Parser{
    protected static PascalErrorHandler errorHandler;

    static {
        errorHandler = new PascalErrorHandler();
    }

    /**
     * Constructor.
     * @param scanner the scanner to be used with this parser.
     */
    public PascalParserTD(Scanner scanner) {
        super(scanner);
    }

    /**
     * Parse a Pascal source program and generate the symbol table and the intermediate code.
     * @throws Exception if an error occurred.
     */
    public void parse() throws Exception {
        Token token;
        long startTime = System.currentTimeMillis();
        try {
            //loop over each token until the end of file
            while(!((token = nextToken()) instanceof EofToken)) {
                TokenType tokentype = token.getType();

                if(tokentype != PascalTokenType.ERROR) {
                    sendMessage(new Message(MessageType.TOKEN, new Object[]{token.getLineNumber(),
                    token.getPosition(), tokentype, token.getText(), token.getValue()}));
                } else {
                    errorHandler.flag(token, (PascalErrorCode) token.getValue(), this);
                }
            }
            float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
            sendMessage(new Message(MessageType.PARSER_SUMMARY, new Number[]{token.getLineNumber(),
                    getErrorCount(), elapsedTime}));
        } catch (IOException ex){
            errorHandler.abortTranslation(PascalErrorCode.IO_ERROR, this);
        }
    }

    /**
     * Return the number of syntax errors found by the parser.
     * @return the error count.
     */
    public int getErrorCount() {
        return errorHandler.getErrorCount();
    }
}
