package message;

/**
 * Created by patrick on 2016/5/8.
 */
public interface MessageListener {
    /**
     * Called to receive a message sent by a message producer.
     * @param message the message that was sent.
     */
    public void messageReceived(Message message);
}
