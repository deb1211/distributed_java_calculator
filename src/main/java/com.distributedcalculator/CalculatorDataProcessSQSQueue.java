package com.distributedcalculator;

import javax.ejb.Singleton;
import java.util.List;
import software.amazon.awssdk.services.sqs.model.Message;

@Singleton
public class CalculatorDataProcessSQSQueue {

    private static CalculatorDataProcessSQSQueue singleInstanceCalculatorAddDataToSQSQueue = null;

    private CalculatorDataProcessSQSQueue() { }

    public static CalculatorDataProcessSQSQueue getInstance()
    {
        if (singleInstanceCalculatorAddDataToSQSQueue == null)
            singleInstanceCalculatorAddDataToSQSQueue = new CalculatorDataProcessSQSQueue();
        return singleInstanceCalculatorAddDataToSQSQueue;
    }

    public void addMessageToQueue(String message, SqsMessageHandler sqsMessageHandler) {
        sqsMessageHandler.sendMessage(message);
    }

    public void deleteMessageFromQueue(List<Message> messages, SqsMessageHandler sqsMessageHandler) {
        sqsMessageHandler.deleteMessageList(messages);
    }

    public List<Message> receiveMessage(SqsMessageHandler sqsMessageHandler) {
        return sqsMessageHandler.receiveMessage();
    }
}
