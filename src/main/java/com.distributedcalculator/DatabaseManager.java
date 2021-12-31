package com.distributedcalculator;

import java.sql.*;

public class DatabaseManager {

    private Connection connection = null;
    private Statement statement = null;

    public void dbHandler(CalculatorDataExpression dataExpression) {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/distributed_calculator_application_db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "root", "password");

            PreparedStatement preparedStatement = connection.prepareStatement("select * from calculationstable where operator = ?"
                    + " AND operandFirst = ?"
                    + " AND operandSecond = ? LIMIT 1");

            preparedStatement.setString(1, dataExpression.getOperator());
            preparedStatement.setString(2, dataExpression.getOperandFirst());
            preparedStatement.setString(3, dataExpression.getOperandSecond());

            ResultSet rset = preparedStatement.executeQuery();

            while(rset.next()) {
                dataExpression.setRequireCalculation(false);
                dataExpression.setCalculatedResult(rset.getString("calculatedresult"));
            }
        } catch(Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
    }

    public void insertIntoDatabaseCalculatedResult (CalculatorDataExpression dataExpression) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/distributed_calculator_application_db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "root", "password");

            PreparedStatement preparedStatement = connection.prepareStatement("insert into calculationstable (operator, operandFirst, operandSecond, " +
                    "calculatedresult, calculorDataExpressionID) VALUES ( ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, dataExpression.getOperator());
            preparedStatement.setString(2, dataExpression.getOperandFirst());
            preparedStatement.setString(3, dataExpression.getOperandSecond());
            preparedStatement.setString(4, dataExpression.getCalculatedResult());
            preparedStatement.setString(5, dataExpression.getExpressionID());

            preparedStatement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
