package com.distributedcalculator;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import software.amazon.awssdk.services.sqs.model.Message;

@WebServlet("/expressionoperation")
public class CalculatorDataProcessor extends HttpServlet {
    static int uniqueExpressionID = 1;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestUrl = request.getRequestURI();
        //TODO: change the expressionID...
        uniqueExpressionID++;
        String expressionID = String.valueOf(uniqueExpressionID);

        //TODO: delete below code, only for testing



        String operator = "+";
        String operandFirst = "4";
        String operandSecond = "12";
        String calculorDataExpressionID = "testID123";

        CalculatorDataStorage.getCalculatorDataStorage().putCalculorDataExpression(new CalculorDataExpression(operator, operandFirst, operandSecond, expressionID));

        CalculorDataExpression dataExpression = CalculatorDataStorage.getCalculatorDataStorage().getCalculatorDataExpression(expressionID);
        DatabaseManager databaseManager = new DatabaseManager();
        /*databaseManager.dbHandler(dataExpression);*/
        CalculatorDataStorage.getCalculatorDataStorage().processExpressionData(databaseManager, dataExpression);

        if (dataExpression.getRequireCalculation()) {
            //add to SQS here
            SqsMessageHandler sqsMessageHandler = new SqsMessageHandler();
            sqsMessageHandler.createQueue();
            CalculatorDataProcessSQSQueue calculatorDataProcessSQSQueue = CalculatorDataProcessSQSQueue.getInstance();
            String message = dataExpression.getOperator() + "," + dataExpression.getOperandFirst() + ","
                    + dataExpression.getOperandSecond() + "," + dataExpression.getExpressionID();
            calculatorDataProcessSQSQueue.addMessageToQueue(message, sqsMessageHandler);

            List<Message> listMessages = calculatorDataProcessSQSQueue.receiveMessage(sqsMessageHandler);
            calculatorDataProcessSQSQueue.deleteMessageFromQueue(listMessages, sqsMessageHandler);

            for (Message messageSQS : listMessages) {
                List<String> expressionParameters = new LinkedList<String>(Arrays.asList(messageSQS.body().split(",")));
                String dataExpressionID = expressionParameters.get(3);

                CalculorDataExpression retrieveddataExpression = CalculatorDataStorage.getCalculatorDataStorage().getCalculatorDataExpression(dataExpressionID);

                //requesthandler lambda
                CalculationServiceLambdaHandler calculationServiceLambdaHandler = new CalculationServiceLambdaHandler();
                calculationServiceLambdaHandler.handleRequest((Object) retrieveddataExpression, null);

                //to this point the value is calculated
                databaseManager.insertIntoDatabaseCalculatedResult(dataExpression);
            }
        }
        if(dataExpression != null) {
            String json = "{\n";
            json += "\"operator\": " + JSONObject.quote(dataExpression.getOperator()) + ",\n";
            json += "\"operandFirst\": " + JSONObject.quote(dataExpression.getOperandFirst()) + ",\n";
            json += "\"operandSecond\": " + JSONObject.quote(dataExpression.getOperandSecond()) + "\n";
            json += "\"calculorDataExpressionID\": " + JSONObject.quote(dataExpression.getExpressionID()) + "\n";
            json += "\"calculatedResult\": " + JSONObject.quote(dataExpression.getCalculatedResult()) + "\n";
            json += "}";
            response.getOutputStream().println(json);
        }
        else {
            response.getOutputStream().println("{}");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestUrl = request.getRequestURI();
        //TODO: change the expressionID...
        uniqueExpressionID++;
        String expressionID = String.valueOf(uniqueExpressionID);

        //TODO: delete below code, only for testing



        String operator = "+";
        String operandFirst = "4";
        String operandSecond = "12";
        String calculorDataExpressionID = "testID123";

        CalculatorDataStorage.getCalculatorDataStorage().putCalculorDataExpression(new CalculorDataExpression(operator, operandFirst, operandSecond, expressionID));

        CalculorDataExpression dataExpression = CalculatorDataStorage.getCalculatorDataStorage().getCalculatorDataExpression(expressionID);
        DatabaseManager databaseManager = new DatabaseManager();
        /*databaseManager.dbHandler(dataExpression);*/
        CalculatorDataStorage.getCalculatorDataStorage().processExpressionData(databaseManager, dataExpression);

        if (dataExpression.getRequireCalculation()) {
            //add to SQS here
            SqsMessageHandler sqsMessageHandler = new SqsMessageHandler();
            sqsMessageHandler.createQueue();
            CalculatorDataProcessSQSQueue calculatorDataProcessSQSQueue = CalculatorDataProcessSQSQueue.getInstance();
            String message = dataExpression.getOperator() + "," + dataExpression.getOperandFirst() + ","
                    + dataExpression.getOperandSecond() + "," + dataExpression.getExpressionID();
            calculatorDataProcessSQSQueue.addMessageToQueue(message, sqsMessageHandler);

            List<Message> listMessages = calculatorDataProcessSQSQueue.receiveMessage(sqsMessageHandler);
            calculatorDataProcessSQSQueue.deleteMessageFromQueue(listMessages, sqsMessageHandler);

            for (Message messageSQS : listMessages) {
                List<String> expressionParameters = new LinkedList<String>(Arrays.asList(messageSQS.body().split(",")));
                String dataExpressionID = expressionParameters.get(3);

                CalculorDataExpression retrieveddataExpression = CalculatorDataStorage.getCalculatorDataStorage().getCalculatorDataExpression(dataExpressionID);

                //requesthandler lambda
                CalculationServiceLambdaHandler calculationServiceLambdaHandler = new CalculationServiceLambdaHandler();
                calculationServiceLambdaHandler.handleRequest((Object) retrieveddataExpression, null);

                //to this point the value is calculated
                databaseManager.insertIntoDatabaseCalculatedResult(dataExpression);
            }
        }
        if(dataExpression != null) {
            String json = "{\n";
            json += "\"operator\": " + JSONObject.quote(dataExpression.getOperator()) + ",\n";
            json += "\"operandFirst\": " + JSONObject.quote(dataExpression.getOperandFirst()) + ",\n";
            json += "\"operandSecond\": " + JSONObject.quote(dataExpression.getOperandSecond()) + "\n";
            json += "\"calculorDataExpressionID\": " + JSONObject.quote(dataExpression.getExpressionID()) + "\n";
            json += "\"calculatedResult\": " + JSONObject.quote(dataExpression.getCalculatedResult()) + "\n";
            json += "}";
            response.getOutputStream().println(json);
        }
        else {
            response.getOutputStream().println("{}");
        }



    }


}
