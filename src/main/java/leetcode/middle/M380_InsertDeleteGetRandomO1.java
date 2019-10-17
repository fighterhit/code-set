package leetcode.middle;

import java.util.*;

public class M380_InsertDeleteGetRandomO1 {
    class RandomizedSet {
        Map<Integer, Integer> map;
        List<Integer> index;
        Random random;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            map = new HashMap<>();
            index = new ArrayList<>();
            random = new Random();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            index.add(val);
            map.put(val, index.size() - 1);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int curIndex = map.get(val);
            //注意先判断是否只有一个元素，若只有一个元素则直接删除即可，大于 1 个元素再交换并删除 index 最后一个元素，否则1个元素交换会造成没有删除
            if (curIndex < index.size() - 1) {
                int tailVal = index.get(index.size() - 1);
                swap(index, curIndex, index.size() - 1);
                map.put(tailVal, curIndex);
            }
            map.remove(val);
            index.remove(index.size() - 1);
            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            return index.get(random.nextInt(index.size()));
        }

        public void swap(List<Integer> ls, int i, int j) {
            int a = ls.get(i), b = ls.get(j);
            ls.set(i, b);
            ls.set(j, a);
        }
    }
}

class T{
    public static void main(String[] args) {
        int[] arr = new int[1];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

    }
}