import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField display;          // Text field to display input and result
    private StringBuilder inputBuffer;  // To store the current input
    private ArrayList<String> history;  // To store calculation history
    private double memory;              // Memory storage for the calculator

    public CalculatorGUI() {
        // Initialize the input buffer, history, and memory
        inputBuffer = new StringBuilder();
        history = new ArrayList<>();
        memory = 0;

        // Set up the frame
        setTitle("Scientific Calculator");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);   // Make the display read-only
        add(display, BorderLayout.NORTH);

        // Create the panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 10, 10)); // 6x4 grid with spacing

        // Add buttons for numbers, operations, memory, constants, and parentheses
        String[] buttons = {
                "7", "8", "9", "/", 
                "4", "5", "6", "*", 
                "1", "2", "3", "-", 
                "0", "C", "=", "+",
                "(", ")", "π", "e",
                "sqrt", "log", "sin", "cos",
                "M+", "M-", "MR", "MC"
        };
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this); // Add action listener
            buttonPanel.add(button);
        }

        // Add the button panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Set the frame to visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Get the button text

        switch (command) {
            case "C": // Clear the input
                inputBuffer.setLength(0); // Clear the buffer
                display.setText("");
                break;
            case "=": // Evaluate the expression
                try {
                    double result = evaluateExpression(inputBuffer.toString());
                    String calculation = inputBuffer.toString() + " = " + result;
                    history.add(calculation); // Add to history
                    display.setText(String.valueOf(result));
                    inputBuffer.setLength(0); // Clear the buffer for new input
                    inputBuffer.append(result); // Allow further calculations
                } catch (Exception ex) {
                    display.setText("Error");
                    inputBuffer.setLength(0); // Clear the buffer on error
                }
                break;
            case "sqrt": // Square root
                try {
                    double result = Math.sqrt(Double.parseDouble(inputBuffer.toString()));
                    display.setText(String.valueOf(result));
                    inputBuffer.setLength(0);
                    inputBuffer.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "log": // Logarithm
                try {
                    double result = Math.log10(Double.parseDouble(inputBuffer.toString()));
                    display.setText(String.valueOf(result));
                    inputBuffer.setLength(0);
                    inputBuffer.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "sin": // Sine
                try {
                    double result = Math.sin(Math.toRadians(Double.parseDouble(inputBuffer.toString())));
                    display.setText(String.valueOf(result));
                    inputBuffer.setLength(0);
                    inputBuffer.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "cos": // Cosine
                try {
                    double result = Math.cos(Math.toRadians(Double.parseDouble(inputBuffer.toString())));
                    display.setText(String.valueOf(result));
                    inputBuffer.setLength(0);
                    inputBuffer.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "π": // Pi constant
                inputBuffer.append(Math.PI);
                display.setText(inputBuffer.toString());
                break;
            case "e": // Euler's constant
                inputBuffer.append(Math.E);
                display.setText(inputBuffer.toString());
                break;
            case "(": // Open parenthesis
            case ")": // Close parenthesis
                inputBuffer.append(command);
                display.setText(inputBuffer.toString());
                break;
            case "M+": // Add to memory
                try {
                    memory += Double.parseDouble(display.getText());
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "M-": // Subtract from memory
                try {
                    memory -= Double.parseDouble(display.getText());
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "MR": // Recall memory
                display.setText(String.valueOf(memory));
                inputBuffer.setLength(0);
                inputBuffer.append(memory);
                break;
            case "MC": // Clear memory
                memory = 0;
                display.setText("");
                break;
            default: // Append numbers and operators to the buffer
                inputBuffer.append(command);
                display.setText(inputBuffer.toString());
                break;
        }
    }

    // Evaluate mathematical expressions
    private double evaluateExpression(String expression) throws Exception {
        return new javax.script.ScriptEngineManager()
                .getEngineByName("JavaScript")
                .eval(expression) instanceof Number
                ? ((Number) new javax.script.ScriptEngineManager()
                        .getEngineByName("JavaScript")
                        .eval(expression))
                        .doubleValue()
                : 0.0;
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}