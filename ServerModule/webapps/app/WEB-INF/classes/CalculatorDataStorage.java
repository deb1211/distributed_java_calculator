import java.util.HashMap;
import java.util.Map;


//We will use Mysql data storage
public class CalculatorDataStorage {
    private Map<String, CalculorDataExpression> calculorDataExpressionMap = new HashMap<>();

    private static CalculatorDataStorage calculatorDataStorage = new CalculatorDataStorage();
    public static CalculatorDataStorage getCalculatorDataStorage() {
        return calculatorDataStorage;
    }

    public CalculorDataExpression getCalculatorDataExpression(String expressionID) {
        return calculorDataExpressionMap.get(expressionID);
    }

    public void putCalculorDataExpression(CalculorDataExpression calculorDataExpression) {
        calculorDataExpressionMap.put(calculorDataExpression.getExpressionID(), calculorDataExpression);
    }

}
