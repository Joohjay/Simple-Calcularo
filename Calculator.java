import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double num1 = 0, num2 = 0;
        char operator;
        boolean validInput = false;

        System.out.println("Welcome to the Calculator!");

        // Display menu
        System.out.println("Choose a mode:");
        System.out.println("1. Basic Operations (+, -, *, /, %, ^)");
        System.out.println("2. Advanced Functions (√, sin, cos, tan, log, ln)");
        int mode = input.nextInt();

        // Handle Basic Operations
        if (mode == 1) {
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

            switch (choice) {
                case 1: // Square Root
                    System.out.println("Enter a number: ");
                    num1 = input.nextDouble();
                    System.out.println("Result: " + Math.sqrt(num1));
                    break;
                case 2: // Power
                    System.out.println("Enter the base number: ");
                    num1 = input.nextDouble();
                    System.out.println("Enter the exponent: ");
                    num2 = input.nextDouble();
                    System.out.println("Result: " + Math.pow(num1, num2));
                    break;
                case 3: // Sine
                    System.out.println("Enter a number (in degrees): ");
                    num1 = input.nextDouble();
                    System.out.println("Result: " + Math.sin(Math.toRadians(num1)));
                    break;
                case 4: // Cosine
                    System.out.println("Enter a number (in degrees): ");
                    num1 = input.nextDouble();
                    System.out.println("Result: " + Math.cos(Math.toRadians(num1)));
                    break;
                case 5: // Tangent
                    System.out.println("Enter a number (in degrees): ");
                    num1 = input.nextDouble();
                    System.out.println("Result: " + Math.tan(Math.toRadians(num1)));
                    break;
                case 6: // Logarithm
                    System.out.println("Enter a number: ");
                    num1 = input.nextDouble();
                    System.out.println("Result: " + Math.log10(num1));
                    break;
                case 7: // Natural Logarithm
                    System.out.println("Enter a number: ");
                    num1 = input.nextDouble();
                    System.out.println("Result: " + Math.log(num1));
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("Invalid mode selected.");
        }

        input.close();
    }
}