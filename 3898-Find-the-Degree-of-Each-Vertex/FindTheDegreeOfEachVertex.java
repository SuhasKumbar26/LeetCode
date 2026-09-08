class FindTheDegreeOfEachVertex {

    public int[] findDegrees(int[][] matrix) {
        int n = matrix.length;
        int[] degree = new int[n];

        for (int i = 0; i < n; i++) {
            int sum = 0;

            for (int j = 0; j < n; j++) {
                sum += matrix[j][i];
            }

            degree[i] = sum;
        }

        return degree;
    }
}