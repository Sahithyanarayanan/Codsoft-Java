import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CurrencyConverterGUI extends JFrame implements ActionListener {

    private JComboBox<String> baseCurrency;
    private JComboBox<String> targetCurrency;
    private JTextField amountField;
    private JLabel resultLabel;

    private HashMap<String, Double> exchangeRates;

    public CurrencyConverterGUI() {
        setTitle("💱 Currency Converter - CODSOFT");
        setSize(400, 300);
        setLayout(new GridLayout(6, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Currency options and rates (base: INR)
        String[] currencies = {"INR", "USD", "EUR", "JPY", "GBP"};
        exchangeRates = new HashMap<>();
        exchangeRates.put("INR", 1.0);
        exchangeRates.put("USD", 0.012);
        exchangeRates.put("EUR", 0.011);
        exchangeRates.put("JPY", 1.75);
        exchangeRates.put("GBP", 0.0095);

        // Components
        baseCurrency = new JComboBox<>(currencies);
        targetCurrency = new JComboBox<>(currencies);
        amountField = new JTextField();
        resultLabel = new JLabel("", JLabel.CENTER);
        JButton convertButton = new JButton("Convert");

        // Add listeners
        convertButton.addActionListener(this);

        // Add components to frame
        add(new JLabel("Select Base Currency:", JLabel.CENTER));
        add(baseCurrency);
        add(new JLabel("Select Target Currency:", JLabel.CENTER));
        add(targetCurrency);
        add(new JLabel("Enter Amount:", JLabel.CENTER));
        add(amountField);
        add(convertButton);
        add(resultLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String base = (String) baseCurrency.getSelectedItem();
        String target = (String) targetCurrency.getSelectedItem();

        if (base.equals(target)) {
            resultLabel.setText("⚠️ Base and target currencies must be different.");
            return;
        }

        String amountText = amountField.getText().trim();
        double amount;

        try {
            amount = Double.parseDouble(amountText);
            if (amount < 0) {
                resultLabel.setText("❌ Amount must be positive.");
                return;
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("❌ Invalid amount.");
            return;
        }

        // Conversion: Convert to INR first, then to target
        double amountInINR = amount / exchangeRates.get(base);
        double converted = amountInINR * exchangeRates.get(target);

        resultLabel.setText(String.format("✅ %.2f %s = %.2f %s", amount, base, converted, target));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverterGUI());
    }
}