package leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

public class E225_ImplementStackusingQueues {
    class MyStack {

        Queue<Integer> q = new LinkedList<>();

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            q.add(x);
            int n = q.size();
            for (int i = 0; i < n - 1; i++) {
                q.add(q.poll());
            }
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return q.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return q.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return q.isEmpty();
        }
    }
}
