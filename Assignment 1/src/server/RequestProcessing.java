package server;

public class RequestProcessing {
    public String calculateRoots(float a, float b, float c) {
        String result;

        // calculate determinant of roots
        double determinant = Math.pow(b, 2) - 4 * a * c;

        // based on determinant find the roots of the equation
        if (determinant < 0) {
            result = "There are no roots because the determinant is less than 0";
        } else if(determinant == 0) {
            double root = Math.round((-b / (2 * a)) * 100) / 100;
            result = "Since determinant is 0, there is only 1 root. The root is " + root;
        } else {
            double root1 = Math.round((-b + Math.sqrt(determinant)) * 100) / 100;
            double root2 = Math.round((-b - Math.sqrt(determinant)) * 100) / 100;

            result = "There are 2 roots. There 2 roots are " + root1 + "and " + root2;
        }

        return result;
    }
}
