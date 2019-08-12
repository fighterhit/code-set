package sword.ch6;

public class P313_ConstructArray {
    public int[] multiply(int[] A) {
        int n = A.length;
        int[] B = new int[n];
        //正向，计算 A[0]*A[1]*A[2]*...*A[i-1] 部分
        //注意 B[i] 和 product 运算
        for (int i = 0, product = 1; i < n; i++) {
            B[i] = product;
            product *= A[i];
        }
        //反向，计算 A[i+1]*A[i+2]*...*A[n-1]
        //注意 B[i] 和 product 运算
        for (int i = n - 1, product = 1; i >= 0; i--) {
            B[i] *= product;
            product *= A[i];
        }
        return B;
    }
}
