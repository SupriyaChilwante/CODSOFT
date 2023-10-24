import javax.swing.*;

class BankAccountModified {
	private double accountBalance;

	public BankAccountModified(double balance) {
		this.accountBalance = balance;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void depositFunds(double amount) {
		accountBalance += amount;
	}

	public boolean withdrawFunds(double amount) {
		if (accountBalance >= amount) {
			accountBalance -= amount;
			return true;
		} else {
			return false;
		}
	}
}

public class ATMInterface extends JFrame {
	private BankAccountModified bankAccount;

	public ATMInterface(BankAccountModified account) {
		this.bankAccount = account;
		setTitle("ATM Interface");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLookAndFeel();

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel amountLabel = new JLabel("Amount:");
		JTextField amountField = new JTextField(10);
		JButton depositButton = new JButton("Deposit");
		JButton withdrawButton = new JButton("Withdraw");
		JButton balanceButton = new JButton("Check Balance");
		JTextArea resultArea = new JTextArea(5, 20);

		panel.add(amountLabel);
		panel.add(amountField);
		panel.add(depositButton);
		panel.add(withdrawButton);
		panel.add(balanceButton);
		panel.add(resultArea);

		depositButton.addActionListener(e -> {
			double amount = Double.parseDouble(amountField.getText());
			bankAccount.depositFunds(amount);
			JOptionPane.showMessageDialog(null, "Deposit successful. Current balance: " + bankAccount.getAccountBalance());
			amountField.setText("");
		});

		withdrawButton.addActionListener(e -> {
			double amount = Double.parseDouble(amountField.getText());
			if (bankAccount.withdrawFunds(amount)) {
				JOptionPane.showMessageDialog(null, "Withdrawal successful. Current balance: " + bankAccount.getAccountBalance());
			} else {
				JOptionPane.showMessageDialog(null, "Insufficient balance. Withdrawal failed.");
			}
			amountField.setText("");
		});

		balanceButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "Your current balance is: " + bankAccount.getAccountBalance());
		});

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(panel);
		setVisible(true);
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BankAccountModified account = new BankAccountModified(1000); // initial balance set to 1000
		new ATMInterface(account);
	}
}

