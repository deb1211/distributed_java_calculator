package com.distributedcalculator;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;
import software.amazon.awssdk.services.sqs.model.Message;

import java.net.URI;
import java.util.List;

public class SqsMessageHandler implements MessageHandler{

    String accessKeyId = "foo";
    String secretAccessKey = "bar";
    private Sqs sqsWithCredentials = new Sqs(URI.create("http://localhost:4566"),
            StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey)));

    CreateQueueResponse queue;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getSecretAccessKey() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public void createQueue() {
        queue = sqsWithCredentials.createQueue("createQueue");
    }

    public List<Message> receiveMessage() {
        List<Message> messages = sqsWithCredentials.receiveMessage(queue.queueUrl());
        return messages;
    }

    public void sendMessage(String message) {
        sqsWithCredentials.sendMessage(queue.queueUrl(), message);
    }

    public void deleteMessageList(List<Message> messages) {
        sqsWithCredentials.deleteMessageList(queue.queueUrl(), messages);
    }
}
