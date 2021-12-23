import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;
import software.amazon.awssdk.services.sqs.model.Message;

@Singleton
public class CalculatorAddDataToSQSQueue {

    private static CalculatorAddDataToSQSQueue singleInstanceCalculatorAddDataToSQSQueue = null;

    private CalculatorAddDataToSQSQueue() { }
    List<String> sqsQueueURL = new ArrayList<String>();

    public static CalculatorAddDataToSQSQueue getInstance()
    {
        if (singleInstanceCalculatorAddDataToSQSQueue == null)
            singleInstanceCalculatorAddDataToSQSQueue = new CalculatorAddDataToSQSQueue();
        return singleInstanceCalculatorAddDataToSQSQueue;
    }

    public void addSQSQueueURL(String queueURL) {
        sqsQueueURL.add(queueURL);
    }

    public void addMessageToQueue(String message, SqsMessageHandler sqsMessageHandler) {
       //TODO: add message to queue as per the queue URL
        sqsMessageHandler.sendMessage(message);
    }

    public void deleteMessageFromQueue(List<Message> messages, SqsMessageHandler sqsMessageHandler) {
        sqsMessageHandler.deleteMessageList(messages);
    }

    public List<Message> receiveMessage(SqsMessageHandler sqsMessageHandler) {
        return sqsMessageHandler.receiveMessage();
    }
}
