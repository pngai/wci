package intermediate;

/**
 * Created by patrick on 2016/5/11.
 */

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * <h1>SymTabEntry</h1>
 *
 * <p>The interface for a symbol table entry.</p>
 */
public interface SymTabEntry {
    /**
     * Getter.
     * @return the name of the entry.
     */
    String getName();

    /**
     * Getter.
     * @return the symbol table that contains this entry.
     */
    SymTab getSymTab();

    /**
     * Append a line number to the entry.
     * @param lineNumber the line number to append
     */
    void appendLineNumber(int lineNumber);

    /**
     * Getter.
     * @return the list of source line numbers.
     */
    ArrayList<Integer> getLineNumbers();

    /**
     * Set an attribute of the entry.
     * @param key the attribute key.
     * @param value the attribute value.
     */
    void setAttribute(SymTabKey key, Object value);

    /**
     * Get the value of an attribute of the entry.
     * @param key the attribute key.
     * @return the attribute value.
     */
    Object getAttribute(SymTabKey key);

}
