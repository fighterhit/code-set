/**
 * @author Fighter Created on 2018/4/28.
 */
public class Test {
    private  int n = 0;
    public static void main(String[] args) {
        /*List ls = new ArrayList(),lss=new ArrayList(),ls2=new ArrayList();
        ls.add(1);
        ls.add(2);
        lss.add(3);
        lss.add(4);
        ls2.add(ls);
        ls2.add(lss);
        System.out.println(ls2);*/

        /*int[] a = new int[]{0, 1, 2, 3, 4};
        swap(a, 1, 3);
        System.out.println(Arrays.toString(a));*/

       /* int[] step = new int[3];
        step[0] = 1;
        System.out.println(Arrays.toString(step));*/

      /*  System.out.println(Integer.toBinaryString(-7));
        System.out.println(Integer.toBinaryString(-7 - 1));

        System.out.println(Integer.toBinaryString(10));
        System.out.println(Integer.toBinaryString(10 - 1));

        System.out.println(Integer.toBinaryString(-10));
        System.out.println(Integer.toBinaryString(-10 - 1));*/
        Test t = new Test();
        t.fun();
        System.out.println(t.n);

    }

    void fun(){
        n++;
        if (n == 1230){
            return;
        }
        fun();
    }

    private static void swap(int[] numbers, int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }
}
