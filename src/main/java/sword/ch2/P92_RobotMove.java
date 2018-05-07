package sword.ch2;

/**
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 *
 * @author Fighter Created on 2018/5/4.
 */
public class P92_RobotMove {

    private int maxCnt = 0;
    int rows, cols;

    public int movingCount(int threshold, int rows, int cols) {

        this.rows = rows;
        this.cols = cols;

        if (threshold < 0 || rows < 0 || cols < 0) {
            return 0;
        }

        boolean[][] visited = new boolean[rows][cols];
        movingCount(threshold, visited, 0, 0, 1);
        return maxCnt;

    }

    public void movingCount(int threshold, boolean[][] visited, int row, int col, int tmpCnt) {

        if (row < 0 || col < 0 || row >= this.rows || col >= this.cols || !check(threshold, row, col) || visited[row][col]) {
            return;
        } else {
            tmpCnt += 1;
            maxCnt = Math.max(tmpCnt, maxCnt);
            movingCount(threshold, visited, row - 1, col, tmpCnt);
            movingCount(threshold, visited, row, col - 1, tmpCnt);
            movingCount(threshold, visited, row + 1, col, tmpCnt);
            movingCount(threshold, visited, row, col + 1, tmpCnt);
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
        System.out.println(new P92_RobotMove().movingCount(5,10,10)); //1
//        System.out.println(movingCount(1,3,4)); //3
//        System.out.println(movingCount(9,2,20)); //36
    }
}
