import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> history = new ArrayList<>(); // To store calculation history
        boolean continueCalculating = true;

        System.out.println("Welcome to the Advanced Calculator!");

        while (continueCalculating) {
            // Display menu
            System.out.println("\nChoose a mode:");
            System.out.println("1. Simple Expression Evaluation (e.g., 2 + 3 * 5)");
            System.out.println("2. Advanced Functions (√, sin, cos, tan, log, ln)");
            System.out.println("3. Unit Conversion");
            System.out.println("4. View Calculation History");
            System.out.println("5. Exit");
            int mode = input.nextInt();
            input.nextLine(); // Consume newline

            if (mode == 1) {
                // Handle Complex Expression Evaluation
                System.out.println("Enter a mathematical expression (e.g., 2 + 3 * 5):");
                String expression = input.nextLine();

                try {
                    double result = evaluateExpression(expression);
                    String calculation = expression + " = " + result;
                    history.add(calculation); // Add to history
                    System.out.println("Result: " + result);
                } catch (Exception e) {
                    System.out.println("Invalid expression. Please try again.");
                }

            } else if (mode == 2) {
                // Handle Advanced Functions
                handleAdvancedFunctions(input, history);

            } else if (mode == 3) {
                // Unit Conversion
                handleUnitConversion(input, history);

            } else if (mode == 4) {
                // View Calculation History
                if (history.isEmpty()) {
                    System.out.println("No calculations have been performed yet.");
                } else {
                    System.out.println("Calculation History:");
                    for (String record : history) {
                        System.out.println(record);
                    }
                }
            } else if (mode == 5) {
                // Exit
                continueCalculating = false;
                System.out.println("Thank you for using the Calculator!");
            } else {
                System.out.println("Invalid mode selected.");
            }
        }

        input.close();
    }

    // Evaluate mathematical expressions
    private static double evaluateExpression(String expression) throws Exception {
        return new ScriptEngineManager().getEngineByName("JavaScript").eval(expression) instanceof Number
                ? ((Number) new ScriptEngineManager().getEngineByName("JavaScript").eval(expression)).doubleValue()
                : 0.0;
    }

    // Advanced functions handler
    private static void handleAdvancedFunctions(Scanner input, ArrayList<String> history) {
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
    }

    // Unit conversion handler
    private static void handleUnitConversion(Scanner input, ArrayList<String> history) {
        System.out.println("Choose a unit conversion category:");
        System.out.println("1. Length (meters ↔ feet, kilometers ↔ miles)");
        System.out.println("2. Weight (kilograms ↔ pounds, grams ↔ ounces)");
        System.out.println("3. Temperature (Celsius ↔ Fahrenheit ↔ Kelvin)");
        int category = input.nextInt();

        switch (category) {
            case 1: // Length Conversion
                System.out.println("Enter value: ");
                double lengthValue = input.nextDouble();
                System.out.println("Convert (1: meters to feet, 2: feet to meters, 3: kilometers to miles, 4: miles to kilometers): ");
                int lengthChoice = input.nextInt();
                double lengthResult = 0;
                switch (lengthChoice) {
                    case 1: lengthResult = lengthValue * 3.28084; break; // meters to feet
                    case 2: lengthResult = lengthValue / 3.28084; break; // feet to meters
                    case 3: lengthResult = lengthValue * 0.621371; break; // kilometers to miles
                    case 4: lengthResult = lengthValue / 0.621371; break; // miles to kilometers
                }
                System.out.println("Result: " + lengthResult);
                history.add("Length Conversion: " + lengthValue + " → " + lengthResult);
                break;

            case 2: // Weight Conversion
                System.out.println("Enter value: ");
                double weightValue = input.nextDouble();
                System.out.println("Convert (1: kilograms to pounds, 2: pounds to kilograms, 3: grams to ounces, 4: ounces to grams): ");
                int weightChoice = input.nextInt();
                double weightResult = 0;
                switch (weightChoice) {
                    case 1: weightResult = weightValue * 2.20462; break; // kilograms to pounds
                    case 2: weightResult = weightValue / 2.20462; break; // pounds to kilograms
                    case 3: weightResult = weightValue * 0.035274; break; // grams to ounces
                    case 4: weightResult = weightValue / 0.035274; break; // ounces to grams
                }
                System.out.println("Result: " + weightResult);
                history.add("Weight Conversion: " + weightValue + " → " + weightResult);
                break;

            case 3: // Temperature Conversion
                System.out.println("Enter value: ");
                double tempValue = input.nextDouble();
                System.out.println("Convert (1: Celsius to Fahrenheit, 2: Fahrenheit to Celsius, 3: Celsius to Kelvin, 4: Kelvin to Celsius): ");
                int tempChoice = input.nextInt();
                double tempResult = 0;
                switch (tempChoice) {
                    case 1: tempResult = (tempValue * 9/5) + 32; break; // Celsius to Fahrenheit
                    case 2: tempResult = (tempValue - 32) * 5/9; break; // Fahrenheit to Celsius
                    case 3: tempResult = tempValue + 273.15; break; // Celsius to Kelvin
                    case 4: tempResult = tempValue - 273.15; break; // Kelvin to Celsius
                }
                System.out.println("Result: " + tempResult);
                history.add("Temperature Conversion: " + tempValue + " → " + tempResult);
                break;

            default:
                System.out.println("Invalid category.");
        }
    }
}