package frontend;

/**
 * Created by patrick on 2016/5/7.
 */
public class EofToken extends Token {
    /**
     * Constructor.
     * @param source the source from where to fetch subsequent characters.
     * @throws Exception if an error occurred.
     */
    public EofToken(Source source) throws Exception {
        super(source);
    }

    /**
     * Do nothing. Do not consume any source characters.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    protected void extract(Source source) throws Exception {
    }
}
