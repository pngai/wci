package frontend;

import intermediate.ICode;
import intermediate.SymTab;
import message.Message;
import message.MessageHandler;
import message.MessageListener;
import message.MessageProducer;

/**
 * Created by patrick on 2016/5/7.
 */

/**
 * <h1>Parser</h1>
 *
 * <p>A language independent framework class. This abstract parser class will be implemented by language -specific subclasses.</p>
 */
public abstract class Parser implements MessageProducer{
    protected  static SymTab symTab;
    protected static MessageHandler messageHandler;

    static {
        symTab = null;
        messageHandler = new MessageHandler();
    }
    protected Scanner scanner;
    protected ICode iCode;

    /**
     * Constructor.
     * @param scanner the scanner to be used with this parser
     */
    protected Parser(Scanner scanner){
        this.scanner = scanner;
        this.iCode = null;
    }

    /**
     * Parse a source program and generate the intermediate code and the symbol table. To be implemented by a language specific parser subclass.
     * @throws Exception if an error occured.
     */
    public abstract void parse() throws Exception;

    /**
     * Return the number of syntax errors found by the parser. To be implemented by a language specific parser subclass.
     * @return the error count.
     */
    public abstract int getErrorCount();

    /**
     * Call the scanner's currentToken() method.
     * @return the current Token.
     */
    public Token currentToken(){
        return scanner.currentToken();
    }

    /**
     * Call the scanner's nextToken() method.
     * @return the next token.
     * @throws Exception if an error occured.
     */
    public Token nextToken() throws Exception {
        return scanner.nextToken();
    }

    /**
     * Add a parser message listener.
     * @param listener the message listener to add.
     */
    public void addMessageListener(MessageListener listener) {
        messageHandler.addListener(listener);
    }

    /**
     * Remove a parser message listener.
     * @param listener the message listener to remove.
     */
    public void removeMessageListener(MessageListener listener) {
        messageHandler.removeListener(listener);
    }

    /**
     * Notify listeners after setting the message.
     * @param message the message to set.
     */
    public void sendMessage(Message message) {
        messageHandler.sendMessage(message);
    }

    public ICode getICode() {
        return this.iCode;
    }

    public SymTab getSymTab() {
        return this.symTab;
    }
}
