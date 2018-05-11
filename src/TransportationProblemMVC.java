import Controller.Controller;
import Model.MatrixModel;
import View.TransportationView;

public class TransportationProblemMVC {

    public static void main(String[] args){

        TransportationView mainView = new TransportationView();
        MatrixModel mainModel = new MatrixModel();

        Controller mainController = new Controller(mainModel, mainView);

        mainView.setVisible(true);

    }

}
