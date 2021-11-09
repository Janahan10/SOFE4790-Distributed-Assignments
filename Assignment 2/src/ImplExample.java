import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;

// Implementing the remote interface 
public class ImplExample implements Hello {  
   
    // Implementing the interface method 
    public String printDate() {  
        String result;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime today = LocalDateTime.now();  
        result = "Current Date is: " + dateFormatter.format(today);
        System.out.println(result);
        return result;
    }  

    // method for calculating quadratic roots (SERVICE)
    public String calculateRoots(int[] coefficients) {
        String result;

        int a = coefficients[0];
        int b = coefficients[1];
        int c = coefficients[2];

        // calculate determinant of roots
        double determinant = Math.pow(b, 2) - 4 * a * c;

        // based on determinant find the roots of the equation
        if (determinant < 0) {
            result = "There are no roots because the determinant is less than 0.";
        } else if(determinant == 0) {
            double root = Math.round((-b / (2 * a)) * 100.0) / 100.0;
            result = "Since determinant is 0, there is only 1 root. The root is " + root + ".";
        } else {
            double root1 = Math.round((-b + Math.sqrt(determinant)) * 100.0) / 100.0;
            double root2 = Math.round((-b - Math.sqrt(determinant)) * 100.0) / 100.0;

            result = "There are 2 roots. There 2 roots are " + root1 + " and " + root2 + ".";
        }

        return result;
    }

    public String flipString(String userInput) {
        String result = "";
        userInput.trim();

        for (int i = userInput.length() - 1; i >= 0; i--) {
            result += userInput.charAt(i);
        }

        return result;
    }

    public String decimalBaseConverter(int num) {
        String temp = "";
        String result = "";
        int divisor = 2;

        while (num > 0) {
            int digit = num % divisor;
            num /= divisor;
            temp += Integer.toString(digit);
        }
        
        for (int i = temp.length() - 1; i >= 0; i--) {
            result += temp.charAt(i);
        }
        return result;
    }

    public String checkEquation(String equation) {
        if (equation.length() == 0) {
            return "There was no expression passed.";
        }
        String temp = "";
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '(' || equation.charAt(i) == ')') {
                temp += equation.charAt(i);
            }
        }

        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) == '(') {
                stack.add(temp.charAt(i));
            } else {
                if (temp.charAt(i) == ')' && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.add(')');
                }
            }
        }

        if (stack.empty()) {
            return "Result: Expression is valid all brackets match";
        }

        return "Result: Brackets do not match, there is an extra bracket";
    }
 } 
 