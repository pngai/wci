package frontend.pascal;

/**
 * Created by patrick on 2016/5/10.
 */

import frontend.Parser;
import frontend.Token;
import message.Message;
import message.MessageType;

/**
 * <h1>PascalErrorHandler</h1>
 *
 * <p>Error handler Pascal syntax errors.</p>
 */
public class PascalErrorHandler {
    private static final int MAX_ERRORS = 25;

    private static int errorCount = 0;

    public int getErrorCount() {
        return errorCount;
    }

    /**
     * Flag an error in the source line.
     * @param token the bad token.
     * @param errorCode the error code.
     * @param parser the parser.
     */
    public void flag(Token token, PascalErrorCode errorCode, Parser parser) {
        parser.sendMessage(new Message(MessageType.SYNTAX_ERROR, new Object[]{token.getLineNumber(),
        token.getPosition(), token.getText(), errorCode.toString()}));
        if(++errorCount > MAX_ERRORS){
            abortTranslation(PascalErrorCode.TOO_MANY_ERRORS, parser);
        }
    }

    /**
     * Abort the translation.
     * @param errorCode the error code.
     * @param parser the parser.
     */
    public void abortTranslation(PascalErrorCode errorCode, Parser parser) {
        String fatalText = "FATAL ERROR: " + errorCode.toString();
        parser.sendMessage(new Message(MessageType.SYNTAX_ERROR, new Object[]{0,0,"",fatalText}));
        System.exit(errorCode.getStatus());
    }
}
