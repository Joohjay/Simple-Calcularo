import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double num1 = 0, num2 = 0;
        char operator;
        boolean validInput = false;

        // Input validation for the first number
        while (!validInput) {
            System.out.println("Enter first number: ");
            if (input.hasNextDouble()) {
                num1 = input.nextDouble();
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                input.next(); // Clear invalid input
            }
        }

        validInput = false;

        // Input validation for the operator
        while (!validInput) {
            System.out.println("Enter operator (+, -, *, /):");
            operator = input.next().charAt(0);
            if (operator == '+' || operator == '-' || operator == '*' || operator == '/') {
                validInput = true;
            } else {
                System.out.println("Invalid operator. Please enter one of +, -, *, /.");
            }
        }

        validInput = false;

        // Input validation for the second number
        while (!validInput) {
            System.out.println("Enter second number: ");
            if (input.hasNextDouble()) {
                num2 = input.nextDouble();
                if (operator == '/' && num2 == 0) {
                    System.out.println("Error: Cannot divide by zero. Please enter a non-zero number.");
                } else {
                    validInput = true;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                input.next(); // Clear invalid input
            }
        }

        // Perform the calculation
        double result;

        switch (operator) {
            case '+': result = num1 + num2; break;
            case '-': result = num1 - num2; break;
            case '*': result = num1 * num2; break;
            case '/': result = num1 / num2; break;
            default:
                System.out.println("Invalid operator");
                return;
        }
        System.out.println("Result: " + result);
    }
}