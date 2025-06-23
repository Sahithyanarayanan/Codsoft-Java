import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculatorGUI extends JFrame implements ActionListener {

    private JTextField[] subjectFields;
    private JButton calculateButton;
    private JTextArea resultArea;
    private int numSubjects = 5; // You can change this to allow dynamic subject count

    public StudentGradeCalculatorGUI() {
        setTitle("📚 Student Grade Calculator");
        setSize(400, 400);
        setLayout(new GridLayout(numSubjects + 3, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        subjectFields = new JTextField[numSubjects];

        for (int i = 0; i < numSubjects; i++) {
            subjectFields[i] = new JTextField();
            add(new JLabel("Enter marks for Subject " + (i + 1) + " (out of 100):"));
            add(subjectFields[i]);
        }

        calculateButton = new JButton("Calculate Grade");
        calculateButton.addActionListener(this);
        add(calculateButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int totalMarks = 0;
        boolean valid = true;

        for (int i = 0; i < numSubjects; i++) {
            String input = subjectFields[i].getText().trim();
            int mark;

            try {
                mark = Integer.parseInt(input);
                if (mark < 0 || mark > 100) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Invalid input in Subject " + (i + 1) + ". Enter marks between 0 and 100.");
                valid = false;
                break;
            }

            totalMarks += mark;
        }

        if (!valid) return;

        double average = (double) totalMarks / numSubjects;
        String grade;

        if (average >= 90) {
            grade = "A+";
        } else if (average >= 80) {
            grade = "A";
        } else if (average >= 70) {
            grade = "B";
        } else if (average >= 60) {
            grade = "C";
        } else if (average >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        resultArea.setText("🎓 RESULT:\n");
        resultArea.append("Total Marks: " + totalMarks + "/" + (numSubjects * 100) + "\n");
        resultArea.append(String.format("Average Percentage: %.2f%%\n", average));
        resultArea.append("Grade: " + grade);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentGradeCalculatorGUI());
    }
}
