package frontend.pascal.tokens;

import frontend.Source;
import frontend.pascal.PascalErrorCode;
import frontend.pascal.PascalToken;
import frontend.pascal.PascalTokenType;

/**
 * Created by patrick on 2016/5/10.
 */
public class PascalNumberToken extends PascalToken{
    /**
     * Constructor.
     *
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public PascalNumberToken(Source source) throws Exception {
        super(source);
    }

    /**
     * Extract a Pascal number token from the source.
     * @throws Exception if an error occurred.
     */
    protected void extract() throws Exception {
        StringBuilder textBuffer = new StringBuilder();
        extractNumber(textBuffer);
        text = textBuffer.toString();
    }

    /**
     * Extract a Pascal number token from the source
     * @param textBuffer  the buffer to append the token's characters.
     * @throws Exception if an error occurred.
     */
    protected void extractNumber(StringBuilder textBuffer) throws Exception{
        String wholeDigits = null;
        String fractionDigits = null;
        String exponentDigits = null;
        char exponentSign = '+';
        boolean sawDotDot = false;
        char currentChar;

        type = PascalTokenType.INTEGER; //assume INTEGER type for now

        wholeDigits = unsignedIntegerDigits(textBuffer);
        if(type == PascalTokenType.ERROR) {
            return;
        }

        //is there a . ?
        //could be  a decimal point or start of a .. token
        currentChar = currentChar();
        if(currentChar == '.') {
            if(peekChar() == '.') {
                sawDotDot = true; //its ".." token, so dont consume it
            } else {
                type = PascalTokenType.REAL;
                textBuffer.append(currentChar);
                currentChar = nextChar(); //consume decimal point

                //collect fractional part
                fractionDigits = unsignedIntegerDigits(textBuffer);
                if(type == PascalTokenType.ERROR) {
                    return;
                }
            }
        }

        //is there a exponent part
        //there cannot be an exponent if we already saw a ".." token
        currentChar = currentChar();
        if(!sawDotDot &&((currentChar =='E') || (currentChar == 'e'))){
            type = PascalTokenType.REAL;    //exponent, so token type is REAL
            textBuffer.append(currentChar);
            currentChar = nextChar(); //consume 'E' or 'e'

            if((currentChar == '+') || (currentChar == '-')) {
                textBuffer.append(currentChar);
                exponentSign = currentChar();
                currentChar = nextChar();   //consume '+' or '-'
            }
            exponentDigits = unsignedIntegerDigits(textBuffer);
        }

        //compute value of integer token
        if(type == PascalTokenType.INTEGER) {
            int integerValue = computeIntegerValue(wholeDigits);
            if(type != PascalTokenType.ERROR) {
                value = new Integer(integerValue);
            }
        }
        else if(type == PascalTokenType.REAL) {
            float floatValue = computeFloatValue(wholeDigits, fractionDigits, exponentDigits, exponentSign);
            if(type != PascalTokenType.ERROR) {
                value = new Float(floatValue);
            }
        }

    }

    /**
     * Extract and return the digits of an unsigned integer.
     * @param textBuffer the buffer to append the token's characters.
     * @return the string of digits.
     * @throws Exception if an error occurred.
     */
    private String unsignedIntegerDigits(StringBuilder textBuffer) throws Exception {
        char currentChar = currentChar();

        //Must have at least one digit
        if(!Character.isDigit(currentChar)) {
            type = PascalTokenType.ERROR;
            value = PascalErrorCode.INVALID_NUMBER;
            return null;
        }

        //extract digits
        StringBuilder digits = new StringBuilder();
        while (Character.isDigit(currentChar)) {
            textBuffer.append(currentChar);
            digits.append(currentChar);
            currentChar = nextChar();   //consume digit
        }
        return digits.toString();
    }

    /**
     * Compute and return the integer value of a string of digits.
     * Check for overflow.
     * @param digits the string of digits.
     * @return the integer value.
     */
    private int computeIntegerValue(String digits) {
        if (digits == null) {
            return 0;
        }
        int integerValue = 0;
        int prevValue = -1; //overflow occurred if prevValue > integerValue
        int index = 0;

        //loop over digits to compute integer value as long as no overflow occurs
        while ((index < digits.length()) && (integerValue >= prevValue)) {
            prevValue = integerValue;
            integerValue = 10*integerValue + Character.getNumericValue(digits.charAt(index++));
        }
        //No overflow
        if(integerValue >= prevValue) {
            return integerValue;
        }
        //Overflow: Set the integer out of range error.
        else {
            type = PascalTokenType.ERROR;
            value = PascalErrorCode.RANGE_INTEGER;
            return 0;
        }
    }

    private float computeFloatValue(String wholeDigits, String fractionDigits, String exponentDigits, char exponentSign) {
        double floatValue = 0.0;
        int exponentValue = computeIntegerValue(exponentDigits);
        String digits = wholeDigits;    //whole and fraction digits

        if(exponentSign == '-') {
            exponentValue = -exponentValue;
        }
        if(fractionDigits != null) {
            exponentValue -= fractionDigits.length();
            digits += fractionDigits;
        }
        if(Math.abs(exponentValue + wholeDigits.length()) > Double.MAX_EXPONENT) {
            type = PascalTokenType.ERROR;
            value = PascalErrorCode.RANGE_REAL;
            return 0.0f;
        }

        int index = 0;
        while (index < digits.length()) {
            floatValue = 10*floatValue + Character.getNumericValue(digits.charAt(index++));
        }
        if(exponentValue != 0) {
            floatValue *= Math.pow(10, exponentValue);
        }
        return (float) floatValue;
    }
}
