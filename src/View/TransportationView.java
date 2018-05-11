package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TransportationView extends JFrame {

    private JTextField mTF = new JTextField(5);
    private JTextField nTF = new JTextField(5);
    private JTextField pTF = new JTextField(5);
    private JButton readyButton = new JButton("Ввести значения матрицы");

    public TransportationView(){
        JPanel mainPanel = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel mnpPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JLabel headerLabel = new JLabel("Введите m, n, p");
        headerPanel.add(headerLabel);

        mnpPanel.add(mTF);
        mnpPanel.add(nTF);
        mnpPanel.add(pTF);

        buttonPanel.add(readyButton);

        mainPanel.add(headerPanel);
        mainPanel.add(mnpPanel);
        mainPanel.add(buttonPanel);

        this.add(mainPanel);
    }

    public void setButtonListener(ActionListener buttonListener){
        readyButton.addActionListener(buttonListener);
    }

    public int getM(){
        return Integer.parseInt(mTF.getText());
    }

    public int getN(){
        return Integer.parseInt(nTF.getText());
    }

    public int getP(){
        return Integer.parseInt(pTF.getText());
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
