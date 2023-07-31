package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CoinConverter extends JFrame {
    private JTextField valueField;
    private JFormattedTextField valueCoinField;
    private JLabel rersultLabel;

    public CoinConverter() {
        setTitle("Coin Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os elementos

        // Componentes da interface
        JLabel valorDolarLabel = new JLabel("Value:");
        valueField = new JTextField(10);

        JLabel valueCoinField = new JLabel("Coin Value:");
        NumberFormat format = new DecimalFormat("#0.00");
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0.01);
        formatter.setMaximum(9999.99);
        formatter.setCommitsOnValidEdit(true);
        this.valueCoinField = new JFormattedTextField(formatter);
        this.valueCoinField.setColumns(10);

        JButton calculateButton = new JButton("Calculate");
        JButton clearButton = new JButton("Clear");
        rersultLabel = new JLabel();

        // Adicionar componentes ao JFrame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(valorDolarLabel, gbc);

        gbc.gridx = 1;
        add(valueField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(valueCoinField, gbc);

        gbc.gridx = 1;
        add(this.valueCoinField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(calculateButton, gbc);

        gbc.gridy = 3;
        add(clearButton, gbc);

        gbc.gridy = 4;
        add(rersultLabel, gbc);

        // Ação do botão Calcular
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotalValue();
            }
        });

        // Ação do botão Limpar
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                valueField.setText("");
                CoinConverter.this.valueCoinField.setValue(null);
                rersultLabel.setText("");
            }
        });
    }

    private void calculateTotalValue() {
        try {
            double value = Double.parseDouble(valueField.getText());
            double coinValue = ((Number) valueCoinField.getValue()).doubleValue();

            double valorReais = value * coinValue;
            double tax = valorReais * 0.06;
            double valueTotal = valorReais + tax;

            rersultLabel.setText(String.format("O valor em reais, com 6%% de imposto, é: R$ %.2f", valueTotal));
        } catch (NumberFormatException ex) {
            rersultLabel.setText("Erro: Digite números válidos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CoinConverter converter = new CoinConverter();
                converter.setVisible(true);
            }
        });
    }
}
