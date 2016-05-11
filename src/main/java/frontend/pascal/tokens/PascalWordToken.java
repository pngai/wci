package frontend.pascal.tokens;

import frontend.Source;
import frontend.pascal.PascalToken;
import frontend.pascal.PascalTokenType;


/**
 * Created by patrick on 2016/5/10.
 */
public class PascalWordToken extends PascalToken{
    /**
     * Constructor.
     *
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public PascalWordToken(Source source) throws Exception {
        super(source);
    }

    /**
     * Extract a Pascal word token from the source.
     * @throws Exception if an error occurred.
     */
    protected void extract() throws Exception{
        StringBuilder textBuffer = new StringBuilder();
        char currentChar = currentChar();

        //Get the word sequence (letter or digit). The scanner has already determined that the first character
        //is a letter.
        while (Character.isLetterOrDigit(currentChar)) {
            textBuffer.append(currentChar);
            currentChar = nextChar();
        }
        text = textBuffer.toString();

        // Is is a reserved word or an identifier?
        type = (PascalTokenType.RESERVED_WORDS.contains(text.toLowerCase())
                ? PascalTokenType.valueOf(text.toUpperCase())
                : PascalTokenType.IDENTIFIER);
    }

}
