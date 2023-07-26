//https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html
//https://docs.oracle.com/javase/8/docs/api/javax/swing/JPanel.html
//https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwiAzaOS_6yAAxWtppUCHaxpBkAQFnoECCYQAQ&url=https%3A%2F%2Fwww.javadoc.io%2Fdoc%2Forg.functionaljava%2Ffunctionaljava%2F4.1%2Ffj%2Fdata%2FHashMap.html&usg=AOvVaw01iqn0loUdW8ZoILt86deU&opi=89978449
//https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
//https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwjt6LvO_6yAAxVRpZUCHSysBv0QFnoECA4QAQ&url=https%3A%2F%2Fwww.javatpoint.com%2Fjava-actionlistener&usg=AOvVaw0OkldejmO-Vo0Nlg0UfzlE&opi=89978449
//https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwjt6LvO_6yAAxVRpZUCHSysBv0QFnoECBMQAQ&url=https%3A%2F%2Fdocs.oracle.com%2Fjavase%2F8%2Fdocs%2Fapi%2Fjava%2Fawt%2Fevent%2FActionListener.html&usg=AOvVaw3CxNH91fJB36F4MT6sTjDX&opi=89978449
//https://www.google.com/url?sa=t&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwjz7qfe_6yAAxWxrpUCHYtlDkoQFnoECA4QAQ&url=https%3A%2F%2Fdocs.oracle.com%2Fjavase%2F8%2Fdocs%2Fapi%2Fjava%2Fawt%2Fevent%2FActionEvent.html&usg=AOvVaw1gl03GpC-kwiucAwYz_kH8&opi=89978449
//https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwjI7qfp_6yAAxUnppUCHffMDZgQFnoECA4QAQ&url=https%3A%2F%2Fwww.javatpoint.com%2Fjava-swing&usg=AOvVaw2AZ5vBUdvAkkIK5EgtBsBS&opi=89978449
//https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwiGhf3x_6yAAxXrrJUCHbb4A7sQFnoECBYQAQ&url=https%3A%2F%2Fwww.javatpoint.com%2Fjava-awt&usg=AOvVaw2ZU3Su38v-mUY4c9MtVyNe&opi=89978449

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class LoginSystemGUI extends JFrame {
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JLabel messageLabel;
    private Map<String, String> users;

    public LoginSystemGUI() {
        // Configurações básicas da janela
        setTitle("Sistema de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Inicialização do mapa de usuários
        users = new HashMap<>();
        users.put("user1", "password1");
        users.put("user2", "password2");

        // Painel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Interface
        JLabel usernameLabel = new JLabel("User:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JButton createButton = new JButton("Create User");
        messageLabel = new JLabel();

        // Ação do botão "Login"
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (login(username, password)) {
                    messageLabel.setText("Login bem-sucedido!");
                } else {
                    messageLabel.setText("Login falhou. Verifique suas credenciais!");
                }

                // Limpar campos de texto
                usernameTextField.setText("");
                passwordField.setText("");
            }
        });

        // Botão "Criar Usuário"
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (createUser(username, password)) {
                    JOptionPane.showMessageDialog(LoginSystemGUI.this, "Usuário criado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(LoginSystemGUI.this, "Nome de usuário já existe ou informações inválidas.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                // Limpar campos de texto
                usernameTextField.setText("");
                passwordField.setText("");
            }
        });

        // Adicionando componentes ao painel principal
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        mainPanel.add(usernameTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        mainPanel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        mainPanel.add(loginButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        mainPanel.add(createButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        mainPanel.add(messageLabel, constraints);

        // Adicionando o painel principal à janela
        add(mainPanel);

        // Ajuste da largura e altura da janela
        setSize(400, 250); // Aumentando a altura da janela para acomodar os campos de texto maiores

        // Centraliza a janela na tela
        setLocationRelativeTo(null);
    }

    public boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public boolean createUser(String username, String password) {
        if (!users.containsKey(username) && username.length() >= 3 && password.length() >= 6) {
            users.put(username, password);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginSystemGUI().setVisible(true);
            }
        });
    }
}
