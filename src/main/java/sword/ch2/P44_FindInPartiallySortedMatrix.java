package sword.ch2;

/**
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * @author Fighter Created on 2018/4/18.
 */
public class P44_FindInPartiallySortedMatrix {
    public boolean Find(int target, int[][] array) {
        int rowLen = array.length, colLen = array.length;
        //注意判断行长度和列长度，都可能为0
        //int[][] arr = new int[][]{};   //0,
        //int[][] arr = new int[][]{{}}; //1, 0
        if(array.length == 0 || array[0].length == 0){
            return false;
        }
        //从右上角开始遍历
        for (int i = 0, j = colLen - 1; i <= rowLen - 1 && j >= 0; ) {
            if (array[i][j] > target) {
                j--;
            } else if (array[i][j] < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int[][] arr = new int[][]{}; //0, 1
        int[][] arr = new int[][]{{}}; //1, 0
        System.out.println(arr.length);
        System.out.println(arr[0].length);
    }
}
