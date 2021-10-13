public class FoxMultiplication {

    public int[][] multiplication(int[][] a, int[][] b, int blockSize) throws Exception {
        Matrix.check(a, b);

        Block[][] blocksA = divideToBlocks(a, blockSize);
        Block[][] blocksB = divideToBlocks(b, blockSize);
        Block[][] blocksC = divideToBlocks(Matrix.zeroMatrix(a.length, b[0].length), blockSize);

        FoxThread[][] foxThreads = new FoxThread[a.length / blockSize][];
        for (int i = 0; i < blocksC.length; i++) {
            foxThreads[i] = new FoxThread[blocksC[i].length];
        }

        long start = System.currentTimeMillis();
        boolean flag = false;
        for (int step = 0; step < foxThreads.length; step++) {
            for (int i = 0; i < foxThreads.length; i++) {
                for (int l = 0; l < foxThreads[i].length; l++) {
                    int k = i + step > foxThreads.length - 1 ? i + step - foxThreads.length : i + step;
                    foxThreads[i][l] = new FoxThread(blocksA[i][k], blocksB[k][l], blocksC[i][l]);
                    if (flag) {
                        foxThreads[i][l].join();
                    }
                    foxThreads[i][l].start();
                }
            }
            flag = true;
        }

        for (int i = 0; i < foxThreads.length; i++) {
            for (int l = 0; l < foxThreads[i].length; l++) {
                foxThreads[i][l].join();
            }
        }
        long finish = System.currentTimeMillis();
        double t = (finish - start) / 1000.0;
        System.out.println("Fox algorithm time : " + t + " sec.");
        return joinBlocks(blocksC, blockSize);
    }

    private Block[][] divideToBlocks(int[][] matrix, int blockSize) throws Exception {
        if (matrix.length % blockSize != 0 || matrix[0].length % blockSize != 0) {
            throw new Exception("Error!");
        }

        Block[][] dividedMatrix = new Block[matrix.length / blockSize][];

        for (int i = 0; i < dividedMatrix.length; i++) {
            dividedMatrix[i] = new Block[matrix[i].length / blockSize];
            for (int l = 0; l < dividedMatrix[i].length; l++) {
                int[][] divisionMatrix = new int[blockSize][blockSize];
                for (int j = blockSize * i; j < blockSize * (i + 1); j++) {
                    for (int k = blockSize * l; k < blockSize * (l + 1) ; k++) {
                        divisionMatrix[j - (blockSize * i)][k - (blockSize * l)] = matrix[j][k];
                    }
                }
                dividedMatrix[i][l] = new Block(divisionMatrix);
            }
        }
        return dividedMatrix;
    }

    private int[][] joinBlocks(Block[][] blocks, int size) {
        int[][] joinedMatrix = Matrix.zeroMatrix(blocks.length * size, blocks.length * size);

        for (int i = 0; i < blocks.length; i++) {
            for (int l = 0; l < blocks[i].length; l++) {
                for (int j = size * i; j < size * (i + 1); j++) {
                    for (int k = size * l; k < size * (l + 1); k++) {
                        joinedMatrix[j][k] = blocks[i][l].getMatrix()[j - (size * i)][k - (size * l)];
                    }
                }
            }
        }
        return joinedMatrix;
    }
}
