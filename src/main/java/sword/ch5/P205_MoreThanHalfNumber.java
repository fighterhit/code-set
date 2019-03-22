package sword.ch5;

/**
 * @author Fighter.
 */
public class P205_MoreThanHalfNumber {
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null) {
            return 0;
        }
        int result = array[0], cnt = 1;
        for (int i = 1; i < array.length; i++) {
            if (cnt == 0) {
                result = array[i];
                cnt = 1;
            } else if (result == array[i]) {
                cnt++;
            } else {
                cnt--;
            }
        }
        cnt = 0;
        for (int n : array) {
            if (n == result){
                cnt++;
            }
        }
        if (cnt * 2 > array.length){
            return result;
        }
        return 0;
    }
}
