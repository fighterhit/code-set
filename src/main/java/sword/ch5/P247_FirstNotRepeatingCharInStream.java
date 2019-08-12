package sword.ch5;

import java.util.LinkedList;
import java.util.Queue;

public class P247_FirstNotRepeatingCharInStream {

    static class CharStatistics {
        public int[] times;
        private int index;

        public CharStatistics() {
            this.times = new int[256];
            this.index = 0;

            //-1表示不存在，>=0表示字符出现在字符流的索引，-2表示出现两次以上
            for (int i = 0; i < 256; i++) {
                times[i] = -1;
            }
        }

        public void insert(char c) {
            if (times[c] == -1) {
                times[c] = index;
            } else {
                times[c] = -2;
            }
            index++;
        }

        public char find() {
            char ret = '?';
            int minIndex = Integer.MAX_VALUE;
            for (int i = 0; i < 256; i++) {
                // 出现次数最小 且 索引最小
                if (times[i] >= 0 && times[i] < minIndex) {
                    minIndex = times[i];
                    ret = (char) i;
                }
            }
            return ret;
        }

    }


    public static void main(String[] args) {
        String str = "google";
        CharStatistics charStatistics = new CharStatistics();
        for (int i = 0; i < str.length(); i++) {
            charStatistics.insert(str.charAt(i));
            System.out.print(charStatistics.find());
        }
    }
}

class Solution {
    char[] cnt = new char[256];
    Queue<Character> q = new LinkedList<>();

    //Insert one char from stringstream
    public void Insert(char ch) {
        cnt[ch]++;
        q.add(ch);
        while (!q.isEmpty() && cnt[q.peek()] > 1) {
            q.poll();
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        return q.isEmpty() ? '#' : q.peek();
    }
}
