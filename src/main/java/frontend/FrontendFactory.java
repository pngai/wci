package frontend;

/**
 * Created by patrick on 2016/5/8.
 */

import frontend.pascal.PascalParserTD;
import frontend.pascal.PascalScanner;

/**
 * <h1>Frontend Factory</h1>
 *
 * <p>A factory class that creates parsers for specific source languages.</p>
 */
public class FrontendFactory {
    public static Parser createParser(String language, String type, Source source) throws Exception {
        if(language.equalsIgnoreCase("Pascal") && type.equalsIgnoreCase("top-down")) {
            Scanner scanner = new PascalScanner(source);
            return new PascalParserTD(scanner);
        } else if(!language.equalsIgnoreCase("Pascal")) {
            throw new Exception("Parser factory: Invalid language '" + language + "'");
        } else {
            throw new Exception("Parser factory: Invalid type '" + type + "'");
        }
    }
}
