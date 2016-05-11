package intermediate.symtabImpl;

import intermediate.SymTab;
import intermediate.SymTabEntry;
import intermediate.SymTabKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by patrick on 2016/5/11.
 */

/**
 * <h1>SymTabEntryImpl</h1>
 *
 * <p>An implementation of a symbol table entry.</p>
 */
public class SymTabEntryImpl extends HashMap<SymTabKey, Object> implements SymTabEntry {
    private String name;                        //entry name
    private SymTab symTab;                      //parent symbol table
    private ArrayList<Integer> lineNumbers;     //source line numbers
    /**
     * Constructor.
     * @param name the name of the entry.
     * @param symTab the symbol table that contains this entry.
     */
    public SymTabEntryImpl(String name, SymTab symTab) {
        this.name = name;
        this.symTab = symTab;
        this.lineNumbers = new ArrayList<Integer>();
    }

    public String getName() {
        return name;
    }

    public SymTab getSymTab() {
        return symTab;
    }

    /**
     * Append a source line number to the entry.
     * @param lineNumber the line number to append.
     */
    public void appendLineNumber(int lineNumber) {
        lineNumbers.add(lineNumber);
    }

    public ArrayList<Integer> getLineNumbers() {
        return lineNumbers;
    }

    /**
     * Set an attribute of the entry.
     * @param key the attribute key.
     * @param value the attribute value.
     */
    public void setAttribute(SymTabKey key, Object value) {
        put(key,value);
    }

    public Object getAttribute(SymTabKey key) {
        return get(key);
    }
}
