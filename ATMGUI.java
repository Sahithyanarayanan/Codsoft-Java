import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// BankAccount class to manage balance
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// ATM GUI class
public class ATMGUI extends JFrame implements ActionListener {
    private BankAccount account;
    private JTextField amountField;
    private JLabel balanceLabel, messageLabel;

    public ATMGUI() {
        account = new BankAccount(1000);

        setTitle("🏧 ATM Interface - CODSOFT");
        setSize(400, 300);
        setLayout(new GridLayout(6, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        balanceLabel = new JLabel("Balance: ₹" + account.getBalance(), JLabel.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(balanceLabel);

        amountField = new JTextField();
        add(new JLabel("Enter Amount (₹):", JLabel.CENTER));
        add(amountField);

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton checkBalanceButton = new JButton("Check Balance");

        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        checkBalanceButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(checkBalanceButton);
        add(buttonPanel);

        messageLabel = new JLabel("", JLabel.CENTER);
        messageLabel.setForeground(Color.BLUE);
        add(messageLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String input = amountField.getText().trim();
        double amount = 0;

        if (!command.equals("Check Balance")) {
            try {
                amount = Double.parseDouble(input);
                if (amount <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                messageLabel.setText("❌ Enter a valid positive amount.");
                return;
            }
        }

        switch (command) {
            case "Deposit":
                if (account.deposit(amount)) {
                    messageLabel.setText("✅ ₹" + amount + " deposited.");
                } else {
                    messageLabel.setText("❌ Deposit failed.");
                }
                break;
            case "Withdraw":
                if (account.withdraw(amount)) {
                    messageLabel.setText("✅ ₹" + amount + " withdrawn.");
                } else {
                    messageLabel.setText("❌ Insufficient balance or invalid amount.");
                }
                break;
            case "Check Balance":
                messageLabel.setText("💰 Current Balance: ₹" + account.getBalance());
                break;
        }

        balanceLabel.setText("Balance: ₹" + account.getBalance());
        amountField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMGUI());
    }
}
