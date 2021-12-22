public class CalculorDataExpression {
    private String operator;
    private String operandFirst;
    private String operandSecond;
    private String calculorDataExpressionID;

    public CalculorDataExpression(String operator, String operandFirst, String operandSecond, String calculorDataExpressionID) {
        this.operator = operator;
        this.operandFirst = operandFirst;
        this.operandSecond = operandSecond;
        this.calculorDataExpressionID = calculorDataExpressionID;
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

    public String getExpressionID() {
        return calculorDataExpressionID;
    }

}
