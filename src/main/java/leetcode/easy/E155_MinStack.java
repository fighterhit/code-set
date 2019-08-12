package leetcode.easy;

import java.util.Stack;

public class E155_MinStack {
    class MinStack {

        Stack<Integer> dataS = new Stack<>();
        Stack<Integer> minS = new Stack<>();
        private int min = Integer.MAX_VALUE;

        /**
         * initialize your data structure here.
         */
        public MinStack() {

        }

        public void push(int x) {
            dataS.push(x);
            min = Math.min(min, x);
            minS.push(min);
        }

        public void pop() {
            dataS.pop();
            minS.pop();
            min = dataS.isEmpty() ? Integer.MAX_VALUE : minS.peek();
        }

        public int top() {
            return dataS.peek();
        }

        public int getMin() {
            return min;
        }
    }
}
