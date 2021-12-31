package com.distributedcalculator;

public class CalculorDataExpression {
    private String operator;
    private String operandFirst;
    private String operandSecond;
    private String calculatorDataExpressionID;
    private String calculatedResult;
    private boolean requireCalculation = true;

    public CalculorDataExpression(String operator, String operandFirst, String operandSecond, String calculatorDataExpressionID) {
        this.operator = operator;
        this.operandFirst = operandFirst;
        this.operandSecond = operandSecond;
        this.calculatorDataExpressionID = calculatorDataExpressionID;
    }

    public String getOperator() {
        return operator;
    }

    public String getOperandFirst() {
        return operandFirst;
    }

    public String getOperandSecond() {
        return operandSecond;
    }

    public String getCalculatedResult() {
        return calculatedResult;
    }

    public String getExpressionID() {
        return calculatorDataExpressionID;
    }

    public void setRequireCalculation(boolean requireCalculation) {
        this.requireCalculation = requireCalculation;
    }

    public boolean getRequireCalculation() {
        return requireCalculation;
    }

    public void setCalculatedResult(String calculatedResult) {
        this.calculatedResult = calculatedResult;
    }
}
