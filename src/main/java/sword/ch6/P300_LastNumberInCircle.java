package sword.ch6;

import sword.ListNode;

import java.util.Scanner;

/**
 * 约瑟夫环问题：
 * http://tingyun.site/2018/04/26/%E7%BA%A6%E7%91%9F%E5%A4%AB%E7%8E%AF%E9%97%AE%E9%A2%98%E8%AF%A6%E8%A7%A3/
 * https://blog.csdn.net/wusuopubupt/article/details/18214999
 */
public class P300_LastNumberInCircle {
    public int LastRemaining_Solution(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        int[] nums = new int[n];
        ListNode<Integer> head = new ListNode<>(0);
        ListNode<Integer> cur = head;
        for (int i = 1; i < n; i++) {
            ListNode<Integer> node = new ListNode<>(i);
            cur.next = node;
            cur = cur.next;
        }
        cur.next = head;
        cur = head;
        while (true) {
            //判断是否只剩一个元素
            if (cur == cur.next) {
                return cur.val;
            }
            for (int i = 1; i < m; i++) {
                cur = cur.next;
            }
            //删除元素，相当于下个元素值挪到当前元素
            cur.val = cur.next.val;
            cur.next = cur.next.next;
        }
    }

    public int LastRemaining_Solution2(int n, int m) {
        // 特殊输入的处理
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }
        return (LastRemaining_Solution2(n - 1, m) + m) % n;
    }

    public static void main(String[] args) {
        System.out.println(new P300_LastNumberInCircle().LastRemaining_Solution2(5, 3)); //3
        System.out.println(new P300_LastNumberInCircle().LastRemaining_Solution2(6, 7)); //4
        System.out.println(new P300_LastNumberInCircle().LastRemaining_Solution2(0, 7)); //-1
    }
}

class Solution{
    /**
     * 最长公共子序列
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String s1 = scan.nextLine();
        String s2 = scan.nextLine();
        String maxSubString = getSubString(s1, s2);
        System.out.println("maxSubString:"+maxSubString);

     /*   Scanner scanner=new Scanner(System.in);
        String s1=scanner.nextLine();
        String s2=scanner.nextLine();
        //找出两个字符串中谁的长度长
        String min=(s1.length()<=s2.length())?s1:s2;
        String max=(min.equals(s1))?s2:s1;
        //如果长的字符串包含短的字符串，直接返回
        if(max.contains(min)){
            System.out.println(min);
            return;
        }
        //字符串从末尾开始慢慢缩减长度，如果包含就直接输出并返回即可
        //外层循环控制start的起始位置
        for(int i=0;i<min.length();i++){
            //每次都是从末端缩减长度，这样的话，如果包含就可以直接输出，即为包含的最长的字符串
            for(int start=i,end=min.length();end>start;end--){
                String temp=min.substring(start,end);
                if(max.contains(temp)){
                    System.out.println(temp);
                    return;
                }
            }
        }*/
    }
    /**
     * 用到subString(start,end)方法，依次缩减字符串
     * @param s1
     * @param s2
     * @return
     */
    public static String getSubString(String s1,String s2){
        //记录相同字符串
        String sameString = null;
        //比较两个字符串的长度，这里是设置s1的长度大于s2
        if(s1.length()<s2.length()){
            //如果s2的长度大，那么就将两个字符串进行替换
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        //如果s2就被包含在s1中，那么这两个字符串最大的字串就是s2
        boolean isContains = s1.contains(s2);
        if(isContains){
            return s2;
        }else{
            boolean b1 = false;
            //如果s2不是两个字符串最大的子类，那么在进行循环查找
            for(int i=0;i<s2.length();i++){
                for(int j=0;j<=i;j++){
                    //获取每次进行比较的子字符串
                    String str = s2.substring(j,s2.length()-i+j);
                    System.out.println("第"+i+"次比较："+str);
                    if(s1.contains(str)){
                        sameString =str;
                        b1 = true;
                        break;
                    }
                }
                //如果比较到s2中最小的为2的时候还没有想同的字符串，我们就默认没有相同的子字符串
                if(s2.substring(0, s2.length()-i).length() ==2){
                    System.out.println("没有相同的子字符串");
                    b1 = true;
                    break;
                }
                if(b1 ==true){
                    break;
                }
            }

        }

        return sameString;

    }
}