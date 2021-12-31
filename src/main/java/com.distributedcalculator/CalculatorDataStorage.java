package com.distributedcalculator;

import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.Map;


//We will use Mysql data storage
@Singleton
public class CalculatorDataStorage {
    private Map<String, CalculatorDataExpression> calculorDataExpressionMap = new HashMap<>();

    private CalculatorDataStorage() {};

    private static CalculatorDataStorage calculatorDataStorage = new CalculatorDataStorage();
    public static CalculatorDataStorage getCalculatorDataStorage() {
        return calculatorDataStorage;
    }

    public CalculatorDataExpression getCalculatorDataExpression(String expressionID) {
        return calculorDataExpressionMap.get(expressionID);
    }

    public void putCalculorDataExpression(CalculatorDataExpression calculorDataExpression) {
        calculorDataExpressionMap.put(calculorDataExpression.getExpressionID(), calculorDataExpression);
    }

    public void processExpressionData(DatabaseManager databaseManager, CalculatorDataExpression dataExpression) {
        databaseManager.dbHandler(dataExpression);
    }

}
