public class Matrix {

    public static void check(int[][] a, int[][] b) throws Exception{
        if (a[0].length != b.length || a.length != b[0].length) {
            throw new Exception("EXCEPTION");
        }
    }

    public static int[][] randomMatrix(int rowSize, int columnSize) {
        int matrix[][] = new int[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            matrix[i] = new int[columnSize];
            for (int l = 0; l < columnSize; l++) {
                matrix[i][l] = (int) (Math.random() * (9 - 1)) + 1;
            }
        }
        return matrix;
    }

    public static int[][] zeroMatrix(int rowSize, int columnSize) {
        int matrix[][] = new int[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            matrix[i] = new int[columnSize];
            for (int l = 0; l < columnSize; l++) {
                matrix[i][l] = 0;
            }
        }
        return matrix;
    }

    public static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int l = 0; l < matrix[i].length; l++) {
                System.out.printf("%7d", matrix[i][l]);
            }
            System.out.println();
        }
    }
}
