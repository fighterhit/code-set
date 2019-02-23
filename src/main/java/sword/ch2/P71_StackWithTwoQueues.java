package sword.ch2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列实现栈
 * 方法二：queue1 队列投始终保持最新元素，每次 push调整顺序
 * https://leetcode.com/problems/implement-stack-using-queues/description/
 *
 * @author Fighter Created on 2018/4/29.
 */
public class P71_StackWithTwoQueues<T> {

    class MyStack {

        private Queue<Integer> queue1 = new LinkedList<>();
        private Queue<Integer> queue2 = new LinkedList<>();

        int n = 0;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {

        }

        /**
         * Push element x onto stack.
         */
        public void push(int data) {
            if (!queue1.isEmpty()) {
                queue1.offer(data);
            } else {
                queue2.offer(data);
            }
            n++;
        }

        public void push2(int data) {
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
            queue1.offer(data);
            while (queue2.isEmpty()) {
                queue1.offer(queue2.poll());
            }
            n++;
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            if (!queue1.isEmpty()) {
                while (queue1.size() != 1) {
                    queue2.offer(queue1.poll());
                }
                n--;
                return queue1.poll();
            } else {
                while (queue2.size() != 1) {
                    queue1.offer(queue2.poll());
                }
                n--;
                return queue2.poll();
            }
        }

        public int pop2() {
            if (!queue1.isEmpty()) {
                while (queue1.size() != 1) {
                    queue2.offer(queue1.poll());
                }
                n--;
                return queue1.poll();
            } else {
                while (queue2.size() != 1) {
                    queue1.offer(queue2.poll());
                }
                n--;
                return queue2.poll();
            }
        }

        /**
         * Get the top element.
         */
        public int top() {
            if (!queue1.isEmpty()) {
                while (queue1.size() != 1) {
                    queue2.offer(queue1.poll());
                }
                int tmp = queue1.poll();
                queue2.offer(tmp);
                return tmp;
            } else {
                while (queue2.size() != 1) {
                    queue1.offer(queue2.poll());
                }
                int tmp = queue2.poll();
                queue1.offer(tmp);
                return tmp;
            }
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return n == 0;
        }
    }

    /**
     * Your MyStack object will be instantiated and called as such:
     * MyStack obj = new MyStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * boolean param_4 = obj.empty();
     */


    class MyStack2 {

        private Queue<Integer> queue1;
        private Queue<Integer> queue2;

        /**
         * Initialize your data structure here.
         */
        public MyStack2() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */

        public void push(int data) {
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
            queue1.offer(data);
            while (!queue2.isEmpty()) {
                queue1.offer(queue2.poll());
            }

        }

        /**
         * Removes the element on top of the stack and returns that element.
         */

        public int pop() {
            return queue1.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return queue1.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return queue1.isEmpty();
        }
    }

    class MyStack3 {

        private Queue<Integer> queue1;
        private Queue<Integer> queue2;

        /**
         * Initialize your data structure here.
         */
        public MyStack3() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */

        public void push(int data) {
            if (!queue2.isEmpty()) {
                queue2.offer(data);
            } else {
                queue1.offer(data);
            }
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            if (!queue2.isEmpty()) {
                int size = queue2.size();
                for (int i = 0; i < size - 1; i++) {
                    queue1.offer(queue2.poll());
                }
                return queue2.poll();
            } else if (!queue1.isEmpty()) {
                int size = queue1.size();
                for (int i = 0; i < size - 1; i++) {
                    queue2.offer(queue1.poll());
                }
                return queue1.poll();
            } else {
                return -1;
            }
        }

        /**
         * Get the top element.
         */
        public int top() {
            if (queue2.size() > 0) {
                return queue2.peek();
            } else if (queue1.size() > 0) {
                return queue1.peek();
            } else {
                return -1;
            }
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }
}


