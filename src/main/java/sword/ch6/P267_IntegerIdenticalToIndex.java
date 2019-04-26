package sword.ch6;

public class P267_IntegerIdenticalToIndex {
    public static int getNumberSameAsIndex(int[] data) {
        int l = 0, r = data.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (data[m] == m) {
                return m;
            } else if (data[m] > m) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(getNumberSameAsIndex(new int[]{-3, -1, 1, 3, 5})); //3
        System.out.println(getNumberSameAsIndex(new int[]{0, 1, 2, 3, 4}));   //0~4
        System.out.println(getNumberSameAsIndex(new int[]{4, 5, 6, 7, 8}));   //-1
    }
}
