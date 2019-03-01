package sword.ch2;

/**
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 *
 * @author Fighter Created on 2018/5/4.
 */
public class P92_RobotMove {

    //注意计数从 0 开始，因为是从（0, 0）开始算

    private int maxCnt = 0;
    int rows, cols;

    public int movingCount(int threshold, int rows, int cols) {

        if (threshold < 0 || rows < 0 || cols < 0) {
            return 0;
        }

        this.rows = rows;
        this.cols = cols;

        boolean[][] visited = new boolean[rows][cols];
        movingCount(threshold, visited, 0, 0);
        return maxCnt;

    }

    public void movingCount(int threshold, boolean[][] visited, int row, int col) {

        if (row < 0 || col < 0 || row >= this.rows || col >= this.cols || !check(threshold, row, col) || visited[row][col]) {
            return;
        } else {
            visited[row][col] = true;
            System.out.println(row + " " + col);
            maxCnt++;
            movingCount(threshold, visited, row - 1, col);
            movingCount(threshold, visited, row, col - 1);
            movingCount(threshold, visited, row + 1, col);
            movingCount(threshold, visited, row, col + 1);
        }
    }


    public static boolean check(int threshold, int i, int j) {
        int sum = 0;
        while (i > 0) {
            sum += i % 10;
            i /= 10;
        }
        while (j > 0) {
            sum += j % 10;
            j /= 10;
        }
        if (sum > threshold) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new P92_RobotMove().movingCount(5, 10, 10)); //1
//        System.out.println(movingCount(1,3,4)); //3
//        System.out.println(movingCount(9,2,20)); //36
    }
}

class P92_RobotMove2 {

    int rows, cols, threshold, res;
    boolean[][] visited;
    int[][] sum;
    int[][] next = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int movingCount(int threshold, int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            return 0;
        }

        this.rows = rows;
        this.cols = cols;
        this.threshold = threshold;

        visited = new boolean[rows][cols];
        sum = new int[rows][cols];
        initSum();
        movingCore(0, 0);
        return res;
    }

    void movingCore(int i, int j) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || visited[i][j]) {
            return;
        }
        //只要不越界都能访问，但若不满足阈值条件则不计数，相当于遍历所有格子
        visited[i][j] = true;
        if (sum[i][j] > threshold) {
            return;
        }
        res++;
        for (int[] n : next) {
            movingCore(i + n[0], j + n[1]);
        }
    }

    void initSum() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum[i][j] = getSum(i, j);
            }
        }
    }

    int getSum(int i, int j) {
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i /= 10;
        }
        while (j != 0) {
            sum += j % 10;
            j /= 10;
        }
        return sum;
    }
}
