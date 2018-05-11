package Model;

public class MatrixModel {

    private int[][][] mainMatrix;
    private int[][][] solutionMatrix;
    private int[] paramA;
    private int[] paramB;
    private int[] paramC;

    public MatrixModel(){}

    public void getInitialSolution(int[][][] solutionMatrix, int[] paramA, int[] paramB, int[] paramC) {
        int checkA;
        int checkB;
        int checkC;
        int solutionA = 0;
        int solutionB = 0;
        int solutionC = 0;

        System.out.println("Поиск начального опорного плана");
        while (true){
            solutionMatrix[solutionA][solutionB][solutionC] = min(paramA[solutionA], paramB[solutionB], paramC[solutionC]);
            System.out.println("x " + (solutionA+1) + (solutionB+1) + (solutionC+1) + ": " + solutionMatrix[solutionA][solutionB][solutionC]);
            int minEl = min(paramA[solutionA], paramB[solutionB], paramC[solutionC]);
            paramA[solutionA] -= minEl;
            paramB[solutionB] -= minEl;
            paramC[solutionC] -= minEl;
            if (paramA[solutionA] == 0 && solutionA < (paramA.length - 1)) {
                solutionA += 1;
            }
            if (paramB[solutionB] == 0 && solutionB < (paramB.length - 1)) {
                solutionB += 1;
            }
            if (paramC[solutionC] == 0 && solutionC < (paramC.length - 1)) {
                solutionC += 1;
            }
            checkA = 0;
            checkB = 0;
            checkC = 0;
            for (int aParamA : paramA) {
                checkA += aParamA;
            }
            for (int aParamB : paramB) {
                checkB += aParamB;
            }
            for (int aParamC : paramC) {
                checkC += aParamC;
            }
            if (checkA == 0 && checkB == 0 && checkC == 0){
                System.out.println("Начальный опорный план вычислен");
                break;
            }
        }
    }

    public void getUvw(int[][][] solutionMatrix, int[][][] mainMatrix, int m, int n, int p) {
        int[][][] paramMatrix = new int[mainMatrix.length][mainMatrix[1].length][mainMatrix[0][1].length];
        int[] u = new int[m];
        int[] v = new int[n];
        int[] w = new int[p];

        for (int i=0; i<solutionMatrix.length; i++){
            for (int j=0; j<solutionMatrix[1].length; j++){
                for (int k=0; k<solutionMatrix[0][1].length; k++){
                    if (solutionMatrix[i][j][k] != 0){
                        paramMatrix[i][j][k] = mainMatrix[i][j][k];
                    }
                }
            }
        }

        System.out.println(paramMatrix.length + " " + paramMatrix[1].length + " " + paramMatrix[0][1].length);

        u[0] = 0;
        v[0] = 0;

        while (true){
            for (int i=0; i<paramMatrix.length; i++){
                for (int j=0; j<paramMatrix[1].length; j++){
                    for (int k=0; k<paramMatrix[0][1].length; k++){
                        if (paramMatrix[i][j][k] != 0){
                            if (!(isUVNull(u, i)) && !(isUVNull(v, j)) && (isWNull(w, k))){
                                w[k] = paramMatrix[i][j][k] - u[i] - v[j];
                                System.out.println("w[" + (k+1) +"] : " + w[k]);
                            }
                            if (!(isUVNull(u, i)) && (isUVNull(v, j)) && !(isWNull(w, k))){
                                v[j] = paramMatrix[i][j][k] - u[i] - w[k];
                                System.out.println("v[" + (j+1) +"] : " + v[j]);
                            }
                            if ((isUVNull(u, i)) && !(isUVNull(v, j)) && !(isWNull(w, k))){
                                u[i] = paramMatrix[i][j][k] - v[j] - w[k];
                                System.out.println("u[" + (i+1) +"] : " + u[i]);
                            }
                        }
                    }
                }
            }

            boolean breakCase = true;
            for (int i=0; i<paramMatrix.length; i++){
                if (u[i] == 0 && i != 0) breakCase = false;
            }
            for (int i=0; i<paramMatrix[1].length; i++){
                if (v[i] == 0 && i != 0) breakCase = false;
            }
            for (int i=0; i<paramMatrix[0][1].length; i++){
                if (w[i] == 0) breakCase = false;
            }
            if (breakCase) {
                System.out.println("Потенциалы подобраны");
                break;
            }
        }

    }

    public boolean isSolutionOptimal(int[][][] mainMatrix, int[] u, int[] v, int[] w) {
        int minEle = mainMatrix[0][0][0] - u[0] - v[0] - w[0];
        int[][][] minMatrix = new int[mainMatrix.length][mainMatrix[1].length][mainMatrix[0][1].length];
        int minI = 0;
        int minJ = 0;
        int minK = 0;

        for (int i=0; i<mainMatrix.length; i++){
            for (int j=0; j<mainMatrix[1].length; j++){
                for (int k=0; k<mainMatrix[0][1].length; k++){
                    minMatrix[i][j][k] = mainMatrix[i][j][k] - u[i] - v[j] - w[k];
                    minEle = Math.min(minEle, minMatrix[i][j][k]);
                    if (minEle > minMatrix[i][j][k]) {
                        minI = i;
                        minJ = j;
                        minK = k;
                    }
                }
            }
        }

        return minEle == 0;

    }

    private boolean isUVNull(int[] test, int i){
        if (i == 0) return false;
        return test[i] == 0;
    }

    private boolean isWNull(int[] test, int i){
        return test[i] == 0;
    }

    private int min(int a, int b, int c){
        return Math.min(a, Math.min(b, c));
    }

    public int[][][] getMainMatrix() {
        return mainMatrix;
    }

    public void setMainMatrix(int[][][] mainMatrix) {
        this.mainMatrix = mainMatrix;
    }

    public int[][][] getSolutionMatrix() {
        return solutionMatrix;
    }

    public void setSolutionMatrix(int[][][] solutionMatrix) {
        this.solutionMatrix = solutionMatrix;
    }

    public int[] getParamA() {
        return paramA;
    }

    public void setParamA(int[] paramA) {
        this.paramA = paramA;
    }

    public int[] getParamB() {
        return paramB;
    }

    public void setParamB(int[] paramB) {
        this.paramB = paramB;
    }

    public int[] getParamC() {
        return paramC;
    }

    public void setParamC(int[] paramC) {
        this.paramC = paramC;
    }

    public boolean areParamsCorrect(int[] paramA, int[] paramB, int[] paramC) {
        int sumA = 0;
        int sumB = 0;
        int sumC = 0;
        for (int aParamA : paramA) {
            sumA += aParamA;
        }
        for (int aParamB : paramB) {
            sumB += aParamB;
        }
        for (int aParamC : paramC) {
            sumC += aParamC;
        }
        if (sumA != sumB) return false;
        if (sumA != sumC) return false;
        return sumB == sumC;
    }
}
