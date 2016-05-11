package frontend.pascal.tokens;

import frontend.Source;
import frontend.pascal.PascalErrorCode;
import frontend.pascal.PascalToken;
import frontend.pascal.PascalTokenType;

import static frontend.Source.EOF;

/**
 * Created by patrick on 2016/5/10.
 */
public class PascalStringToken extends PascalToken {
    /**
     * Constructor.
     *
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public PascalStringToken(Source source) throws Exception {
        super(source);
    }

    /**
     * Extract a Pascal string token from the source
     * @throws Exception if an error occurred.
     */
    protected void extract() throws Exception{
        StringBuilder textBuffer = new StringBuilder();
        StringBuilder valueBuffer = new StringBuilder();

        char currentChar = nextChar(); // consume initial quote
        textBuffer.append('\'');
        //get string characters
        do {
            if(Character.isWhitespace(currentChar)){
                currentChar = ' ';
            }

            if((currentChar != '\'') && currentChar != EOF) {
                textBuffer.append(currentChar);
                valueBuffer.append(currentChar);
                currentChar = nextChar(); //consume character
            }

            //Quotes
            if(currentChar =='\'') {
                while ((currentChar =='\'') && peekChar() =='\'') {
                    textBuffer.append("''");
                    valueBuffer.append(currentChar);    //append single quote
                    currentChar = nextChar();           //consume pair of quotes
                    currentChar = nextChar();
                }
            }
        } while ((currentChar != '\'') && (currentChar != EOF));
        if(currentChar=='\'') {
            nextChar();     //consume final quote
            textBuffer.append('\'');
            type = PascalTokenType.STRING;
            value = valueBuffer.toString();
        } else {
            type = PascalTokenType.ERROR;
            value = PascalErrorCode.UNEXPECTED_EOF;
        }
        text = textBuffer.toString();
    }
}
