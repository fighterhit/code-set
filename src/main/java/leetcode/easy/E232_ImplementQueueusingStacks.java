package leetcode.easy;

import java.util.Stack;

public class E232_ImplementQueueusingStacks {

    class MyQueue {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {

        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            s1.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            in2out();
            return s2.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            in2out();
            return s2.peek();
        }

        public void in2out() {
            if (s2.isEmpty()) {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return s1.isEmpty() && s2.isEmpty();
        }
    }
}
