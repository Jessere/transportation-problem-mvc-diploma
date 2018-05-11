package Controller;

import Model.MatrixModel;
import View.MatrixView;
import View.TransportationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private MatrixModel controllerModel;
    private MatrixView controllerMatrixView;
    private TransportationView controllerView;

    public Controller(MatrixModel model, TransportationView view){
        controllerModel = model;
        controllerView = view;

        controllerView.setButtonListener(new ButtonListener());
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int m;
            int n;
            int p;

            try{
                m = controllerView.getM();
                n = controllerView.getN();
                p = controllerView.getP();

                controllerMatrixView = new MatrixView(m, n, p);
                controllerMatrixView.setConfirmListener(new ConfirmListener());
            } catch (NumberFormatException ex){
                System.out.println(ex);
                controllerView.showMessageDialog("Нужно ввести целые числа");
            }
        }
    }

    private class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controllerModel.setMainMatrix(controllerMatrixView.getUserMatrix());
            controllerModel.setParamA(controllerMatrixView.getParamA());
            controllerModel.setParamB(controllerMatrixView.getParamB());
            controllerModel.setParamC(controllerMatrixView.getParamC());
            if (!controllerModel.areParamsCorrect(controllerModel.getParamA(), controllerModel.getParamB(), controllerModel.getParamC())){
                controllerView.showMessageDialog("Введены некорректные ограничения задачи");
            } else {
                controllerModel.setSolutionMatrix(new int[controllerView.getM()][controllerView.getN()][controllerView.getP()]);
                controllerModel.getInitialSolution(controllerModel.getSolutionMatrix(), controllerModel.getParamA(), controllerModel.getParamB(), controllerModel.getParamC());
                controllerModel.getUvw(controllerModel.getSolutionMatrix(), controllerModel.getMainMatrix(), controllerView.getM(), controllerView.getN(), controllerView.getP());
            }
        }
    }
}
