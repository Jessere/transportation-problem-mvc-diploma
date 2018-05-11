package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class MatrixView extends JFrame {
    private JButton confirmButton;
    private JTextField[] paramATF;
    private JTextField[] paramBTF;
    private JTextField[] paramCTF;
    private JTextField[][][] userMatrixField;
    private int m;
    private int n;
    private int p;

    public MatrixView(int m, int n, int p) {
        this.m = m;
        this.n = n;
        this.p = p;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(300, 350);


        JPanel mainMatrixPanel = new JPanel();
        JPanel matrixPanel = new JPanel(new GridLayout(m, n));
        JPanel paramAPanel = new JPanel();
        JPanel paramBPanel = new JPanel();
        JPanel paramCPanel = new JPanel();
        JPanel paramsPanel = new JPanel(new GridLayout(3, 1));
        JPanel buttonPanel = new JPanel();
        JPanel headerPanel = new JPanel();

        confirmButton = new JButton("Подтвердить значения");
        paramATF = new JTextField[m];
        paramBTF = new JTextField[n];
        paramCTF = new JTextField[p];
        JLabel matrixHeaderLabel = new JLabel("Введите значения матрицы и ограничения");

        userMatrixField = new JTextField[m][n][p];
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                for (int k=0; k<p; k++){
                    String index = "m:" + (i + 1) + " n:" + (j + 1) + " p:" + (k + 1);
                    userMatrixField[i][j][k] = new JTextField(index);
                    matrixPanel.add(userMatrixField[i][j][k]);
                }
            }
        }

        headerPanel.add(matrixHeaderLabel);

        buttonPanel.add(confirmButton);

        for (int i=0; i<m; i++){
            String index = "i:" +(i+1);
            paramATF[i] = new JTextField(index, 10);
            paramAPanel.add(paramATF[i]);
        }

        for (int i=0; i<n; i++){
            String index = "j:" +(i+1);
            paramBTF[i] = new JTextField(index, 10);
            paramBPanel.add(paramBTF[i]);
        }

        for (int i=0; i<p; i++){
            String index = "k:" +(i+1);
            paramCTF[i] = new JTextField(index, 10);
            paramCPanel.add(paramCTF[i]);
        }

        Border paramABorder = BorderFactory.createTitledBorder("Ограничения A");
        paramAPanel.setBorder(paramABorder);

        Border paramBBorder = BorderFactory.createTitledBorder("Ограничения B");
        paramBPanel.setBorder(paramBBorder);

        Border paramCBorder = BorderFactory.createTitledBorder("Ограничения C");
        paramCPanel.setBorder(paramCBorder);

        paramsPanel.add(paramAPanel);
        paramsPanel.add(paramBPanel);
        paramsPanel.add(paramCPanel);

        mainMatrixPanel.add(headerPanel);
        mainMatrixPanel.add(matrixPanel);
        mainMatrixPanel.add(paramsPanel);
        mainMatrixPanel.add(buttonPanel);

        this.add(mainMatrixPanel);
        this.setVisible(true);
    }

    public void setConfirmListener(ActionListener confirmListener){
        confirmButton.addActionListener(confirmListener);
    }

    public int[] getParamA(){
        int[] userParamAMatrix = new int[m];
        for (int i=0; i<m; i++){
            userParamAMatrix[i] = Integer.parseInt(paramATF[i].getText());
        }
        return userParamAMatrix;
    }

    public int[] getParamB(){
        int[] userParamBMatrix = new int[m];
        for (int i=0; i<n; i++){
            userParamBMatrix[i] = Integer.parseInt(paramBTF[i].getText());
        }
        return userParamBMatrix;
    }

    public int[] getParamC(){
        int[] userParamCMatrix = new int[m];
        for (int i=0; i<p; i++){
            userParamCMatrix[i] = Integer.parseInt(paramCTF[i].getText());
        }
        return userParamCMatrix;
    }

    public int[][][] getUserMatrix(){
        int[][][] userMatrix = new int[m][n][p];
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                for (int k=0; k<p; k++){
                    userMatrix[i][j][k] = Integer.parseInt(userMatrixField[i][j][k].getText());
                }
            }
        }
        return userMatrix;
    }

}
