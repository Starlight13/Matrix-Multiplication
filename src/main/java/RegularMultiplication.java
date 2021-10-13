public class RegularMultiplication {

    public static int[][] multiplication(int[][] a, int[][] b) throws Exception {
        Matrix.check(a, b);
        int[][] result = Matrix.zeroMatrix(a.length, b[0].length);

        long start = System.currentTimeMillis();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        long finish = System.currentTimeMillis();
        double t = (finish - start) / 1000.0;
        System.out.println("Regular time: " + t + " sec.");
        return result;
    }
}
