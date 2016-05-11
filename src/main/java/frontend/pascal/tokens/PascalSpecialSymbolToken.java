package frontend.pascal.tokens;

import frontend.Source;
import frontend.pascal.PascalErrorCode;
import frontend.pascal.PascalToken;
import frontend.pascal.PascalTokenType;

/**
 * Created by patrick on 2016/5/10.
 */
public class PascalSpecialSymbolToken extends PascalToken {
    /**
     * Constructor.
     *
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public PascalSpecialSymbolToken(Source source) throws Exception {
        super(source);
    }

    /**
     * Extract a Pascal special symbol token from the source
     * @throws Exception if an error occurred.
     */
    protected void extract() throws Exception {
        char currentChar = currentChar();
        text = Character.toString(currentChar);
        type = null;

        switch (currentChar) {
            // Single-character special symbols.
            case '+':  case '-':  case '*':  case '/':  case ',':
            case ';':  case '\'': case '=':  case '(':  case ')':
            case '[':  case ']':  case '{':  case '}':  case '^': {
                nextChar();  // consume character
                break;
            }
            // : or :=
            case ':': {
                currentChar = nextChar();  // consume ':';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '='
                }
                break;
            }
            // < or <= or <>
            case '<': {
                currentChar = nextChar();  // consume '<';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '='
                }
                else if (currentChar == '>') {
                    text += currentChar;
                    nextChar();  // consume '>'
                }
                break;
            }
            // > or >=
            case '>': {
                currentChar = nextChar();  // consume '>';

                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();  // consume '='
                }
                break;
            }
            // . or ..
            case '.': {
                currentChar = nextChar();  // consume '.';

                if (currentChar == '.') {
                    text += currentChar;
                    nextChar();  // consume '.'
                }
                break;
            }
            default: {
                nextChar();  // consume bad character
                type = PascalTokenType.ERROR;
                value = PascalErrorCode.INVALID_CHARACTER;
            }
        }
        // Set the type if it wasn't an error.
        if (type == null) {
            type = PascalTokenType.SPECIAL_SYMBOLS.get(text);
        }
    }
}
