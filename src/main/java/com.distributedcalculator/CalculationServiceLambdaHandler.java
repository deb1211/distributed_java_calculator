package com.distributedcalculator;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CalculationServiceLambdaHandler implements
        RequestHandler<Object, Object> {

    @Override
    public Object handleRequest(Object input, Context context) {


        CalculorDataExpression calculorDataExpression = (CalculorDataExpression) input;
        calculatorComputeService(calculorDataExpression);

        return null;
    }

    public void calculatorComputeService(CalculorDataExpression calculorDataExpression) {
        double firstOperand = Integer.parseInt(calculorDataExpression.getOperandFirst());
        double secondOperand = Integer.parseInt(calculorDataExpression.getOperandSecond());
        String operator = calculorDataExpression.getOperator();
        double result = 0.0;
        if (operator.equals("*")) {
            result = firstOperand * secondOperand;
        }
        else if (operator.equals("/")) {
            result = firstOperand / secondOperand;
        }
        else if (operator.equals("+")) {
            result = firstOperand + secondOperand;
        }
        else if (operator.equals("-")) {
            result = firstOperand - secondOperand;
        }
        calculorDataExpression.setCalculatedResult(String.valueOf(result));
    }

}