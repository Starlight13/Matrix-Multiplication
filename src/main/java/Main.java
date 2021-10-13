public class Main {

    public static void main(String[] args) throws Exception {
        testMatrix();
    }

    public static void testMatrix() throws Exception {
        LinearMultiplication linearMultiplication = new LinearMultiplication();
        FoxMultiplication foxMatrixMultiplication = new FoxMultiplication();

//        int[][] b = {{1,2,3}, {11,12,13}, {21,22,23}};
        int[][] b = Matrix.randomMatrix(1000, 1000);

        int[][] resultR = RegularMultiplication.multiplication(b,b);
        int[][] resultP1 = linearMultiplication.multiplication(b,b,0);
        int[][] resultP2 = foxMatrixMultiplication.multiplication(b,b,1);

        System.out.println("Regular:");
        Matrix.print(resultR);
        System.out.println();
        System.out.println("Linear algorithm:");
        Matrix.print(resultP1);
        System.out.println();
        System.out.println("Fox algorithm:");
        Matrix.print(resultP2);
    }
}
