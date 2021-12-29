import software.amazon.awssdk.services.sqs.model.Message;

import java.util.List;

public interface MessageHandler {

    public void sendMessage(String message);

    public List<Message> receiveMessage();
}
