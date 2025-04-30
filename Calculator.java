import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> history = new ArrayList<>(); // To store calculation history
        boolean continueCalculating = true;

        System.out.println("Welcome to the Calculator!");

        while (continueCalculating) {
            // Display menu
            System.out.println("\nChoose a mode:");
            System.out.println("1. Basic Operations (+, -, *, /, %, ^)");
            System.out.println("2. Advanced Functions (√, sin, cos, tan, log, ln)");
            System.out.println("3. View Calculation History");
            System.out.println("4. Exit");
            int mode = input.nextInt();

            if (mode == 1) {
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
                    System.out.println("Enter operator (+, -, *, /, %, ^):");
                    operator = input.next().charAt(0);
                    if (operator == '+' || operator == '-' || operator == '*' || operator == '/' ||
                        operator == '%' || operator == '^') {
                        validInput = true;
                    } else {
                        System.out.println("Invalid operator. Please enter one of +, -, *, /, %, ^.");
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
                double result = 0;

                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/': result = num1 / num2; break;
                    case '%': result = num1 % num2; break;
                    case '^': result = Math.pow(num1, num2); break;
                    default:
                        System.out.println("Invalid operator");
                        return;
                }
                String calculation = num1 + " " + operator + " " + num2 + " = " + result;
                history.add(calculation); // Add to history
                System.out.println("Result: " + result);

            } else if (mode == 2) {
                // Handle Advanced Functions
                System.out.println("Choose a function:");
                System.out.println("1. Square Root (√)");
                System.out.println("2. Power (x^n)");
                System.out.println("3. Sine (sin)");
                System.out.println("4. Cosine (cos)");
                System.out.println("5. Tangent (tan)");
                System.out.println("6. Logarithm (log)");
                System.out.println("7. Natural Logarithm (ln)");
                int choice = input.nextInt();

                double num1 = 0, num2 = 0;
                String calculation = "";

                switch (choice) {
                    case 1: // Square Root
                        System.out.println("Enter a number: ");
                        num1 = input.nextDouble();
                        double sqrtResult = Math.sqrt(num1);
                        calculation = "√" + num1 + " = " + sqrtResult;
                        System.out.println("Result: " + sqrtResult);
                        break;
                    case 2: // Power
                        System.out.println("Enter the base number: ");
                        num1 = input.nextDouble();
                        System.out.println("Enter the exponent: ");
                        num2 = input.nextDouble();
                        double powerResult = Math.pow(num1, num2);
                        calculation = num1 + " ^ " + num2 + " = " + powerResult;
                        System.out.println("Result: " + powerResult);
                        break;
                    case 3: // Sine
                        System.out.println("Enter a number (in degrees): ");
                        num1 = input.nextDouble();
                        double sinResult = Math.sin(Math.toRadians(num1));
                        calculation = "sin(" + num1 + ") = " + sinResult;
                        System.out.println("Result: " + sinResult);
                        break;
                    case 4: // Cosine
                        System.out.println("Enter a number (in degrees): ");
                        num1 = input.nextDouble();
                        double cosResult = Math.cos(Math.toRadians(num1));
                        calculation = "cos(" + num1 + ") = " + cosResult;
                        System.out.println("Result: " + cosResult);
                        break;
                    case 5: // Tangent
                        System.out.println("Enter a number (in degrees): ");
                        num1 = input.nextDouble();
                        double tanResult = Math.tan(Math.toRadians(num1));
                        calculation = "tan(" + num1 + ") = " + tanResult;
                        System.out.println("Result: " + tanResult);
                        break;
                    case 6: // Logarithm
                        System.out.println("Enter a number: ");
                        num1 = input.nextDouble();
                        double logResult = Math.log10(num1);
                        calculation = "log(" + num1 + ") = " + logResult;
                        System.out.println("Result: " + logResult);
                        break;
                    case 7: // Natural Logarithm
                        System.out.println("Enter a number: ");
                        num1 = input.nextDouble();
                        double lnResult = Math.log(num1);
                        calculation = "ln(" + num1 + ") = " + lnResult;
                        System.out.println("Result: " + lnResult);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
                if (!calculation.isEmpty()) {
                    history.add(calculation); // Add to history
                }

            } else if (mode == 3) {
                // View Calculation History
                if (history.isEmpty()) {
                    System.out.println("No calculations have been performed yet.");
                } else {
                    System.out.println("Calculation History:");
                    for (String record : history) {
                        System.out.println(record);
                    }
                }
            } else if (mode == 4) {
                // Exit
                continueCalculating = false;
                System.out.println("Thank you for using the Calculator!");
            } else {
                System.out.println("Invalid mode selected.");
            }
        }

        input.close();
    }
}