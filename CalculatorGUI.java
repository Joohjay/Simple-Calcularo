import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField display;          // Text field to display input and result
    private StringBuilder inputBuffer;  // To store the current input
    private ArrayList<String> history;  // To store calculation history
    private double memory;              // Memory storage for the calculator
    private JComboBox<String> decimalSelector; // Dropdown for selecting decimal places
    private int decimalPlaces;          // Stores the selected number of decimal places
    private boolean isDarkMode = false; // Tracks the current theme (false = light, true = dark)

    public CalculatorGUI() {
        // Initialize the input buffer, history, memory, and default decimal places
        inputBuffer = new StringBuilder();
        history = new ArrayList<>();
        memory = 0;
        decimalPlaces = 2; // Default to 2 decimal places

        // Set up the frame
        setTitle("Scientific Calculator");
        setSize(500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);   // Make the display read-only
        add(display, BorderLayout.NORTH);

        // Create a panel for the theme toggle and decimal place selector
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(display, BorderLayout.CENTER);

        // Create the dropdown for decimal places
        String[] decimalOptions = {"0", "1", "2", "3", "4", "5"};
        decimalSelector = new JComboBox<>(decimalOptions);
        decimalSelector.setSelectedIndex(2); // Default to 2 decimal places
        decimalSelector.addActionListener(e -> decimalPlaces = decimalSelector.getSelectedIndex());

        // Create theme toggle button
        JButton themeToggle = new JButton("Toggle Theme");
        themeToggle.addActionListener(e -> toggleTheme());

        // Add theme selector and decimal selector to the panel
        JPanel selectorPanel = new JPanel();
        selectorPanel.add(new JLabel("Precision:"));
        selectorPanel.add(decimalSelector);
        selectorPanel.add(themeToggle);
        topPanel.add(selectorPanel, BorderLayout.SOUTH);

        // Add the top panel to the frame
        add(topPanel, BorderLayout.NORTH);

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

        // Assign colors to different types of buttons
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this); // Add action listener

            // Set colors based on button type
            if ("0123456789".contains(text)) { // Numbers
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            } else if ("/*-+=()".contains(text)) { // Operations
                button.setBackground(new Color(173, 216, 230)); // Light blue
                button.setForeground(Color.BLACK);
            } else if ("πe".contains(text)) { // Constants
                button.setBackground(new Color(255, 228, 196)); // Light brown
                button.setForeground(Color.BLACK);
            } else if ("sqrtlogsinccos".contains(text)) { // Functions
                button.setBackground(new Color(240, 230, 140)); // Light yellow
                button.setForeground(Color.BLACK);
            } else if ("M+M-MRMC".contains(text)) { // Memory functions
                button.setBackground(new Color(255, 182, 193)); // Light pink
                button.setForeground(Color.BLACK);
            } else { // Default for other buttons (e.g., C, =)
                button.setBackground(new Color(255, 160, 122)); // Light coral
                button.setForeground(Color.BLACK);
            }

            buttonPanel.add(button);
        }

        // Add the button panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Apply the light theme by default
        applyLightTheme();

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
                    String formattedResult = formatResult(result);
                    String calculation = inputBuffer.toString() + " = " + formattedResult;
                    history.add(calculation); // Add to history
                    display.setText(formattedResult);
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
                    display.setText(formatResult(result));
                    inputBuffer.setLength(0);
                    inputBuffer.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "log": // Logarithm
                try {
                    double result = Math.log10(Double.parseDouble(inputBuffer.toString()));
                    display.setText(formatResult(result));
                    inputBuffer.setLength(0);
                    inputBuffer.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "sin": // Sine
                try {
                    double result = Math.sin(Math.toRadians(Double.parseDouble(inputBuffer.toString())));
                    display.setText(formatResult(result));
                    inputBuffer.setLength(0);
                    inputBuffer.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "cos": // Cosine
                try {
                    double result = Math.cos(Math.toRadians(Double.parseDouble(inputBuffer.toString())));
                    display.setText(formatResult(result));
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
                display.setText(formatResult(memory));
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

    // Format result to the selected number of decimal places
    private String formatResult(double result) {
        StringBuilder format = new StringBuilder("#.");
        for (int i = 0; i < decimalPlaces; i++) {
            format.append("#");
        }
        DecimalFormat df = new DecimalFormat(format.toString());
        return df.format(result);
    }

    // Toggle between light and dark themes
    private void toggleTheme() {
        if (isDarkMode) {
            applyLightTheme();
        } else {
            applyDarkTheme();
        }
        isDarkMode = !isDarkMode;
    }

    // Apply the light theme to the calculator
    private void applyLightTheme() {
        getContentPane().setBackground(Color.WHITE);
        display.setBackground(Color.WHITE);
        display.setForeground(Color.BLACK);
    }

    // Apply the dark theme to the calculator
    private void applyDarkTheme() {
        getContentPane().setBackground(Color.DARK_GRAY);
        display.setBackground(Color.DARK_GRAY);
        display.setForeground(Color.WHITE);
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}