package message;

import java.util.ArrayList;

/**
 * Created by patrick on 2016/5/8.
 */
public class MessageHandler {
    private Message message;
    private ArrayList<MessageListener> listeners;

    /**
     * Constructor.
     */
    public MessageHandler() {
        this.listeners = new ArrayList<MessageListener>();
    }

    /**
     * Add a listener to the listeners list.
     * @param listener the listener to add.
     */
    public void addListener(MessageListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Remove a listener from the listeners list.
     * @param listener the listener to remove.
     */
    public void removeListener(MessageListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Notify listeners after setting the message.
     * @param message the message to set.
     */
    public void sendMessage(Message message) {
        this.message = message;
        notifyListeners();
    }

    /**
     * Notify each listener in the listeners list by calling the listener's messageReceived() method.
     */
    private void notifyListeners() {
        for(MessageListener listener : listeners) {
            listener.messageReceived(message);
        }
    }
}
