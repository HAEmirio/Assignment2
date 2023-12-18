import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalaryCalculatorGUI extends JFrame {

    private JTextField hoursWorkedField, salesField;
    private JTextArea resultArea;

    public SalaryCalculatorGUI() {
        setTitle("MagicCopy Sdn. Bhd. Salary Calculator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel typeLabel = new JLabel("Employee Type:");
        typeLabel.setBounds(10, 20, 120, 25);
        panel.add(typeLabel);

        JComboBox<String> employeeTypeComboBox = new JComboBox<>(new String[]{"Monthly Worker", "Hourly Worker", "Commission Worker"});
        employeeTypeComboBox.setBounds(140, 20, 150, 25);
        panel.add(employeeTypeComboBox);

        JLabel hoursWorkedLabel = new JLabel("Hours Worked:");
        hoursWorkedLabel.setBounds(10, 50, 120, 25);
        panel.add(hoursWorkedLabel);

        hoursWorkedField = new JTextField(20);
        hoursWorkedField.setBounds(140, 50, 150, 25);
        panel.add(hoursWorkedField);

        JLabel salesLabel = new JLabel("Weekly Sales:");
        salesLabel.setBounds(10, 80, 120, 25);
        panel.add(salesLabel);

        salesField = new JTextField(20);
        salesField.setBounds(140, 80, 150, 25);
        panel.add(salesField);

        JButton calculateButton = new JButton("Calculate Salary");
        calculateButton.setBounds(10, 120, 150, 25);
        panel.add(calculateButton);

        resultArea = new JTextArea();
        resultArea.setBounds(180, 120, 200, 25);
        resultArea.setEditable(false);
        panel.add(resultArea);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedType = employeeTypeComboBox.getSelectedIndex();
                double result = 0;

                switch (selectedType) {
                    case 0: // Monthly Worker
                        result = calculatePayMonthlyWorker();
                        break;
                    case 1: // Hourly Worker
                        result = calculatePayHourlyWorker();
                        break;
                    case 2: // Commission Worker
                        result = calculatePayCommWorker();
                        break;
                }

                resultArea.setText("Salary: RM" + String.format("%.2f", result));
            }
        });
    }

    private double calculatePayMonthlyWorker() {
        return 2000.00;
    }

    private double calculatePayHourlyWorker() {
        try {
            double hoursWorked = Double.parseDouble(hoursWorkedField.getText());
            double hourlyRate = 30.00;

            if (hoursWorked <= 40) {
                return hoursWorked * hourlyRate;
            } else {
                double overtimeHours = hoursWorked - 40;
                return (40 * hourlyRate) + (overtimeHours * 1.5 * hourlyRate);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for hours worked. Please enter a number.");
            return 0;
        }
    }

    private double calculatePayCommWorker() {
        try {
            double weeklySales = Double.parseDouble(salesField.getText());
            return 500.00 + (0.055 * weeklySales);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for weekly sales. Please enter a number.");
            return 0;
        }
    }

    public static void main(String[] args) {
        new SalaryCalculatorGUI();
    }
}
