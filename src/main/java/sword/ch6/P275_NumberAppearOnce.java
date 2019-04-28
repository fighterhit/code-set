package sword.ch6;

public class P275_NumberAppearOnce {
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        int res = 0;
        for (int i = 0; i < array.length; i++) {
            res ^= array[i];
        }
        int r1Index = 0;
        for (int i = 0; i < 32; i++) {
            if ((res >> i & 1) == 1) {
                r1Index = i;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if ((array[i] >> r1Index & 1) == 1) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
    }
}
