package frontend;

import message.*;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by patrick on 2016/5/7.
 */

/**
 * <h1>Source</h1>
 *
 * <p>The framework class that represents the source program.</p>
 */
public class Source implements MessageProducer {
    public static final char EOL = '\n';
    public static final char EOF = (char) 0;

    private BufferedReader reader;
    private String line;
    private int lineNum;
    private int currentPos;
    protected static MessageHandler messageHandler;

    static {
        messageHandler = new MessageHandler();
    }

    /**
     * Constructor.
     * @param reader the reader for source program
     * @throws IOException if an I/O error occured
     */
    public Source(BufferedReader reader) throws IOException {
        this.lineNum = 0;
        this.currentPos = -2;   //set to -2 to read the first source line
        this.reader = reader;
    }

    /**
     * Return the source character at the current position.
     * @return the source character at the current position.
     * @throws Exception if an error occurred.
     */
    public char currentChar() throws Exception {
        //first time read
        if(currentPos == -2) {
            readLine();
            return nextChar();
        }
        //end of file
        else if(line == null){
            return EOF;
        }
        //end of line
        else if((currentPos == -1) || (currentPos == line.length())) {
            return EOL;
        }
        //read next line
        else if(currentPos > line.length()) {
            readLine();
            return nextChar();
        } else {
            return line.charAt(currentPos);
        }
    }

    /**
     * Consumes the current source character and return the next character.
     * @return the next source character.
     * @throws Exception if an error occurred.
     */
    public char nextChar() throws Exception {
        ++currentPos;
        return currentChar();
    }

    /**
     * Return the source character following the current  character without consuming the current character.
     * @return the following character.
     * @throws Exception if an error occurred.
     */
    public char peekChar() throws Exception {
        currentChar();
        if(line == null){
            return EOF;
        }
        int nextPos = currentPos + 1;
        return nextPos < line.length() ? line.charAt(nextPos) : EOL;
    }

    /**
     * Read the next source line.
     * @throws IOException if an I/O error occurred.
     */
    private void readLine() throws IOException {
        line = reader.readLine();   //null when at the end of source
        currentPos = -1;
        if(line != null){
            ++lineNum;
        }
        if(line != null) {
            sendMessage(new Message(MessageType.SOURCE_LINE, new Object[]{lineNum, line}));
        }
    }

    /**
     * Close the source.
     * @throws Exception if an error occurred.
     */
    public void close() throws Exception {
        if(reader != null) {
            try {
                reader.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
    }

    public int getLineNum() {
        return lineNum;
    }

    public int getPosition() {
        return currentPos;
    }

    public void addMessageListener(MessageListener listener) {
        messageHandler.addListener(listener);
    }

    public void removeMessageListener(MessageListener listener) {
        messageHandler.removeListener(listener);
    }

    public void sendMessage(Message message) {
        messageHandler.sendMessage(message);
    }
}
