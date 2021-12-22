import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/expressionoperation")
public class CalculatorDataServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestUrl = request.getRequestURI();
        //TODO: change the expressionID...
        /*String expressionID = requestUrl.substring("/expression/".length());*/
        String expressionID = "testID123";



        //TODO: delete below code, only for testing
        String operator = "+";
        String operandFirst = "4";
        String operandSecond = "6";
        String calculorDataExpressionID = "testID123";
        CalculatorDataStorage.getCalculatorDataStorage().putCalculorDataExpression(new CalculorDataExpression(operator, operandFirst, operandSecond, calculorDataExpressionID));

        CalculorDataExpression dataExpression = CalculatorDataStorage.getCalculatorDataStorage().getCalculatorDataExpression(expressionID);
        if(dataExpression != null) {
            String json = "{\n";
            json += "\"operator\": " + JSONObject.quote(dataExpression.getOperator()) + ",\n";
            json += "\"operandFirst\": " + JSONObject.quote(dataExpression.getOperandFirst()) + ",\n";
            json += "\"operandSecond\": " + JSONObject.quote(dataExpression.getOperandSecond()) + "\n";
            json += "\"calculorDataExpressionID\": " + JSONObject.quote(dataExpression.getExpressionID()) + "\n";
            json += "}";
            response.getOutputStream().println(json);
        }
        else {
            response.getOutputStream().println("{}");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String operator = request.getParameter("operator");
        String operandFirst = request.getParameter("operandFirst");
        String operandSecond = request.getParameter("operandSecond");
        String calculorDataExpressionID = request.getParameter("testID123");

        CalculatorDataStorage.getCalculatorDataStorage().putCalculorDataExpression(new CalculorDataExpression(operator, operandFirst, operandSecond, calculorDataExpressionID));

    }


}
