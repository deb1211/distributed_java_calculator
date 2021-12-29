package com.distributedcalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DatabaseManager {

    private Connection connection = null;
    private Statement statement = null;

    public void dbHandler(CalculorDataExpression dataExpression) {
        try
        {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/distributed_calculator_application_db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "root", "password");
/*
            statement = connection.createStatement();
            // Step 3: Execute a SQL SELECT query
            String sqlStr = "select * from calculationstable where operator = " + dataExpression.getOperator()
                    + " AND operandFirst = " + dataExpression.getOperandFirst()
                    + " AND operandSecond = " + dataExpression.getOperandSecond()
                    ;

            //fix the sql here..

            ResultSet rset = statement.executeQuery(sqlStr);
*/

            PreparedStatement preparedStatement = connection.prepareStatement("select * from calculationstable where operator = ?"
                    + " AND operandFirst = ?"
                    + " AND operandSecond = ?");

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

    public void insertIntoDatabaseCalculatedResult (CalculorDataExpression dataExpression) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into calculationstable (operator, operandFirst, operandSecond, " +
                "calculatedresult, calculorDataExpressionID) VALUES (");
        stringBuilder.append("\"" + dataExpression.getOperator());
        stringBuilder.append("\"" + dataExpression.getOperandFirst() + "\"");
        stringBuilder.append("\"" + dataExpression.getOperandSecond() + "\"");
        stringBuilder.append("\"" + dataExpression.getCalculatedResult() + "\"");
        stringBuilder.append("\"" + dataExpression.getExpressionID() + "\")");

        String sqlStr = stringBuilder.toString();

/*        String sqlStr = "insert into calculationstable (operator, operandFirst, operandSecond, " +
                "calculatedresult, calculorDataExpressionID) VALUES (" + "\"" + dataExpression.getOperator()
                + "\"" + dataExpression.getOperandFirst() + "\""
                + "\"" + dataExpression.getOperandSecond() + "\""
                + "\"" + dataExpression.getCalculatedResult() + "\""
                + "\"" + dataExpression.getExpressionID() + "\")";*/

        try {
            ResultSet rset = statement.executeQuery(sqlStr);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
